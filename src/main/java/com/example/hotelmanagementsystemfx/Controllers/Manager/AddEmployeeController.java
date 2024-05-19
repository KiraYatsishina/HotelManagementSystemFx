package com.example.hotelmanagementsystemfx.Controllers.Manager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML
    private Label createMessage_label;

    @FXML
    private Button create_button;

    @FXML
    private Button delete_button;

    @FXML
    private Label emailError_label;

    @FXML
    private Label email_label;

    @FXML
    private TextField email_textField;

    @FXML
    private Label employeeType_label;

    @FXML
    private ToggleGroup employeesType;

    @FXML
    private Label employeesTypeError_label;

    @FXML
    private VBox errors_VB;

    @FXML
    private Label firstName_label;

    @FXML
    private TextField firstName_textField;

    @FXML
    private ToggleGroup gender;

    @FXML
    private Label genderErrorLabel;

    @FXML
    private Label lastName_label;

    @FXML
    private TextField lastName_textField;

    @FXML
    private Label login_label;

    @FXML
    private Label passWord_label;

    @FXML
    private Label passwordError_label;

    @FXML
    private TextField password_textField;

    @FXML
    private Label phoneNumberError_label;

    @FXML
    private Label phoneNumber_label;

    @FXML
    private TextField phoneNumber_textField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
