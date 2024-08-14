package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private Label administratorRegisteredMostReservationsCount_label;

    @FXML
    private Label administratorRegisteredMostReservations_label;

    @FXML
    private Label clientMostCheckedInCount_label;

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
    private Label maidMostServiceOrdersCount_label;

    @FXML
    private Label maidMostServiceOrders_label;

    @FXML
    private Label reservationHighestPrice_label;

    @FXML
    private Label countDaysReservationLongestTenure_label;


    @FXML
    private Label roomMostOccupiedCount_label;

    @FXML
    private Label roomMostOccupied_label;

    @FXML
    private Label serviceOrderMostOrderedCount_label;

    @FXML
    private Label serviceOrderMostOrdered_label;

    @FXML
    private Button update_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String firstName = Model.getInstance().getViewFactory().getEmployeeAccount().firstNameProperty().get();
        String lastName = Model.getInstance().getViewFactory().getEmployeeAccount().lastNameProperty().get();
        fullName_label.setText(firstName + " " + lastName);
        //onUpdate();
        //update_button.setOnAction(actionEvent ->onUpdate());
    }

//    private void onUpdate() {
//        countEmployees_label.setText(getCountOf(Const.EMPLOYEE_TABLE));
//        countRooms_label.setText(getCountOf(Const.ROOM_TABLE));
//        countServiceOrders_label.setText(getCountOf(Const.SERVICE_ORDER_TABLE));
//        countClients_label.setText(getCountOf(Const.CLIENT_TABLE));
//        countReservations_label.setText(getCountOf(Const.RESERVATION_TABLE));
//        try {
//            ResultSet roomMostOccupied = Model.getInstance().getDatabaseHandler().getRoomMostOccupied();
//            if (roomMostOccupied.next()) {
//                roomMostOccupied_label.setText(roomMostOccupied.getString(1));
//                roomMostOccupiedCount_label.setText("(" + roomMostOccupied.getString(2) + ")");
//            }
//            ResultSet clientMostCheckedIn = Model.getInstance().getDatabaseHandler().getClientMostCheckedIn();
//            if (clientMostCheckedIn.next()) {
//                String fName = clientMostCheckedIn.getString(2);
//                String lName = clientMostCheckedIn.getString(3);
//                clientMostCheckedIn_label.setText(fName + " " + lName);
//                clientMostCheckedInCount_label.setText("(" + clientMostCheckedIn.getString(4) + ")");
//            }
//            ResultSet serviceOrderMostOrdered = Model.getInstance().getDatabaseHandler().getServiceOrderOrderedMost();
//            if (serviceOrderMostOrdered.next()) {
//                serviceOrderMostOrdered_label.setText(serviceOrderMostOrdered.getString(1));
//                serviceOrderMostOrderedCount_label.setText("(" + serviceOrderMostOrdered.getString(2) + ")");
//            }
//            ResultSet reservationHighestPrice = Model.getInstance().getDatabaseHandler().getReservationHifhestPrice();
//            if (reservationHighestPrice.next())
//                reservationHighestPrice_label.setText( reservationHighestPrice.getString(2));
//            ResultSet reservationLongestTenure = Model.getInstance().getDatabaseHandler().getReservationLongestTenure();
//            if (reservationLongestTenure.next())
//                countDaysReservationLongestTenure_label.setText(reservationLongestTenure.getString(2) +  " days");
//            ResultSet maidMostServiceOrders = Model.getInstance().getDatabaseHandler().getMaidMostserviceOrders();
//            if (maidMostServiceOrders.next()) {
//                String fName = maidMostServiceOrders.getString(2);
//                String lName = maidMostServiceOrders.getString(3);
//                maidMostServiceOrders_label.setText(fName + " " + lName);
//                maidMostServiceOrdersCount_label.setText("(" + maidMostServiceOrders.getString(4) + ")");
//            }
//            ResultSet administratorRegisteredMostReservations = Model.getInstance().getDatabaseHandler().getAdministratorMostReservations();
//            if (administratorRegisteredMostReservations.next()) {
//                String fName = administratorRegisteredMostReservations.getString(2);
//                String lName = administratorRegisteredMostReservations.getString(3);
//                administratorRegisteredMostReservations_label.setText(fName + " " + lName);
//                administratorRegisteredMostReservationsCount_label.setText("(" + administratorRegisteredMostReservations.getString(4) + ")");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private String getCountOf(String tableName){
//        return Model.getInstance().getDatabaseHandler().getCount(tableName);
//    }
}
