package com.example.hotelmanagementsystemfx.Controllers.Administrator;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Entities.Client;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Models.Entities.Reservation;
import com.example.hotelmanagementsystemfx.Models.Entities.Room;
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
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    @FXML
    private Label period_label;
    @FXML
    private Label tenure_label;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton man_radioButton;
    @FXML
    private RadioButton women_radioButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //fillChoiceBoxes();
        //search_button.setOnAction(actionEvent -> onSearchButton());

        rooms_listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                boolean hasError = false;
                if(checkIn_datePicker.getValue() == null){
                    hasError = true;
                    new Shake(checkIn_datePicker).playAnim();
                }
                if(checkOut_datePicker.getValue() == null){
                    hasError = true;
                    new Shake(checkOut_datePicker).playAnim();
                }
                if(!hasError && checkOut_datePicker.getValue().isBefore(checkIn_datePicker.getValue())){
                    hasError = true;
                    new Shake(checkIn_datePicker).playAnim();
                    new Shake(checkOut_datePicker).playAnim();
                }

                if(!hasError) {
                    save_VB.setVisible(true);
                    LocalDate checkInDate = checkIn_datePicker.getValue();
                    LocalDate checkOutDate = checkOut_datePicker.getValue();

                    period_label.setText(checkInDate + "   " + checkOutDate);
                    long numberOfDays = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
                    tenure_label.setText(String.valueOf(numberOfDays) + " days");
                    double price = newValue.pricePerNightProperty().getValue() * numberOfDays;
                    price_label.setText(String.valueOf(price));

                }
            }
            //save_button.setOnAction(actionEvent -> onSaveButton(newValue));
        });
    }

//    private void onSaveButton(Room room) {
//        boolean hasError = false;
//        Double depositedAmount = 0.0;
//        try{
//            depositedAmount = Double.parseDouble(depositedAmount_textField.getText());
//            if(depositedAmount <=0) throw new NumberFormatException();
//        }catch (NumberFormatException e){
//            hasError = true;
//            new Shake(depositedAmount_textField).playAnim();
//        }
//        int numberOfGuests = 0;
//        if(numberOfGuests_choiceBox.getValue().equals("All number of guests")){
//            hasError = true;
//            new Shake(numberOfGuests_choiceBox).playAnim();
//        }else numberOfGuests = Integer.valueOf(numberOfGuests_choiceBox.getValue());
//
//        Double price = Double.parseDouble(price_label.getText());
//        Double change = depositedAmount - price;
//
//        if(change < 0) {
//            hasError = true;
//            new Shake(depositedAmount_textField).playAnim();
//        }
//        if(!hasError) change_label.setText(String.valueOf(change) + " $");
//        if(firstName_textField.getText().isEmpty()){
//            hasError = true;
//            new Shake(firstName_textField).playAnim();
//        }
//        if(lastName_textField.getText().isEmpty()){
//            hasError = true;
//            new Shake(lastName_textField).playAnim();
//        }
//        String phonePattern = "^0\\d{9}$";
//        String phoneNumber = phoneNumber_textField.getText();
//        if(phoneNumber.isEmpty()) {
//            new Shake(phoneNumber_textField).playAnim();
//            hasError = true;
//        }else if(!Pattern.compile(phonePattern).matcher(phoneNumber).matches()){
//            new Shake(phoneNumber_textField).playAnim();
//            clientError_label.setText("* Incorrect format.");
//            hasError = true;
//        }
//        String gender = null;
//        if(women_radioButton.isSelected())gender = "Female";
//        else if(man_radioButton.isSelected())gender = "Male";
//        else {
//            hasError = true;
//            new Shake(women_radioButton).playAnim();
//            new Shake(man_radioButton).playAnim();
//        }
//
//        if(!hasError){
//            Client client = new Client("", firstName_textField.getText(), lastName_textField.getText(), phoneNumber, gender);
//            String clientId = null;
//            if(Model.getInstance().getDatabaseHandler().existClient(client) != null) {
//                clientId = Model.getInstance().getDatabaseHandler().existClient(client);
//            }else{
//                clientId = Model.getInstance().getDatabaseHandler().createClient(client);
//            }
//            String idRoom = room.idRoomProperty().get();
//            String idEmployee = Model.getInstance().getViewFactory().getEmployeeAccount().idEmployeeProperty().get();
//            LocalDate today = LocalDate.now();
//
//            String checkIn = String.valueOf(checkIn_datePicker.getValue());
//            String checkOut = String.valueOf(checkOut_datePicker.getValue());
//            String status = null;
//            if(checkIn.equals(today.toString()))  status = "Occupied";
//            else status = "Pre-booked";
//            Reservation reservation = new Reservation("", clientId, idRoom,
//                    idEmployee, numberOfGuests, today.toString(), checkIn, checkOut, price, status);
//            Model.getInstance().getDatabaseHandler().createReservation(reservation);
//            saveError_label.setText("The new reservation has been saved ✔");
//        }else{
//            saveError_label.setText("The new reservation has not been saved ❌");
//        }
//    }

