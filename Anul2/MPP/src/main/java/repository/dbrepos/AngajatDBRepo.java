package repository.dbrepos;

import domain.Angajat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.AngajatRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AngajatDBRepo implements AngajatRepo {

    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public AngajatDBRepo(Properties props){
        logger.info("Initializing AngajatDBRepo with properties: {} ", props);
        dbUtils = new JdbcUtils(props);
    }


    @Override
    public Angajat findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Angajat> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Angajat> angajati =new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Angajat")) {
            try(ResultSet result = preStmt.executeQuery()) {
                while(result.next()){
                    Long id = result.getLong("id");
                    String email = result.getString("email");
                    String parola = result.getString("parola");

                    Angajat angajat = new Angajat(email,parola);
                    angajat.setId(id);
                    angajati.add(angajat);
                }
            }
            }catch (SQLException e){
            logger.error(e);
            System.out.println("Error DB "+ e);
        }
        logger.traceExit();
        return angajati;
    }

    @Override
    public void save(Angajat entity) {
        logger.traceEntry("saving tasks{}", entity);
        Connection con =dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Angajat (email,parola) values (?,?)")){
            preStmt.setString(1,entity.getEmail());
            preStmt.setString(2,entity.getParola());
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error db" + ex);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Long aLong) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("delete from Angajat where id=?")){
            preStmt.setLong(1,aLong);
            int result = preStmt.executeUpdate();
            logger.trace("Deleted {} instance",result);
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Angajat entity) {

    }
}
