//import Network.Utils.AbstractServer;
//import Network.Utils.ConcurrentServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartRpcServer {

//    private static int defaultPort = 55555;

    public static void main(String[] args){

        /*Properties serverProps = new Properties();
        try{
            serverProps.load(StartRpcServer.class.getResourceAsStream("/Server.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find /Server.properties " + e);
            return;
        }

        AngajatRepo angajatRepo = new AngajatDBRepo(serverProps);
        SpectacolRepo spectacolRepo = new SpectacolDBRepo(serverProps);
        BiletRepo biletRepo = new BiletDBRepo(serverProps);

        IService srv = new ServerImpl(angajatRepo,spectacolRepo,biletRepo);

        int serverPort = defaultPort;
        try{
            serverPort = Integer.parseInt(serverProps.getProperty("flight.server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number" + nef.getMessage());
            System.err.println("Using default port " + defaultPort);
        }
        System.out.println("Starting server on port: " + serverPort);
        AbstractServer server = new ConcurrentServer(serverPort, srv);
        try{
            server.start();
        }catch (ServerException e){
            System.err.println("Error starting the server" + e.getMessage());
        }finally {
            try {
                server.stop();
            }catch(ServerException e){
                System.err.println("Error stopping server " + e.getMessage());
            }
        }*/

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");

    }
}