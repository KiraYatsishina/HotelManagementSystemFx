package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Models.Entities.Room;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        pricePerNight_label.textProperty().bind(room.pricePerNightProperty().asString().concat("$"));
        Image doneImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/done.png"));
        Image noImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/no.png"));

        if(room.roomTypeProperty().get().equals("Standard")) fillstar(1);
        else if(room.roomTypeProperty().get().equals("Lux")) fillstar(2);
        else if(room.roomTypeProperty().get().equals("Improved")) fillstar(3);
        else if(room.roomTypeProperty().get().equals("Executive suite")) fillstar(4);
        else if(room.roomTypeProperty().get().equals("Apartments")) fillstar(5);

        refrigerator_label.textProperty().bind(room.hasRefrigeratorProperty().asString());
        if(refrigerator_label.getText().equals("1")) hasRefrigerator_image.setImage(doneImage);
        else hasRefrigerator_image.setImage(noImage);
        refrigerator_label.textProperty().unbind();
        refrigerator_label.setText("Refrigerator");

        conditioning_label.textProperty().bind(room.hasAirConditioningProperty().asString());
        if(conditioning_label.getText().equals("1")) hasAirConditioning_image.setImage(doneImage);
        else hasAirConditioning_image.setImage(noImage);
        conditioning_label.textProperty().unbind();
        conditioning_label.setText("Conditioning");

        status_label.textProperty().bind(Model.getInstance().getDatabaseHandler().getRoomDAO().getRoomStatus(room.idRoomProperty().get()));
        switch (status_label.getText()) {
            case "Available" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Occupied" -> status_circle.setStyle("-fx-fill: #708090;");
            case "Reserved" -> status_circle.setStyle("-fx-fill: #1E90FF;");}
    }
    private void fillstar(int countStars){
        Image goldStar = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/star.png"));
        Image blackStar = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/greyStar.png"));
        List<ImageView> stars = new ArrayList<>(List.of(star_1_image, star_2_image, star_3_image, star_4_image, star_5_image));
        for (int i = 0; i < stars.size(); i++) {
            if (i < countStars) stars.get(i).setImage(goldStar);
            else stars.get(i).setImage(blackStar);
        }
    }
}
