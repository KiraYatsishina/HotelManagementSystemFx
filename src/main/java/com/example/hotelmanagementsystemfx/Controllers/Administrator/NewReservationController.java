package com.example.hotelmanagementsystemfx.Controllers.Administrator;

import com.example.hotelmanagementsystemfx.Models.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class NewReservationController implements Initializable {

    @FXML
    private Label change_label;

    @FXML
    private DatePicker checkIn_datePicker;

    @FXML
    private DatePicker checkOut_datePicker;

    @FXML
    private Label clientError_label;

    @FXML
    private TextField depositedAmount_textField;

    @FXML
    private TextField firstName_textField;

    @FXML
    private Line first_line;

    @FXML
    private TextField lastName_textField;

    @FXML
    private ChoiceBox<String> numberOfGuests_choiceBox;

    @FXML
    private TextField phoneNumber_textField;

    @FXML
    private Label price_label;

    @FXML
    private ChoiceBox<String> roomType_choiceBox;

    @FXML
    private ListView<Room> rooms_listView;

    @FXML
    private Label saveError_label;

    @FXML
    private VBox save_VB;

    @FXML
    private Button save_button;

    @FXML
    private Label searchError_label;

    @FXML
    private Button search_button;

    @FXML
    private Line second_line;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
