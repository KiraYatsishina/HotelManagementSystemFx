package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Entities.ServiceOrder;
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

public class ServiceOrderCellController implements Initializable {
    @FXML
    private ImageView account_image;

    @FXML
    private Label clientFullName_label;

    @FXML
    private Button edit_button;

    @FXML
    private Label orderDate_label;

    @FXML
    private Label price_label;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_label;
    private ServiceOrder serviceOrder;

    public ServiceOrderCellController(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet client = Model.getInstance().getDatabaseHandler().getClientById(serviceOrder.idClientProperty().get());
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
        double price = Model.getInstance().getDatabaseHandler().getPriceServiceOrderById(serviceOrder.idServiceOrderProperty().get());
        price_label.setText(price + " $");
        orderDate_label.textProperty().bind(serviceOrder.orderDateProperty());
        String status = Model.getInstance().getDatabaseHandler().getStatusServiceOrderById(serviceOrder.idServiceOrderProperty().get());
        status_label.setText(status);
        switch (status_label.getText()) {
            case "Complete" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Cancelled" -> status_circle.setStyle("-fx-fill: #708090;");
            case "Pending" -> status_circle.setStyle("-fx-fill: #1E90FF;");
            case "In process" -> status_circle.setStyle("-fx-fill: #FFFF00;");}
    }
}
