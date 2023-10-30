package controller;

import Services.IObserver;
import Services.IService;
import Services.ProjectException;
import chat.domain.Angajat;
import chat.domain.Spectacol;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MenuController extends UnicastRemoteObject implements IObserver, Serializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView search_table;


    @FXML
    private TableColumn artist;

    @FXML
    private TableColumn date;

    @FXML
    private TableColumn locatie;

    @FXML
    private TableColumn nrloc_d;

    @FXML
    private TableColumn nrloc_v;

    @FXML
    private TextField artist_search;

    ObservableList<Spectacol> spectacolObservable = FXCollections.observableArrayList();

    private IService srv;
    private Long id;

    private ArrayList<Spectacol> spectacole;
    private Angajat angajat;
    private BuyController buyController;

    public MenuController() throws RemoteException {
    }


    public void initial(IService s){
        this.srv = s;


        artist.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Artist"));
        locatie.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Locatie"));
        date.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Date"));
        nrloc_d.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriDisponibile"));
        nrloc_v.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriVandute"));


        search_table.setItems(spectacolObservable);


    }
    public void initi_srv(){

        System.out.println("aici2312312");
        artist.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Artist"));
        locatie.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Locatie"));
        date.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Date"));
        nrloc_d.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriDisponibile"));
        nrloc_v.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriVandute"));


        search_table.setItems(spectacolObservable);


    }


    public void setServer(IService s) { this.srv = s;}

    public void search_by_Artist(MouseEvent actionEvent) throws ProjectException {

        String artist = artist_search.getText();

        if(artist.equals(null))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Pick a date...");
            alert.showAndWait();
        }
        else{
            initi_srv();
            spectacolObservable.setAll(srv.getALlShows_by_Artist(artist));
        }

        spectacolObservable.forEach(x->{
            System.out.println(x);
        });

//        showObservable.setAll();
    }

    public void buy_ticket(MouseEvent event) throws IOException, ProjectException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Buy_Ticket.fxml"));
        Pane root;
        root = loader.load();
        Stage register_stage=new Stage();

//        BuyController menu = loader.getController();
//        menu.initial(srv);
        BuyController buy =loader.getController();
        buy.initial(srv,this);

        this.buyController = buy;

        register_stage.setScene(new Scene(root));
        register_stage.show();
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();

    }
    @Override
    public void menuUpdate(Spectacol show) throws RemoteException {
        if(this.buyController!=null)
        {
            this.buyController.menuUpdate(show);
        }


         System.out.println("esd");
        Platform.runLater(() -> {
//            System.out.println("try to updateSSSS");

            Spectacol sw=null;
            for (Spectacol s : spectacolObservable) {
                if(s.getId().equals(show.getId()))
                {


                    s.setLocuriDisponibile(show.getLocuriDisponibile());
                    s.setLocuriVandute(show.getLocuriVandute());


//                   sw=s;
                }
            }
            search_table.refresh();
        });


    }
}
