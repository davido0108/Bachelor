package controller;

import Services.IObserver;
import Services.IService;
import Services.ProjectException;
import chat.domain.Bilet;
import chat.domain.BuyTicket;
import chat.domain.Spectacol;
import javafx.application.Platform;
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
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BuyController extends UnicastRemoteObject implements IObserver, Serializable {
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


    private IService srv;
    private MenuController parent;

    public BuyController() throws RemoteException {
    }

    public void initial(IService s, MenuController par) throws ProjectException, RemoteException {
        this.srv = s;
        this.parent = par;

        artist.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Artist"));
        locatie.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Locatie"));
        date.setCellValueFactory(new PropertyValueFactory<Spectacol, String>("Date"));
        nrloc_d.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriDisponibile"));
        nrloc_v.setCellValueFactory(new PropertyValueFactory<Spectacol, Integer>("LocuriVandute"));

        spectacolObservable.setAll(srv.AllSpectacole());
        search_table.setItems(spectacolObservable);

    }

    public void spectacole_disponibile(MouseEvent mouseEvent) throws IOException, ProjectException,RemoteException {

        spectacolObservable.setAll(srv.AllSpectacole());
    }

    public void buy_ticket(MouseEvent mouseEvent) throws ProjectException,RemoteException{
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

        BuyTicket buyTicket = new BuyTicket(spectacol.getId(),bilet);

        srv.buyTicket(buyTicket.getId(),buyTicket.getBilet());
        System.out.println("Biletul: " + bilet + "a fost adaugat");

        spectacolObservable.setAll(srv.AllSpectacole());



    }
    @Override
    public void menuUpdate(Spectacol show) throws RemoteException{



         System.out.println("esd");
        Platform.runLater(() -> {
           System.out.println("try to updateSSSS");

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
