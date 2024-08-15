package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeePageController implements Initializable {
    Employee employee;
    @FXML
    private ImageView gender_image;

    @FXML
    private Label label;


    public EmployeePageController(Employee employee){
        this.employee = employee;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("!!!!!!!!!!!!");
    }
}
