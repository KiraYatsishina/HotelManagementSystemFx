package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Entities.Client;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.ClientCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    @FXML
    private DatePicker checkInDate_datePicker;

    @FXML
    private DatePicker checkOutDate_datePicker;

    @FXML
    private TextField firstName_textField;

    @FXML
    private TextField lastName_textField;

    @FXML
    private RadioButton man_radioButton;

    @FXML
    private TextField phoneNumber_textField;

    @FXML
    private Button search_button;

    @FXML
    private ToggleGroup sex;

    @FXML
    private RadioButton woman_radioButton;

    @FXML
    private ListView<Client> clients_listView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        clients_listView.setItems(Model.getInstance().getClients());
        clients_listView.setCellFactory(e -> new ClientCellFactory());
        search_button.setOnAction(actionEvent -> onClientSearch());

    }
    private void onClientSearch(){
        String sqlRequest = makeRequest();
        ObservableList<Client> searchResults = Model.getInstance().searchClients(sqlRequest);
        clients_listView.setItems(searchResults);
        clients_listView.setCellFactory(e -> new ClientCellFactory());
    }
    private String makeRequest() {
        StringBuilder request = new StringBuilder("SELECT distinct client.* FROM client " +
                "JOIN reservation ON client.idClient = reservation.idClient WHERE 1=1");

        if (!firstName_textField.getText().isEmpty())
            request.append(" AND client.firstName LIKE '%").append(firstName_textField.getText()).append("%'");
        if (!lastName_textField.getText().isEmpty())
            request.append(" AND client.lastName LIKE '%").append(lastName_textField.getText()).append("%'");
        if (!phoneNumber_textField.getText().isEmpty())
            request.append(" AND client.phoneNumber LIKE '%").append(phoneNumber_textField.getText()).append("%'");

        if (man_radioButton.isSelected())
            request.append(" AND client.gender = 'Male'");
        else if (woman_radioButton.isSelected())
            request.append(" AND client.gender = 'Female'");

        if (checkInDate_datePicker.getValue() != null)
            request.append(" AND reservation.checkInDate >= '").append(checkInDate_datePicker.getValue()).append("'");
        if (checkOutDate_datePicker.getValue() != null)
            request.append(" AND reservation.checkOutDate <= '").append(checkOutDate_datePicker.getValue()).append("'");



        return request.toString();
    }

    private void initData(){
        if(Model.getInstance().getClients().isEmpty()){
            Model.getInstance().setClients();
        }
    }
}

