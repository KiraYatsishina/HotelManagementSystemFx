package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.ManagerMenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
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
    private Button settings_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        home_button.setOnAction(actionEvent -> onHomePage());
        employees_button.setOnAction(actionEvent -> onEmployees());
        clients_button.setOnAction(actionEvent -> onClients());
    }

    private void onClients() {
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.CLIENTS);
    }

    private void onEmployees() {
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.EMPLOYEES);
    }

    private void onHomePage(){
        Model.getInstance().getViewFactory().getManagerSelectedMenuItem().set(ManagerMenuOptions.HOMEPAGE);
    }
}
