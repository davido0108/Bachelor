//import Network.RpcProtocol.ServicesRpcProxy;
import Services.IService;
import controller.LoginController;
import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;

public class StartRpcClient extends Application {


    /*private Stage primaryStage;

    private static int defaultPort = 55555;
    private static String defaultServer = "localhost";


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("In start");
        Properties clientProps = new Properties();
        try {
            clientProps.load(StartRpcClient.class.getResourceAsStream("/client.properties"));
            System.out.println("Client properties set. ");
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find client.properties " + e);
            return;
        }
        String serverIP = clientProps.getProperty("show.server.host", defaultServer);
        int serverPort = defaultPort;
        System.out.println("srv pportttt : " + serverPort);

        try {
            serverPort = Integer.parseInt(clientProps.getProperty("show.server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);


        IService server = new ServicesRpcProxy(serverIP,serverPort);

//        server.AllShows().forEach(s-> System.out.println(s.getName()));

        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("Views/login.fxml"));
        Parent root=loader.load();


        LoginController ctrl = loader.getController();
        ctrl.setServer(server);

        FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("Views/Menu.fxml"));

        Parent root2=loader2.load();
        MenuController ctrl2 = loader2.getController();
        ctrl2.setServer(server);
        ctrl.setMenuPage(root2);
        ctrl.setMenuController(ctrl2);

        primaryStage.setTitle("Shows");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }*/



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:springClientConfig.xml");
        IService server = (IService) factory.getBean("service");
        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("Views\\login.fxml"));
        Parent root=loader.load();


        LoginController ctrl = loader.getController();
        ctrl.setServer(server);

        FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("Views\\Menu.fxml"));

        Parent root2=loader2.load();
        MenuController ctrl2 = loader2.getController();
        ctrl2.setServer(server);
        ctrl.setMenuPage(root2);
        ctrl.setMenuController(ctrl2);

        primaryStage.setTitle("Shows");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
