package controller;


import domain.MessageTask;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import services.MessageTaskService;
import utils.events.MessageTaskChangeEvent;
import utils.events.TaskStatusEvent;
import utils.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MessageTaskController implements Observer<MessageTaskChangeEvent> {
    MessageTaskService service;
    ObservableList<MessageTask> model = FXCollections.observableArrayList();


    @FXML
    TableView<MessageTask> tableView;
    @FXML
    TableColumn<MessageTask,String> tableColumnDesc;
    @FXML
    TableColumn<MessageTask,String> tableColumnFrom;
    @FXML
    TableColumn<MessageTask,String> tableColumnTo;
    @FXML
    TableColumn<MessageTask,String> tableColumnData;

    public void setMessageTaskService(MessageTaskService messageTaskService) {
        service = messageTaskService;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        tableColumnDesc.setCellValueFactory(new PropertyValueFactory<MessageTask,String>("description"));
        tableColumnFrom.setCellValueFactory(new PropertyValueFactory<MessageTask,String>("from"));
        tableColumnTo.setCellValueFactory(new PropertyValueFactory<MessageTask,String>("to"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<MessageTask,String>("date"));
        tableView.setItems(model);

    }

    private void initModel() {
        Iterable<MessageTask> messages;
        messages = service.getAll();
        List<MessageTask> messageTaskList = StreamSupport.stream(messages.spliterator(),false)
                .collect(Collectors.toList());
        model.setAll(messageTaskList);


    }



    public void handleDeleteMessage(ActionEvent actionEvent) {
        MessageTask selectedMessage = tableView.getSelectionModel().getSelectedItem();
        if(selectedMessage != null){
            MessageTask deletedMessage = service.deleteMessageTask(selectedMessage);
            if(deletedMessage != null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"Delete","Mesajul a fost sters");
            }
        }
        else{
            MessageAlert.showErrorMessage(null,"Trebuie selectat un mesaj");
        }
    }

    @Override
    public void update(MessageTaskChangeEvent messageTaskChangeEvent) {

        initModel();
    }

    @FXML
    public void handleUpdateMessage(ActionEvent ev) {
        MessageTask messageSelected = tableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void handleAddMessage(ActionEvent ev) {

        showMessageTaskEditDialog(null);
    }

    public void showMessageTaskEditDialog(MessageTask messageTask) {
        // TODO
    }



}
