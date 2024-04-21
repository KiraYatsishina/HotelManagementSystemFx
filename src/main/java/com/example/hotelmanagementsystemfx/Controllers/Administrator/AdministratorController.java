package com.example.hotelmanagementsystemfx.Controllers.Administrator;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable {
    public BorderPane administrator_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case RESERVATIONS -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getReservationsView());
                case SERVICE_ORDERS -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getServiceOrdersView());
                case CLIENTS -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case ROOMS -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getRoomsView());
                case NEW_RESERVATION -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getNewReservationView());
                case NEW_SERVICE_ORDER -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getNewServiceOrderView());
                case SETTINGS -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getSettingsView());
                default -> administrator_parent.setCenter(Model.getInstance().getViewFactory().getAdministratorHomePageView());
            }
        });
    }
}
