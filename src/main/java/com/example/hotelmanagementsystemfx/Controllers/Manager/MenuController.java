package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.ManagerMenuOptions;
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
    private Button addEmployee_button;

    @FXML
    private Button addRoom_button;

    @FXML
    private Button clients_button;

    @FXML
    private Button employees_button;

    @FXML
    private Label fullName_label;

    @FXML
    private Button home_button;

    @FXML
    private Button logOut_button;

    @FXML
    private Label profile_label;

    @FXML
    private Button reservations_button;

    @FXML
    private Button rooms_button;

    @FXML
    private Button serviceOrders_button;
    @FXML
    private Button addServiceOrdersType_button;
    @FXML
    private Button settings_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
        String fullName = employee.firstNameProperty().get() + " " + employee.lastNameProperty().get();
        String profile = employee.profileProperty().get();
        fullName_label.setText(fullName);
        profile_label.setText(profile);
        addListeners();
    }

    private void addListeners(){
        home_button.setOnAction(actionEvent -> onHomePage());
        employees_button.setOnAction(actionEvent -> onEmployees());
        clients_button.setOnAction(actionEvent -> onClients());
        reservations_button.setOnAction(actionEvent -> onReservations());
        serviceOrders_button.setOnAction(actionEvent -> onServiceOrders());
        rooms_button.setOnAction(actionEvent -> onRooms());
        addRoom_button.setOnAction(actionEvent -> onAddRoom());
        addEmployee_button.setOnAction(actionEvent -> onAddEmployee());
        addServiceOrdersType_button.setOnAction(actionEvent -> onAddServiceOrdersType());
        settings_button.setOnAction(actionEvent -> onSettings());
        logOut_button.setOnAction(actionEvent -> onLogOut());
    }



    private void onLogOut(){
        Stage stage = (Stage) home_button.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }
    private void onClients() {
        clearStylesOnButton(clients_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.CLIENTS);
    }

    private void onEmployees() {
        clearStylesOnButton(employees_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.EMPLOYEES);
    }

    private void onHomePage(){
        clearStylesOnButton(home_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.HOMEPAGE);
    }

    private void onReservations(){
        clearStylesOnButton(reservations_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.RESERVATIONS);
    }
    private void onServiceOrders(){
        clearStylesOnButton(serviceOrders_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.SERVICE_ORDERS);
    }
    private void onRooms(){
        clearStylesOnButton(rooms_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.ROOMS);
    }
    private void onAddRoom(){
        clearStylesOnButton(addRoom_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.ADD_ROOM);
    }
    private void onAddEmployee(){
        clearStylesOnButton(addEmployee_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.ADD_EMPLOYEE);
    }
    private void onAddServiceOrdersType() {
        clearStylesOnButton(addServiceOrdersType_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.ADD_SERVICE_ORDERS_TYPE);
    }
    private void onSettings(){
        clearStylesOnButton(settings_button);
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.SETTINGS);
    }
    private List<Button> allButton() {
        return Arrays.asList(
                home_button,
                employees_button,
                clients_button,
                reservations_button,
                serviceOrders_button,
                rooms_button,
                addEmployee_button,
                addRoom_button,
                addServiceOrdersType_button,
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
