package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Entities.CompleteServiceOrder;
import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EmployeesController implements Initializable {

    @FXML
    private AnchorPane mian_anchorPane;

    @FXML
    private TableView<Employee> employees_tableView;

    @FXML
    private TableColumn<CompleteServiceOrder, String> firstNameColumn;

    @FXML
    private TableColumn<CompleteServiceOrder, String> lastNameColumn;

    @FXML
    private TableColumn<CompleteServiceOrder, String> statusColumn;

    @FXML
    private TableColumn<CompleteServiceOrder, String> profileColumn;

    @FXML
    private RadioButton women_radioButton;

    @FXML
    private TextField password_textField;

    @FXML
    private TextField phoneNumber_textField;

    @FXML
    private RadioButton maid_radioButton;

    @FXML
    private RadioButton man_radioButton;

    @FXML
    private RadioButton manager_radioButton;

    @FXML
    private TextField lastName_textField;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField firstName_textField;

    @FXML
    private Button addEmployee_button;

    @FXML
    private RadioButton administrator_radioButton;

    @FXML
    private VBox createEmployee_VB;

    @FXML
    private Button createEmployee_button;

    @FXML
    private TextField email_textField;

    @FXML
    private ToggleGroup employeesType;

    @FXML
    private Label choiceError_label;

    @FXML
    private Label emailError_label;

    @FXML
    private Label firstNameError_label;

    @FXML
    private Label lastNameError_label;

    @FXML
    private Label passwordError_label;

    @FXML
    private Label phoneNumberError_label;

    private boolean clickAddEmployee = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addEmployee_button.setOnAction(event -> {
            if(clickAddEmployee){
                createEmployee_VB.setVisible(false);
                clickAddEmployee = false;
            }else{
                createEmployee_VB.setVisible(true);
                clickAddEmployee = true;
            }
            clearErrors();
        });

        createEmployee_button.setOnAction(event -> createEmployee());

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        profileColumn.setCellValueFactory(new PropertyValueFactory<>("profile"));

        List<Employee> employees = Model.getInstance().getDatabaseHandler().getEmployeeDAO().getAll();
        ObservableList<Employee> obsEmployees = FXCollections.observableArrayList(employees);
        employees_tableView.setItems(obsEmployees);

        employees_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                navigateToEmployeePage(newSelection);
            }
        });
    }

    private void createEmployee() {
        if(validField()) return;

        String firstName = firstName_textField.getText();
        String lastName = lastName_textField.getText();
        String email = email_textField.getText();
        String phoneNumber = phoneNumber_textField.getText();
        String gender = ((RadioButton) this.gender.getSelectedToggle()).getText();
        String status = "Employeed";
        String profile = ((RadioButton) this.employeesType.getSelectedToggle()).getText();
        String password = password_textField.getText();

        Employee newEmployee = new Employee(0, firstName, lastName, email, phoneNumber, gender, lastName + firstName, password, status, profile, 0);

        int savedEmployeeId = Model.getInstance().getDatabaseHandler().getEmployeeDAO().save(newEmployee);
        newEmployee.setIdEmployee(savedEmployeeId);
        List<Employee> employees = Model.getInstance().getDatabaseHandler().getEmployeeDAO().getAll();
        ObservableList<Employee> obsEmployees = FXCollections.observableArrayList(employees);
        employees_tableView.setItems(obsEmployees);
        createEmployee_VB.setVisible(false);
    }

    private boolean validField() {
        clearErrors();
        boolean hasError = false;
        if(firstName_textField.getText().isEmpty() ||
                firstName_textField.getText().length() < 1 ||
                firstName_textField.getText().length() > 10){
            firstNameError_label.setVisible(true);
            new Shake(firstNameError_label).playAnim();
            hasError = true;
        }
        if(lastName_textField.getText().isEmpty() ||
                lastName_textField.getText().length() < 1 ||
                lastName_textField.getText().length() > 10){
            lastNameError_label.setVisible(true);
            new Shake(lastNameError_label).playAnim();
            hasError = true;
        }
        if(email_textField.getText().isEmpty() || !email_textField.getText().matches("^(.+)@(\\S+)$")){
            emailError_label.setVisible(true);
            new Shake(emailError_label).playAnim();
            hasError = true;
        }
        if(phoneNumber_textField.getText().isEmpty() || !phoneNumber_textField.getText().matches("^0\\d{9}$")){
            phoneNumberError_label.setVisible(true);
            new Shake(phoneNumberError_label).playAnim();
            hasError = true;
        }
        if(password_textField.getText().isEmpty() ||
                password_textField.getText().length() < 5 ||
                password_textField.getText().length() > 15){
            passwordError_label.setVisible(true);
            new Shake(passwordError_label).playAnim();
            hasError = true;
        }
        if(employeesType.getSelectedToggle() == null || gender.getSelectedToggle() == null){
            choiceError_label.setVisible(true);
            new Shake(choiceError_label).playAnim();
            hasError = true;
        }
        return hasError;
    }


    private void clearErrors(){
        firstNameError_label.setVisible(false);
        lastNameError_label.setVisible(false);
        emailError_label.setVisible(false);
        phoneNumberError_label.setVisible(false);
        passwordError_label.setVisible(false);
        choiceError_label.setVisible(false);
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
