package controller;

import Service.Service;
import domain.Angajat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController {
    @FXML
    private TextField user123;
    @FXML
    private PasswordField password;

    private Service srv;
    private Long id;

    public void initial(Service s){
        this.srv = s;
    }

    


    public void login123(MouseEvent mouseEvent)  throws Exception{
        String u  = user123.getText();
        String p = password.getText();

        Angajat angajat = null;

        Iterable<Angajat> lis = srv.getAllAngajati();

        for (Angajat angajat1 : lis){
            if(angajat1.getEmail().equals(u) && angajat1.getParola().equals(p))
                angajat = angajat1;
        }

        if(angajat == null){
            System.out.println("datele sunt introduse gresit");
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Menu.fxml"));
        Pane root;
        root = loader.load();
        Stage register_stage=new Stage();

        MenuController menu = loader.getController();
        menu.initial(srv);
        register_stage.setScene(new Scene(root));
        register_stage.show();
    }


}
