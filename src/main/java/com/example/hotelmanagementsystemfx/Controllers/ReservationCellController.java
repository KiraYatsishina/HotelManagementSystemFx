package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Entities.Reservation;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationCellController implements Initializable {
    @FXML
    private ImageView account_image;

    @FXML
    private Label checkInDate_label;

    @FXML
    private Label checkOutDate_label;

    @FXML
    private Label clientFullName_label;

    @FXML
    private Button edit_button;

    @FXML
    private Label price_label;

    @FXML
    private Label reservationDate_label;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_label;

    @FXML
    private Label tenure_label;
    private Reservation reservation;
    public ReservationCellController(Reservation reservation){
        this.reservation = reservation;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet client = Model.getInstance().getDatabaseHandler().getClientById(reservation.idClientProperty().get());
        String firstName = null;
        String lastName = null;
        try {
            if(client.next()){
                firstName = client.getString(Const.CLIENT_FIRSTNAME);
                lastName = client.getString(Const.CLIENT_LASTNAME);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clientFullName_label.textProperty().bind(Bindings.concat(firstName + " " + lastName));
        reservationDate_label.textProperty().bind(reservation.reservationDateProperty());
        checkInDate_label.textProperty().bind(reservation.checkInDateProperty());
        checkOutDate_label.textProperty().bind(reservation.checkOutDateProperty());
        tenure_label.textProperty().bind(reservation.tenureProperty().asString().concat(" days"));
        price_label.textProperty().bind(reservation.priceProperty().asString().concat("$"));
        status_label.textProperty().bind(reservation.statusProperty());
        switch (status_label.getText()) {
            case "Completed" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Cancelled" -> status_circle.setStyle("-fx-fill: #708090;");
            case "No-show" -> status_circle.setStyle("-fx-fill: #1E90FF;");
            case "Invalid" -> status_circle.setStyle("-fx-fill: #FFFF00;");
            case "Occupied" -> status_circle.setStyle("-fx-fill: #800080;");
            case "Reserved" -> status_circle.setStyle("-fx-fill: #153543;");}
    }
}
