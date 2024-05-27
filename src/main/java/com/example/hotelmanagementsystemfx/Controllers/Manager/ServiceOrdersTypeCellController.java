package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.ServiceOrdersType;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiceOrdersTypeCellController implements Initializable {
    @FXML
    private ImageView account_image;

    @FXML
    private Button edit_button;

    @FXML
    private Label name_label;

    @FXML
    private Label orderCount_label;

    @FXML
    private Label price_label;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_label;
    private final ServiceOrdersType serviceOrdersType;
    public ServiceOrdersTypeCellController(ServiceOrdersType serviceOrdersType){
        this.serviceOrdersType = serviceOrdersType;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name_label.textProperty().bind(serviceOrdersType.nameProperty());
        price_label.textProperty().bind(serviceOrdersType.priceProperty().asString().concat("$"));
        orderCount_label.textProperty().bind(serviceOrdersType.orderCountProperty().asString().concat(" times"));
        status_label.textProperty().bind(serviceOrdersType.statusProperty());
        switch (status_label.getText()) {
            case "Active" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Disabled" -> status_circle.setStyle("-fx-fill: #708090;");
        }
    }
}
