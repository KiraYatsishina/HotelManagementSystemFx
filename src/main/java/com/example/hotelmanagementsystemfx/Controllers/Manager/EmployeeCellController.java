package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView account_image;
    private final Employee employee;
    public EmployeeCellController(Employee employee){
        this.employee = employee;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image femaleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/femaleAcc.png"));
        Image maleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/maleAcc.png"));
        if(employee.genderProperty().get().equals("Female")) account_image.setImage(femaleImage);
        else if(employee.genderProperty().get().equals("Male")) account_image.setImage(maleImage);
        fullName_label.textProperty().bind(Bindings.concat(employee.lastNameProperty(), " ", employee.firstNameProperty()));
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
