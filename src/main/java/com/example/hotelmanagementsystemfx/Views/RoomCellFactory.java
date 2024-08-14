package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.RoomCellController;
import com.example.hotelmanagementsystemfx.Models.Entities.Room;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class RoomCellFactory extends ListCell<Room> {
    @Override
    protected void updateItem(Room room, boolean empty) {
        super.updateItem(room, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/RoomCell.fxml"));
            RoomCellController cellController = new RoomCellController(room);
            loader.setController(cellController);
            getStyleClass().add("loader");
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
