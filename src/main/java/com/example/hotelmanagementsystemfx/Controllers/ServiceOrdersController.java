package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Models.Entities.ServiceOrder;
import com.example.hotelmanagementsystemfx.Views.ServiceOrderCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiceOrdersController implements Initializable {

    @FXML
    private Button update_button;

    @FXML
    private ListView<ServiceOrder> serviceOrders_listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        serviceOrders_listView.setItems(Model.getInstance().getServiceOrders());
        serviceOrders_listView.setCellFactory(e -> new ServiceOrderCellFactory());
        update_button.setOnAction(actionEvent -> onServiceOrderUpdate());
    }

    private void onServiceOrderUpdate() {
        ObservableList<ServiceOrder> searchResults = Model.getInstance().getServiceOrders();
        serviceOrders_listView.setItems(searchResults);
        serviceOrders_listView.setCellFactory(e -> new ServiceOrderCellFactory());
    }

    private void initData(){
        if(Model.getInstance().getServiceOrders().isEmpty()){
            Model.getInstance().setServiceOrders();
        }
    }
}
