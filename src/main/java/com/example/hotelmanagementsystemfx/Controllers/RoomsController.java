package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Models.Room;
import com.example.hotelmanagementsystemfx.Views.RoomCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {

    @FXML
    private CheckBox reverseSort_checkBox;

    @FXML
    private ListView<Room> rooms_listView;

    @FXML
    private Button sort_button;

    @FXML
    private ChoiceBox<String> sort_choiceBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort_choiceBox.getItems().addAll("Price", "Capacity", "Floor", "Room type");
        initData();
        rooms_listView.setItems(Model.getInstance().getRooms());
        rooms_listView.setCellFactory(e -> new RoomCellFactory());
        sort_button.setOnAction(actionEvent -> onRoomSearch());
    }

    private void onRoomSearch() {
        String sqlRequest = makeRequest();
        ObservableList<Room> searchResults = Model.getInstance().sortRooms(sqlRequest);
        rooms_listView.setItems(searchResults);
        rooms_listView.setCellFactory(e -> new RoomCellFactory());

    }

    private String makeRequest() {
        String selectedSort = sort_choiceBox.getValue();
        if (selectedSort == null) {
            return "SELECT * FROM room";
        }
        boolean reverseSort = reverseSort_checkBox.isSelected();
        String order = reverseSort ? "DESC" : "ASC";

        String sortBy;
        switch (selectedSort) {
            case "Price" -> sortBy = "pricePerNight";
            case "Capacity" -> sortBy = "capacity";
            case "Floor" -> sortBy = "floor";
            case "Room type" -> sortBy = "roomType";
            default -> sortBy = "price";
        }

        return "SELECT * FROM room ORDER BY " + sortBy + " " + order;
    }

    private void initData(){
        if(Model.getInstance().getRooms().isEmpty()){
            Model.getInstance().setRooms();
        }
    }
}
