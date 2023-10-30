package repository.dbrepos;

import domain.Bilet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.BiletRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class BiletDBRepo implements BiletRepo {

    private JdbcUtils dbutils;

    private static final Logger logger = LogManager.getLogger();

    public BiletDBRepo(Properties props){
        logger.info("Initializing BiletDBRepo with properties {}", props);
        dbutils = new JdbcUtils(props);
    }

    @Override
    public Bilet findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Bilet> findAll() {
        logger.traceEntry();
        Connection con = dbutils.getConnection();
        List<Bilet> biletList = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("Select * from Bilete")){
            try(ResultSet resultSet = preStmt.executeQuery()){
                while (resultSet.next()){
                    Long id = resultSet.getLong("id");
                    String nume = resultSet.getString("nume");
                    int nrloc_dorite = resultSet.getInt("nrloc_dorite");

                    Bilet bilet = new Bilet(nume,nrloc_dorite);
                    bilet.setId(id);
                    biletList.add(bilet);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit();
        return biletList;
    }

    @Override
    public void save(Bilet entity) {
        logger.traceEntry("saving task {}", entity);
        Connection con = dbutils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Bilete(nume,nrloc_dorite) values (?,?)")){
            preStmt.setString(1,entity.getNume());
            preStmt.setInt(2,entity.getNrloc_dorite());

            int result = preStmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.out.println("Error DB" + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Bilet entity) {
    }
}
