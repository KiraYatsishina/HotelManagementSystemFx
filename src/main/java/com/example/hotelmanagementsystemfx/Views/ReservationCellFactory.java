package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.ReservationCellController;
import com.example.hotelmanagementsystemfx.Entities.Reservation;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ReservationCellFactory extends ListCell<Reservation> {
    @Override
    protected void updateItem(Reservation reservation, boolean empty) {
        super.updateItem(reservation, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/ReservationCell.fxml"));
            ReservationCellController cellController = new ReservationCellController(reservation);
            loader.setController(cellController);
            getStyleClass().add("loader");
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}