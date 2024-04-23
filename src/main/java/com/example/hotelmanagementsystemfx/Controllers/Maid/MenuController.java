package com.example.hotelmanagementsystemfx.Controllers.Maid;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.AdministratorMenuOptions;
import com.example.hotelmanagementsystemfx.Views.MaidMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Label fullName_label;

    @FXML
    private Button home_button;

    @FXML
    private Button logOut_button;

    @FXML
    private Button myServiceOrders_button;

    @FXML
    private Label profile_label;

    @FXML
    private Button serviceOrders_button;

    @FXML
    private Button settings_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
    private void addListeners() {
        home_button.setOnAction(actionEvent -> onHomePage());
        myServiceOrders_button.setOnAction(actionEvent -> onMyServiceOrders());
        serviceOrders_button.setOnAction(actionEvent -> onServiceOrders());
        settings_button.setOnAction(actionEvent -> onSettings());
        logOut_button.setOnAction(actionEvent -> onLogOut());
    }
    private void onHomePage() {
        clearStylesOnButton(home_button);
        Model.getInstance().getViewFactory().getMaidSelectedMenuItem().set(MaidMenuOptions.HOMEPAGE);
    }
    private void onSettings() {
        clearStylesOnButton(settings_button);
        Model.getInstance().getViewFactory().getMaidSelectedMenuItem().set(MaidMenuOptions.SETTINGS);
    }

    private void onServiceOrders() {
        clearStylesOnButton(serviceOrders_button);
        Model.getInstance().getViewFactory().getMaidSelectedMenuItem().set(MaidMenuOptions.SERVICE_ORDERS);

    }

    private void onMyServiceOrders() {
        clearStylesOnButton(myServiceOrders_button);
        Model.getInstance().getViewFactory().getMaidSelectedMenuItem().set(MaidMenuOptions.MY_SERVICE_ORDERS);

    }

    private void onLogOut(){
        Stage stage= (Stage) home_button.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
    private List<Button> allButton() {
        return Arrays.asList(
                home_button,
                serviceOrders_button,
                myServiceOrders_button,
                settings_button
        );
    }
    private void clearStylesOnButton(Button buttonPressed){
        for (Button button :
                allButton()) {
            button.getStyleClass().clear();
            button.getStyleClass().add("menu_button");
        }
        buttonPressed.getStyleClass().add("menu_button_pressed");
    }
}
