package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCellController implements Initializable {
    @FXML
    private Button edit_button;

    @FXML
    private Label email_label;

    @FXML
    private Label fullName_label;

    @FXML
    private Label phoneNumber_label;

    @FXML
    private Label profile_label;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_label;
    private final Employee employee;
    public EmployeeCellController(Employee employee){
        this.employee = employee;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullName_label.textProperty().bind(employee.fullNameProperty());
        email_label.textProperty().bind(employee.emailProperty());
        phoneNumber_label.textProperty().bind(employee.phoneNumberProperty());
        profile_label.textProperty().bind(employee.profileProperty());
        status_label.textProperty().bind(employee.statusProperty());
        switch (status_label.getText()) {
            case "Employed" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Terminated" -> status_circle.setStyle("-fx-fill: #708090;");
            case "Vocation" -> status_circle.setStyle("-fx-fill: #1E90FF;");
            case "Sick Leave" -> status_circle.setStyle("-fx-fill: #FFFF00;");
            case "Maternity Leave" -> status_circle.setStyle("-fx-fill: #800080;");}
    }
}
