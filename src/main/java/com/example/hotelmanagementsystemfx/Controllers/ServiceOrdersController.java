package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Models.Room;
import com.example.hotelmanagementsystemfx.Models.ServiceOrder;
import com.example.hotelmanagementsystemfx.Views.RoomCellFactory;
import com.example.hotelmanagementsystemfx.Views.ServiceOrderCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiceOrdersController implements Initializable {
    @FXML
    private Button search_button;

    @FXML
    private ListView<ServiceOrder> serviceOrders_listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        serviceOrders_listView.setItems(Model.getInstance().getServiceOrders());
        serviceOrders_listView.setCellFactory(e -> new ServiceOrderCellFactory());
        search_button.setOnAction(actionEvent -> onServiceOrderSearch());
    }

    private void onServiceOrderSearch() {
        String sqlRequest = makeRequest();
        ObservableList<ServiceOrder> searchResults = Model.getInstance().searchServiceOrders(sqlRequest);
        serviceOrders_listView.setItems(searchResults);
        serviceOrders_listView.setCellFactory(e -> new ServiceOrderCellFactory());
    }

    private String makeRequest() {
        return "";
    }
    private void initData(){
        if(Model.getInstance().getServiceOrders().isEmpty()){
            Model.getInstance().setServiceOrders();
        }
    }
}
