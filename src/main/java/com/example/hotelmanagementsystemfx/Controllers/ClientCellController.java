package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Entities.Client;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    @FXML
    private Label countReservations_label;

    @FXML
    private Label countServiceOrders_label;

    @FXML
    private Button edit_button;

    @FXML
    private Label fullName_label;

    @FXML
    private ImageView gender_image;

    @FXML
    private Label phoneNumber_label;

    private Client client;
    public ClientCellController(Client client){
        this.client = client;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image femaleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/femaleAcc.png"));
        Image maleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/maleAcc.png"));
        if(client.genderProperty().get().equals("Female")) gender_image.setImage(femaleImage);
        else if(client.genderProperty().get().equals("Male")) gender_image.setImage(maleImage);
        fullName_label.textProperty().bind(Bindings.concat(client.lastNameProperty(), " ", client.firstNameProperty()));
        phoneNumber_label.textProperty().bind(client.phoneNumberProperty());
        int countReserv = Model.getInstance().getDatabaseHandler().countReservationsOfClient(client.phoneNumberProperty().get());
        countReservations_label.setText(String.valueOf(countReserv));
        int countServiceOrders = Model.getInstance().getDatabaseHandler().countServiceOrdersOfClient(client.phoneNumberProperty().get());
        countServiceOrders_label.setText(String.valueOf(countServiceOrders));
    }
}
