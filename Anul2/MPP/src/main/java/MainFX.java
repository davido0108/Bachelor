import Service.Service;
import controller.LoginController;
import domain.Angajat;
import domain.Spectacol;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.dbrepos.AngajatDBRepo;
import repository.dbrepos.BiletDBRepo;
import repository.dbrepos.SpectacolDBRepo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainFX extends Application {
    private Service srv;

    public void init_srv(){
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        AngajatDBRepo repoAngajat =new AngajatDBRepo(props);
        SpectacolDBRepo spectacolDBRepo = new SpectacolDBRepo(props);
        BiletDBRepo biletDBRepo = new BiletDBRepo(props);

        srv = new Service(repoAngajat,biletDBRepo,spectacolDBRepo);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init_srv();

        System.out.println("ceau");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Login.fxml"));
        Pane root;
        root = loader.load();

        System.out.println("casd/as/dasdas ??");

        LoginController ctr = loader.getController();
        ctr.initial(srv);
        primaryStage.setTitle("Program");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