//    private void onSearchButton() {
//        String sqlRequest = makeRequest();
//
//        ObservableList<Room> searchResults = Model.getInstance().sortRooms(sqlRequest);
//        if(searchResults.isEmpty()) {
//            searchError_label.setText("* No such rooms were found");
//            rooms_listView.setVisible(false);
//            first_line.setVisible(false);
//            second_line.setVisible(false);
//            save_VB.setVisible(false);
//        }
//        else{
//            searchError_label.setText("");
//            rooms_listView.setItems(searchResults);
//            rooms_listView.setCellFactory(e -> new RoomCellFactory());
//            rooms_listView.setVisible(true);
//            first_line.setVisible(true);
//            second_line.setVisible(true);
//        }
//    }

    private String makeRequest() {
        StringBuilder request = new StringBuilder("SELECT * FROM room WHERE 1=1");

        if (!roomType_choiceBox.getValue().equals("All room types"))
            request.append(" AND roomType = '").append(roomType_choiceBox.getValue()).append("'");
        if (!numberOfGuests_choiceBox.getValue().equals("All number of guests"))
            request.append(" AND capacity = ").append(numberOfGuests_choiceBox.getValue());
        LocalDate today = LocalDate.now();
        if(checkIn_datePicker.getValue() != null && checkIn_datePicker.getValue().isBefore(today)){
            new Shake(checkIn_datePicker).playAnim();
            return "SELECT * FROM room WHERE 1=0";
        }
        if (checkIn_datePicker.getValue() != null && checkOut_datePicker.getValue() != null) {
            LocalDate checkInDate = checkIn_datePicker.getValue();
            LocalDate checkOutDate = checkOut_datePicker.getValue();
            if(checkOutDate.isBefore(checkInDate)){
                new Shake(checkIn_datePicker).playAnim();
                new Shake(checkOut_datePicker).playAnim();
                return "SELECT * FROM room WHERE 1=0";
            }

            request.append(" AND idRoom NOT IN (");
            request.append("SELECT idRoom FROM reservation WHERE ");
            request.append("NOT (checkOutDate <= '").append(checkInDate).append("' ");
            request.append("OR checkInDate >= '").append(checkOutDate).append("')");
            request.append(")");
        }
        return request.toString();
    }

//    private void fillChoiceBoxes () {
//
//        ResultSet roomTypes = Model.getInstance().getDatabaseHandler().getAllRoomTypes();
//        roomType_choiceBox.getItems().add("All room types");
//        try {
//            while (roomTypes.next()) {
//                String fullName = roomTypes.getString("roomType");
//                roomType_choiceBox.getItems().add(fullName);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        roomType_choiceBox.setValue("All room types");
//
//        ResultSet numberOfGuests = Model.getInstance().getDatabaseHandler().getAllNumberOfGuests();
//        numberOfGuests_choiceBox.getItems().add("All number of guests");
//        try {
//            while (numberOfGuests.next()) {
//                String fullName = numberOfGuests.getString("capacity");
//                numberOfGuests_choiceBox.getItems().add(fullName);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        numberOfGuests_choiceBox.setValue("All number of guests");
//    }
}
