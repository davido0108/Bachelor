package controller;

import Service.Service;
import controller.model.SpectacolModel;
import domain.Spectacol;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MenuController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView search_table;


    @FXML
    private TableColumn<Spectacol, String> artist;

    @FXML
    private TableColumn<Spectacol, String> date;

    @FXML
    private TableColumn<Spectacol, String> locatie;

    @FXML
    private TableColumn<Spectacol, Integer> nrloc_d;

    @FXML
    private TableColumn<Spectacol, Integer> nrloc_v;

    @FXML
    private TextField artist_search;

    ObservableList<Spectacol> spectacolObservable = FXCollections.observableArrayList();

    private Service srv;
    private Long id;

    private ArrayList<Spectacol> spectacole;


    public void initial(Service s){
        this.srv = s;


        artist.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Artist"));
        locatie.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Locatie"));
        date.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Date"));
        nrloc_d.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriDisponibile"));
        nrloc_v.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriVandute"));

        spectacolObservable.setAll(srv.getAllSpectacole());
        search_table.setItems(spectacolObservable);


    }

    public void search_by_Artist(MouseEvent actionEvent) {

        String artist = artist_search.getText();

        if(artist.equals(null))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("Pick a date...");
            alert.showAndWait();
        }
        else{
            spectacolObservable.setAll(srv.getSpectacolbyArtist(artist));
        }

        spectacolObservable.forEach(x->{
            System.out.println(x);
        });

//        showObservable.setAll();
    }

    public void buy_ticket(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Buy_Ticket.fxml"));
        Pane root;
        root = loader.load();
        Stage register_stage=new Stage();

        BuyController menu = loader.getController();
        menu.initial(srv);
        register_stage.setScene(new Scene(root));
        register_stage.show();
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();

    }
}
