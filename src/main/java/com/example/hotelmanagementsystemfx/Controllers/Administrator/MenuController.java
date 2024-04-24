package com.example.hotelmanagementsystemfx.Controllers.Administrator;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.AdministratorMenuOptions;
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
    private Button clients_button;

    @FXML
    private Label fullName_label;

    @FXML
    private Button home_button;

    @FXML
    private Button logOut_button;

    @FXML
    private Button newReservation_button;

    @FXML
    private Button newServiceOrder_button;

    @FXML
    private Label profile_label;

    @FXML
    private Button reservations_button;

    @FXML
    private Button rooms_button;

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
        reservations_button.setOnAction(actionEvent -> onReservations());
        serviceOrders_button.setOnAction(actionEvent -> onServiceOrders());
        clients_button.setOnAction(actionEvent -> onClients());
        rooms_button.setOnAction(actionEvent -> onRooms());
        newReservation_button.setOnAction(actionEvent -> onNewReservation());
        newServiceOrder_button.setOnAction(actionEvent -> onNewServiceOrder());
        settings_button.setOnAction(actionEvent -> onSettings());
        logOut_button.setOnAction(actionEvent -> onLogOut());
    }

    private void onSettings() {
        clearStylesOnButton(settings_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.SETTINGS);
    }

    private void onNewServiceOrder() {
        clearStylesOnButton(newServiceOrder_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.NEW_SERVICE_ORDER);

    }

    private void onNewReservation() {
        clearStylesOnButton(newReservation_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.NEW_RESERVATION);

    }

    private void onRooms() {
        clearStylesOnButton(rooms_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.ROOMS);

    }

    private void onClients() {
        clearStylesOnButton(clients_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.CLIENTS);

    }

    private void onServiceOrders() {
        clearStylesOnButton(serviceOrders_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.SERVICE_ORDERS);
    }

    private void onReservations() {
        clearStylesOnButton(reservations_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.RESERVATIONS);

    }

    private void onHomePage() {
        clearStylesOnButton(home_button);
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.HOMEPAGE);
    }

    private void onLogOut(){
        Stage stage= (Stage) home_button.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
    private List<Button> allButton() {
        return Arrays.asList(
                home_button,
                clients_button,
                reservations_button,
                serviceOrders_button,
                rooms_button,
                newReservation_button,
                newServiceOrder_button,
                settings_button
        );
    }
    private void clearStylesOnButton(Button buttonPressed){
        for (Button button : allButton()) {
            button.getStyleClass().clear();
            button.getStyleClass().add("menu_button");
        }
        buttonPressed.getStyleClass().add("menu_button_pressed");
    }
}
