package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import com.example.hotelmanagementsystemfx.Models.Entities.Reservation;
import com.example.hotelmanagementsystemfx.Models.Entities.ServiceOrder;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EmployeesController implements Initializable {

    @FXML
    private AnchorPane mian_anchorPane;

    @FXML
    private TableView<Employee> employees_tableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employees_tableView.getColumns().addAll(
                Employee.getFirstName(),
                Employee.getLastName(),
                Employee.getStatus(),
                Employee.getProfile()
        );
        List<Employee> employees = Model.getInstance().getDatabaseHandler().getEmployeeDAO().getAll();
        ObservableList<Employee> obsEmployees = FXCollections.observableArrayList(employees);
        employees_tableView.setItems(obsEmployees);

        employees_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                navigateToEmployeePage(newSelection);
            }
        });

    }


    private void navigateToEmployeePage(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/Employee.fxml"));
            EmployeeController controller = new EmployeeController(employee);
            loader.setController(controller);

            AnchorPane employeePane = loader.load();
            mian_anchorPane.getChildren().setAll(employeePane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
