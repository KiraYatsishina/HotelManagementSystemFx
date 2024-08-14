package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.ServiceOrderCellController;
import com.example.hotelmanagementsystemfx.Models.Entities.ServiceOrder;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ServiceOrderCellFactory extends ListCell<ServiceOrder> {
    @Override
    protected void updateItem(ServiceOrder serviceOrder, boolean empty) {
        super.updateItem(serviceOrder, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/ServiceOrderCell.fxml"));
            ServiceOrderCellController cellController = new ServiceOrderCellController(serviceOrder);
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
