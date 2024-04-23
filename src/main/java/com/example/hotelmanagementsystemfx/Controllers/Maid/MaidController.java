package com.example.hotelmanagementsystemfx.Controllers.Maid;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MaidController  implements Initializable {
    public BorderPane maid_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getMaidSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case SERVICE_ORDERS -> maid_parent.setCenter(Model.getInstance().getViewFactory().getServiceOrdersView());
                case MY_SERVICE_ORDERS -> maid_parent.setCenter(Model.getInstance().getViewFactory().getMyServiceOrdersView());
                case SETTINGS -> maid_parent.setCenter(Model.getInstance().getViewFactory().getSettingsView());
                default -> maid_parent.setCenter(Model.getInstance().getViewFactory().getAdministratorHomePageView());
            }
        });
    }
}
