package com.example.hotelmanagementsystemfx.Controllers.Administrator;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Models.Reservation;
import com.example.hotelmanagementsystemfx.Models.Room;
import com.example.hotelmanagementsystemfx.Views.ReservationCellFactory;
import com.example.hotelmanagementsystemfx.Views.RoomCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        fillChoiceBoxes();
        search_button.setOnAction(actionEvent -> onSearchButton());
    }

    private void onSearchButton() {
        String sqlRequest = makeRequest();
        ObservableList<Room> searchResults = Model.getInstance().sortRooms(sqlRequest);
        if(searchResults.isEmpty()) {
            searchError_label.setText("* No such rooms were found");
            rooms_listView.setVisible(false);
            first_line.setVisible(false);
            second_line.setVisible(false);
        }
        else{
            rooms_listView.setItems(searchResults);
            rooms_listView.setCellFactory(e -> new RoomCellFactory());
            rooms_listView.setVisible(true);
            first_line.setVisible(true);
            second_line.setVisible(true);

        }

    }

    private String makeRequest() {
        StringBuilder request = new StringBuilder("SELECT * FROM room WHERE 1=1");

        if (!roomType_choiceBox.getValue().equals("All room types")) {
            request.append(" AND roomType = '").append(roomType_choiceBox.getValue()).append("'");
        }

        if (!numberOfGuests_choiceBox.getValue().equals("All number of guests")) {
            request.append(" AND capacity = ").append(numberOfGuests_choiceBox.getValue());
        }

        if (checkIn_datePicker.getValue() != null && checkOut_datePicker.getValue() != null) {
            LocalDate checkInDate = checkIn_datePicker.getValue();
            LocalDate checkOutDate = checkOut_datePicker.getValue();
        }
        // и комната должна быть свободна
        return request.toString();
    }
    private void initData () {
        if (Model.getInstance().getRooms().isEmpty()) {
            Model.getInstance().setRooms();
        }
    }
    private void fillChoiceBoxes () {

        ResultSet roomTypes = Model.getInstance().getDatabaseHandler().getAllRoomTypes();
        roomType_choiceBox.getItems().add("All room types");
        try {
            while (roomTypes.next()) {
                String fullName = roomTypes.getString("roomType");
                roomType_choiceBox.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomType_choiceBox.setValue("All room types");

        ResultSet numberOfGuests = Model.getInstance().getDatabaseHandler().getAllNumberOfGuests();
        numberOfGuests_choiceBox.getItems().add("All number of guests");
        try {
            while (numberOfGuests.next()) {
                String fullName = numberOfGuests.getString("capacity");
                numberOfGuests_choiceBox.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        numberOfGuests_choiceBox.setValue("All number of guests");
    }
}
