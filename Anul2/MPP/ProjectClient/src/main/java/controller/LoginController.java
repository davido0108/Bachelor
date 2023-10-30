package controller;

import Services.IService;
import chat.domain.Angajat;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField user123;
    @FXML
    private PasswordField password;

    private MenuController menuController;
    private Parent mainParent;

    private IService srv;
    private Long id;

    public void initial(IService s){
        this.srv = s;
    }


    public void setMenuPage( Parent parent) { this.mainParent = parent;}
    public void setServer(IService s) { this.srv = s;}
    public void setMenuController(MenuController menu){
        this.menuController = menu;
    }

    public void login123(MouseEvent mouseEvent)  throws Exception{
        String u  = user123.getText();
        String p = password.getText();

        Angajat angajat2 = null;
        try{
            System.out.println("aicia");
            angajat2= srv.login(u,p,menuController);



            System.out.println("sssssssss");



//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/Views/Menu.fxml"));
        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("login failed...");
            alert.showAndWait();
        }
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



        Stage primaryStage = new Stage();
        primaryStage.setTitle("Show");
        primaryStage.setScene(new Scene(mainParent));



//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/Views/Menu.fxml"));
//        Pane root;
//        root = loader.load();
//        Stage register_stage=new Stage();
//
//        MenuController menu = loader.getController();
//        menu.initial(srv);
//        register_stage.setScene(new Scene(root));
//        register_stage.show();
        primaryStage.show();
    }


}
