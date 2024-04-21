package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    public BorderPane manager_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case EMPLOYEES -> manager_parent.setCenter(Model.getInstance().getViewFactory().getEmployeesView());
                case CLIENTS -> manager_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case RESERVATIONS -> manager_parent.setCenter(Model.getInstance().getViewFactory().getReservationsView());
                case SERVICE_ORDERS -> manager_parent.setCenter(Model.getInstance().getViewFactory().getServiceOrdersView());
                case ROOMS -> manager_parent.setCenter(Model.getInstance().getViewFactory().getRoomsView());
                case ADD_ROOM -> manager_parent.setCenter(Model.getInstance().getViewFactory().getAddRoomView());
                case ADD_EMPLOYEE -> manager_parent.setCenter(Model.getInstance().getViewFactory().getAddEmployeeView());
                case SETTINGS -> manager_parent.setCenter(Model.getInstance().getViewFactory().getSettingsView());
                default -> manager_parent.setCenter(Model.getInstance().getViewFactory().getManagerHomePageView());
            }
        });
    }
}
