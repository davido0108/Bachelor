package controller;

import Service.Service;
import domain.Bilet;
import domain.Spectacol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public class BuyController {
    @FXML
    private TableView<Spectacol> search_table;

    @FXML
    private TextField nrloc_dorite;

    @FXML
    private TextField nume;

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

    ObservableList<Spectacol> spectacolObservable = FXCollections.observableArrayList();


    private Service srv;

    public void initial(Service s) {
        this.srv = s;
        artist.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Artist"));
        locatie.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Locatie"));
        date.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Date"));
        nrloc_d.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriDisponibile"));
        nrloc_v.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriVandute"));

        spectacolObservable.setAll(srv.getAllSpectacole());
        search_table.setItems(spectacolObservable);



    }

    public void spectacole_disponibile(MouseEvent mouseEvent) throws IOException {

        spectacolObservable.setAll(srv.getAllSpectacole());
    }

    public void buy_ticket(MouseEvent mouseEvent){
        String nr = nrloc_dorite.getText();
        String nume_cumparator = nume.getText();

        System.out.println("Nr. locuri dorite: " + nr );

        if(nr.isEmpty() || nume_cumparator.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setContentText("add a number...");
            alert.showAndWait();
        }

        Spectacol spectacol = search_table.getSelectionModel().getSelectedItem();
        System.out.println("Show selectat: " + spectacol);

        Bilet bilet = new Bilet(nume_cumparator, Integer.parseInt(nr));
        srv.buyTicket(spectacol.getId(),bilet);
        System.out.println("Biletul: " + bilet + "a fost adaugat");

        spectacolObservable.setAll(srv.getAllSpectacole());


    }

}
