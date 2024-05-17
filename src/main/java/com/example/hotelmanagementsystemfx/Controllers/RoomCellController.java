package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Models.Employee;
import com.example.hotelmanagementsystemfx.Models.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomCellController implements Initializable {
    @FXML
    private Button edit_button;

    @FXML
    private ImageView hasAirConditioning_image;

    @FXML
    private ImageView hasRefrigerator_image;

    @FXML
    private Label pricePerNight_label;

    @FXML
    private Label roomNumber_label;

    @FXML
    private ImageView star_1_image;

    @FXML
    private ImageView star_2_image;

    @FXML
    private ImageView star_3_image;

    @FXML
    private ImageView star_4_image;

    @FXML
    private ImageView star_5_image;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_label;
    @FXML
    private Label refrigerator_label;
    @FXML
    private Label conditioning_label;

    private final Room room;
    public RoomCellController(Room room){
        this.room = room;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomNumber_label.textProperty().bind(room.roomNumberProperty());
        pricePerNight_label.textProperty().bind(room.pricePerNightProperty());
//        Image doneImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/done.png"));
//        Image noImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/no.png"));
//        refrigerator_label.textProperty().bind(room.hasRefrigeratorProperty());
//        if(refrigerator_label.getText().equals("0")) hasRefrigerator_image = new ImageView(doneImage);
//        else hasRefrigerator_image = new ImageView(noImage);

//        conditioning_label.textProperty().bind(room.hasAirConditioningProperty());
//        if(conditioning_label.getText().equals("1")) hasAirConditioning_image.setImage(new Image(getClass().getResourceAsStream("src/main/resources/com/example/hotelmanagementsystemfx/Images/done.png")));
//        else hasAirConditioning_image.setImage(new Image(getClass().getResourceAsStream("src/main/resources/com/example/hotelmanagementsystemfx/Images/no.png")));

        status_label.textProperty().bind(room.statusProperty());
        switch (status_label.getText()) {
            case "Available" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Occupied" -> status_circle.setStyle("-fx-fill: #708090;");
            case "Reserved" -> status_circle.setStyle("-fx-fill: #1E90FF;");}
    }
}
