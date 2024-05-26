package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private Label administratorRegisteredMostReservations_label;

    @FXML
    private Label clientMostCheckedIn_label;

    @FXML
    private Label countClients_label;

    @FXML
    private Label countEmployees_label;

    @FXML
    private Label countReservations_label;

    @FXML
    private Label countRooms_label;

    @FXML
    private Label countServiceOrders_label;

    @FXML
    private Label fullName_label;

    @FXML
    private Label maidMostServiceOrders_label;

    @FXML
    private Label reservationHighestPrice_label;

    @FXML
    private Label reservationLongestTenure_label;

    @FXML
    private Label roomMostOccupied_label;

    @FXML
    private Label serviceOrderMostOrdered_label;

    @FXML
    private Button update_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String firstName = Model.getInstance().getViewFactory().getEmployeeAccount().firstNameProperty().get();
        String lastName = Model.getInstance().getViewFactory().getEmployeeAccount().lastNameProperty().get();
        fullName_label.setText(firstName + " " + lastName);
        onUpdate();
        update_button.setOnAction(actionEvent ->onUpdate());
    }

    private void onUpdate() {
        countEmployees_label.setText(getCountOf(Const.EMPLOYEE_TABLE));
        countRooms_label.setText(getCountOf(Const.ROOM_TABLE));
        countServiceOrders_label.setText(getCountOf(Const.SERVICE_ORDER_TABLE));
        countClients_label.setText(getCountOf(Const.CLIENT_TABLE));
        countReservations_label.setText(getCountOf(Const.RESERVATION_TABLE));

    }
    private String getCountOf(String tableName){
        return Model.getInstance().getDatabaseHandler().getCount(tableName);
    }
}
