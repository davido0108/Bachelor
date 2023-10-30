package chat.repository.dbrepos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class  JdbcUtils {

    private Properties jdbcProps;

    private static final Logger logger= LogManager.getLogger();

    public JdbcUtils(){
        try {
            Properties props = new Properties();
            props.load(new FileReader("bd.config"));
            jdbcProps=props;
        } catch (
                IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
    }

    private  Connection instance=null;

    private Connection getNewConnection(){
        logger.traceEntry();

        String url=jdbcProps.getProperty("jdbc.url");
        String user=jdbcProps.getProperty("jdbc.user");
        String pass=jdbcProps.getProperty("jdbc.pass");
        logger.info("trying to connect to database ... {}",url);
        logger.info("user: {}",user);
        logger.info("pass: {}", pass);
        Connection con=null;
        try {

            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(instance);
        return instance;
    }

    public void closeConnection(){
        if (instance!=null) {
            try {
                instance.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}