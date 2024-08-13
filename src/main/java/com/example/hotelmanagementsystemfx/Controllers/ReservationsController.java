package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Entities.Reservation;
import com.example.hotelmanagementsystemfx.Views.ReservationCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationsController implements Initializable {
    @FXML
    private ChoiceBox<String> administrator_name_choiceBox;

    @FXML
    private DatePicker checkIn_datePicker;

    @FXML
    private DatePicker checkOut_datePicker;

    @FXML
    private ChoiceBox<String> clientName_choiceBox;

    @FXML
    private CheckBox completed_checkBox;

    @FXML
    private CheckBox invalid_checkBox;

    @FXML
    private CheckBox noShow_checkBox;

    @FXML
    private TextField numberOfGuests_textField;

    @FXML
    private CheckBox occupied_checkBox;
    @FXML
    private CheckBox cancelled_checkBox;

    @FXML
    private TextField priceFrom_textField;

    @FXML
    private TextField priceTo_textField;

    @FXML
    private DatePicker reservationDate_datePicker;

    @FXML
    private ListView<Reservation> reservations_listView;

    @FXML
    private CheckBox reserved_checkBox;

    @FXML
    private CheckBox reverseSort_checkBox;

    @FXML
    private ChoiceBox<String> roomNumber_choiceBox;

    @FXML
    private Button search_button;

    @FXML
    private ChoiceBox<String> sort_choiceBox;

    @FXML
    private Label error_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillChoiceBoxes();
        initData();
        reservations_listView.setItems(Model.getInstance().getReservations());
        reservations_listView.setCellFactory(e -> new ReservationCellFactory());
        search_button.setOnAction(actionEvent -> onReservationSearch());
    }

    private void onReservationSearch() {
        String sqlRequest = makeRequest();
        ObservableList<Reservation> searchResults = Model.getInstance().searchReservations(sqlRequest);
        reservations_listView.setItems(searchResults);
        reservations_listView.setCellFactory(e -> new ReservationCellFactory());
    }

    private String makeRequest() {
        error_label.setVisible(false);
        StringBuilder request = new StringBuilder("SELECT reservation.*, " +
                "CONCAT(employee.firstName, ' ', employee.lastName) AS administrator_name, " +
                "CONCAT(client.firstName, ' ', client.lastName) AS client_name, room.roomNumber" +
                " FROM reservation");
        request.append(" JOIN employee ON reservation.idEmployee = employee.idEmployee");
        request.append(" JOIN client ON reservation.idClient = client.idClient");
        request.append(" JOIN room ON reservation.idRoom = room.idRoom WHERE 1=1");

        boolean hasError = false;
        if (administrator_name_choiceBox.getValue() != null
                && !administrator_name_choiceBox.getValue().isEmpty()
                && !administrator_name_choiceBox.getValue().equals("All administrators")) {
            request.append(" AND CONCAT(employee.firstName, ' ', employee.lastName) = '").append(administrator_name_choiceBox.getValue()).append("'");
        }


        if (checkIn_datePicker.getValue() != null)
            request.append(" AND checkInDate >= '").append(checkIn_datePicker.getValue()).append("'");
        if (checkOut_datePicker.getValue() != null)
            request.append(" AND checkOutDate <= '").append(checkOut_datePicker.getValue()).append("'");

        if (clientName_choiceBox.getValue() != null
                && !clientName_choiceBox.getValue().isEmpty()
                && !clientName_choiceBox.getValue().equals("All clients")) {
            request.append(" AND CONCAT(client.firstName, ' ', client.lastName) = '").append(clientName_choiceBox.getValue()).append("'");
        }

        boolean statusAdded = false;

        if (completed_checkBox.isSelected()) {
            if (!statusAdded) {
                request.append(" AND (reservation.status = 'Completed'");
                statusAdded = true;
            } else request.append(" OR reservation.status = 'Completed'");
        }

        if (invalid_checkBox.isSelected()) {
            if (!statusAdded) {
                request.append(" AND (reservation.status = 'Invalid'");
                statusAdded = true;
            } else request.append(" OR reservation.status = 'Invalid'");
        }

        if (noShow_checkBox.isSelected()) {
            if (!statusAdded) {
                request.append(" AND (reservation.status = 'No-Show'");
                statusAdded = true;
            } else request.append(" OR reservation.status = 'No-Show'");
        }

        if (occupied_checkBox.isSelected()) {
            if (!statusAdded) {
                request.append(" AND (reservation.status = 'Occupied'");
                statusAdded = true;
            } else request.append(" OR reservation.status = 'Occupied'");
        }

        if (cancelled_checkBox.isSelected()) {
            if (!statusAdded) {
                request.append(" AND (reservation.status = 'Cancelled'");
                statusAdded = true;
            } else
                request.append(" OR reservation.status = 'Cancelled'");
        }

        if (reserved_checkBox.isSelected()) {
            if (!statusAdded) {
                request.append(" AND (reservation.status = 'Reserved'");
                statusAdded = true;
            } else request.append(" OR reservation.status = 'Reserved'");
        }
        if (statusAdded) request.append(")");


        if (!numberOfGuests_textField.getText().isEmpty()) {
            try {
                int numberOfGuests = Integer.parseInt(numberOfGuests_textField.getText());
                request.append(" AND numberOfGuests = ").append(numberOfGuests);
                if(numberOfGuests <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                new Shake(numberOfGuests_textField).playAnim();
                hasError = true;
            }
        }

        double priceFrom = 0;
        if (!priceFrom_textField.getText().isEmpty()) {
            try {
                priceFrom = Double.parseDouble(priceFrom_textField.getText());
                request.append(" AND price >= ").append(priceFrom);
            } catch (NumberFormatException e) {
                new Shake(priceFrom_textField).playAnim();
                hasError = true;
            }
        }

        double priceTo = 0;
        if (!priceTo_textField.getText().isEmpty()) {
            try {
                priceTo = Double.parseDouble(priceTo_textField.getText());
                request.append(" AND price <= ").append(priceTo);
            } catch (NumberFormatException e) {
                new Shake(priceTo_textField).playAnim();
                hasError = true;
            }
        }

        if(!priceFrom_textField.getText().isEmpty()
                && !priceTo_textField.getText().isEmpty()
                && priceTo < priceFrom){
            new Shake(priceFrom_textField).playAnim();
            new Shake(priceTo_textField).playAnim();
            hasError = true;
        }

        if (reservationDate_datePicker.getValue() != null)
            request.append(" AND reservationDate = '").append(reservationDate_datePicker.getValue()).append("'");

        if (roomNumber_choiceBox.getValue() != null && !roomNumber_choiceBox.getValue().isEmpty() && !roomNumber_choiceBox.getValue().equals("All room numbers"))
            request.append(" AND room.roomNumber = '").append(roomNumber_choiceBox.getValue()).append("'");


        if (sort_choiceBox.getValue() != null && !sort_choiceBox.getValue().isEmpty()) {
            String sortOrder = reverseSort_checkBox.isSelected() ? " DESC" : " ASC";
            request.append(" ORDER BY ");

            switch (sort_choiceBox.getValue()) {
                case "Price" -> request.append("price").append(sortOrder);
                case "Reservation date" -> request.append("reservationDate").append(sortOrder);
                case "Tenure" -> request.append("DATEDIFF(checkOutDate, checkInDate)").append(sortOrder);
                case "Number of guests" -> request.append("numberOfGuests").append(sortOrder);
            }
        }
        if(hasError){
            error_label.setVisible(true);
            return "SELECT * FROM reservation WHERE 1=0;";
        }
        return request.toString();
    }
    private void initData () {
        if (Model.getInstance().getReservations().isEmpty()) {
            Model.getInstance().setReservations();
        }
    }
    private void fillChoiceBoxes () {
        sort_choiceBox.getItems().addAll("Price", "Reservation date", "Tenure", "Number of guests");
        ResultSet administrators = Model.getInstance().getDatabaseHandler().getAdministratorNames();
        administrator_name_choiceBox.getItems().add("All administrators");
        try {
            while (administrators.next()) {
                String fullName = administrators.getString("fullName");
                administrator_name_choiceBox.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        administrator_name_choiceBox.setValue("All administrators");

        ResultSet clients = Model.getInstance().getDatabaseHandler().getAllClientsData();
        clientName_choiceBox.getItems().add("All clients");
        try {
            while (clients.next()) {
                String fullName = clients.getString("firstName") + " " + clients.getString("lastName");
                clientName_choiceBox.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clientName_choiceBox.setValue("All clients");

        ResultSet roomsNumber = Model.getInstance().getDatabaseHandler().getAllRoomsData();
        roomNumber_choiceBox.getItems().add("All room numbers");
        try {
            while (roomsNumber.next()) {
                String roomNumber = roomsNumber.getString("roomNumber");
                roomNumber_choiceBox.getItems().add(roomNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomNumber_choiceBox.setValue("All room numbers");
    }
}