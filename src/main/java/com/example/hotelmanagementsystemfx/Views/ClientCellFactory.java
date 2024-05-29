package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.ClientCellController;
import com.example.hotelmanagementsystemfx.Controllers.Manager.EmployeeCellController;
import com.example.hotelmanagementsystemfx.Models.Client;
import com.example.hotelmanagementsystemfx.Models.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/ClientCell.fxml"));
            ClientCellController cellController = new ClientCellController(client);
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
