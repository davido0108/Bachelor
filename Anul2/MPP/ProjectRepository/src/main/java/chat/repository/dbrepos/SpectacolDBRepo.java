package chat.repository.dbrepos;

import chat.domain.Angajat;
import chat.domain.Spectacol;
import chat.repository.SpectacolRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class  SpectacolDBRepo implements SpectacolRepo {

    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public SpectacolDBRepo(Properties props){
        logger.info("Initializing SpectacolDBRepo ");
        dbUtils=new JdbcUtils();
    }

    @Override
    public Spectacol findOne(Long aLong) {
        logger.traceEntry("finding Spectacol with id {}",aLong);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("Select * from Spectacol where id=?")){
            preStmt.setLong(1,aLong);
            try(ResultSet result = preStmt.executeQuery()){
                Long id = result.getLong("id");
                String artist = result.getString("artist");
                String locatie = result.getString("locatie");
                int nrloc_d = result.getInt("nrloc_d");
                int nrloc_v = result.getInt("nrloc_v");
                String data = result.getString("data");
                Spectacol spectacol = new Spectacol(artist, locatie, data, nrloc_d, nrloc_v);
                spectacol.setId(id);
                logger.traceExit(spectacol);
                return spectacol;
            }
        }catch (SQLException e){
            logger.error(e);
            System.out.println("error db " + e);
        }
        logger.traceExit("No task found with id {}", aLong);
        return null;
    }

    @Override
    public Iterable<Spectacol> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Spectacol> spectacole =new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Spectacol")) {
            try(ResultSet result = preStmt.executeQuery()) {
                while(result.next()){
                    Long id = result.getLong("id");
                    String email = result.getString("artist");
                    String parola = result.getString("locatie");
                    Integer nrloc_d = result.getInt("nrloc_d");
                    Integer nrloc_v = result.getInt("nrloc_v");
                    String data = result.getString("data");

                    Spectacol spectacol = new Spectacol(email,parola,data,nrloc_d,nrloc_v);
                    spectacol.setId(id);
                    spectacole.add(spectacol);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.out.println("Error DB "+ e);
        }
        logger.traceExit();
        return spectacole;
    }

    @Override
    public void save(Spectacol entity) {
        logger.traceEntry("saving task {}", entity);
        System.out.println(entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Spectacol(locatie,nrloc_d,nrloc_v,data,artist) values (?,?,?,?,?)")){
            preStmt.setString(1,entity.getLocatie());
            preStmt.setInt(2,entity.getLocuriDisponibile());
            preStmt.setInt(3,entity.getLocuriVandute());
            preStmt.setString(4,entity.getDate());
            preStmt.setString(5,entity.getArtist());


            int result = preStmt.executeUpdate();
            logger.trace("saved {} instances ",result);

        }catch(SQLException e){
            logger.error(e);
            System.out.println("Error DB" + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Long id) throws Exception {
        Spectacol spectacol = findOne(id);
        logger.traceEntry("deleting spectacol{}",spectacol);
        Connection connection = dbUtils.getConnection();
        if(spectacol == null)
            throw new Exception("Task must exist\n");

        try(PreparedStatement preparedStatement = connection.prepareStatement("delete from Spectacol where id=?")){
            preparedStatement.setInt(1,id.intValue());
            int result = preparedStatement.executeUpdate();
            logger.trace("Deleted {} instances", result);
        }
        catch(SQLException ex){
            logger.error(ex);
            System.err.println("Error DB" + ex);
        }
        catch(Exception ex){
            logger.error(ex);
            System.err.println(ex);
        }
        logger.traceExit();

    }

    @Override
    public void update(Spectacol entity) {
        logger.traceEntry("Update Spectacol with id :", entity.getId());
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStmt = con.prepareStatement("UPDATE Spectacol SET locatie=?, nrloc_d=?, nrloc_v=?, data=?, artist=? WHERE id=?")){
            preStmt.setString(1,entity.getLocatie());
            preStmt.setInt(2,entity.getLocuriDisponibile());
            preStmt.setInt(3,entity.getLocuriVandute());
            preStmt.setString(4,entity.getDate());
            preStmt.setString(5,entity.getArtist());
            preStmt.setLong(6,entity.getId());
            System.out.println(entity);
            int result = preStmt.executeUpdate();
            logger.trace(" updated {} instances",result);

        }catch (SQLException e){
            logger.error(e);
            System.out.println("Error DB" + e);
        }
        logger.traceExit();
    }
}
