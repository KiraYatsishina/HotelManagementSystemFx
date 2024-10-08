package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.Manager.ServiceOrdersTypeCellController;
import com.example.hotelmanagementsystemfx.Models.Entities.ServiceType;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ServiceOrdersTypeCellFactory extends ListCell<ServiceType> {
    @Override
    protected void updateItem(ServiceType ordersType, boolean empty) {
        super.updateItem(ordersType, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/ServiceOrdersTypeCell.fxml"));
            ServiceOrdersTypeCellController cellController = new ServiceOrdersTypeCellController(ordersType);
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
