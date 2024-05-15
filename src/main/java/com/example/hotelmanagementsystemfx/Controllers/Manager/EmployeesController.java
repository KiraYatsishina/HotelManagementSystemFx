package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Employee;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.EmployeeCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {
    @FXML
    private ListView<Employee> employees_listView;

    @FXML
    private Button search_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        employees_listView.setItems(Model.getInstance().getEmployees());
        employees_listView.setCellFactory(e -> new EmployeeCellFactory());
    }
    private void initData(){
        if(Model.getInstance().getEmployees().isEmpty()){
            Model.getInstance().setEmployees();
        }
    }
}
