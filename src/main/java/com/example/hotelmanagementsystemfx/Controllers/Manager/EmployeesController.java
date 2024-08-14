package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.AdministratorMenuOptions;
import com.example.hotelmanagementsystemfx.Views.EmployeeCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EmployeesController implements Initializable {
    @FXML
    private ListView<Employee> employees_listView;

    @FXML
    private Button search_button;

    @FXML
    private CheckBox maternityLeave_checkBox;

    @FXML
    private CheckBox administrator_checkBox;

    @FXML
    private TextField email_textField;

    @FXML
    private CheckBox employed_checkBox;

    @FXML
    private TextField firstName_textField;

    @FXML
    private TextField lastName_textField;

    @FXML
    private CheckBox maid_checkBox;

    @FXML
    private RadioButton man_radioButton;

    @FXML
    private CheckBox manager_checkBox;

    @FXML
    private TextField phoneNumber_textField;

    @FXML
    private ToggleGroup sex;

    @FXML
    private CheckBox sickLeave_checkBox;

    @FXML
    private CheckBox terminated_checkBox;

    @FXML
    private CheckBox vocation_checkBox;

    @FXML
    private RadioButton women_radioButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        employees_listView.setItems(Model.getInstance().getEmployees());
        employees_listView.setCellFactory(e -> new EmployeeCellFactory());
        search_button.setOnAction(actionEvent -> onEmployeeSearch());
        employees_listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    private void onEmployee() {
        Model.getInstance().getViewFactory().getAdministratorSelectedMenuItem().set(AdministratorMenuOptions.SETTINGS);
    }
    private void onEmployeeSearch(){
        String sqlRequest = makeRequest();
        ObservableList<Employee> searchResults = Model.getInstance().searchEmployees(sqlRequest);
        employees_listView.setItems(searchResults);
        employees_listView.setCellFactory(e -> new EmployeeCellFactory());
    }

    private String makeRequest() {
        List<List<String>> conditions = new ArrayList<>();

        List<String> statusConditions = new ArrayList<>();
        if (employed_checkBox.isSelected())
            statusConditions.add("status = 'Employed'");
        if (terminated_checkBox.isSelected())
            statusConditions.add("status = 'Terminated'");
        if (maternityLeave_checkBox.isSelected())
            statusConditions.add("status = 'Maternity Leave'");
        if (sickLeave_checkBox.isSelected())
            statusConditions.add("status = 'Sick Leave'");
        if (vocation_checkBox.isSelected())
            statusConditions.add("status = 'Vocation'");
        if (!statusConditions.isEmpty())
            conditions.add(statusConditions);

        List<String> genderConditions = new ArrayList<>();
        if (man_radioButton.isSelected())
            genderConditions.add("gender = 'Male'");
        else if (women_radioButton.isSelected())
            genderConditions.add("gender = 'Female'");
        if (!genderConditions.isEmpty())
            conditions.add(genderConditions);

        List<String> personalInfoConditions = new ArrayList<>();
        if (!firstName_textField.getText().isEmpty())
            personalInfoConditions.add("firstName LIKE '%" + firstName_textField.getText() + "%'");
        if (!lastName_textField.getText().isEmpty())
            personalInfoConditions.add("lastName LIKE '%" + lastName_textField.getText() + "%'");
        if (!email_textField.getText().isEmpty())
            personalInfoConditions.add("email LIKE '%" + email_textField.getText() + "%'");
        if (!phoneNumber_textField.getText().isEmpty())
            personalInfoConditions.add("phoneNumber LIKE '%" + phoneNumber_textField.getText() + "%'");
        if (!personalInfoConditions.isEmpty())
            conditions.add(personalInfoConditions);

        List<String> employeeTypeConditions = new ArrayList<>();
        if (manager_checkBox.isSelected())
            employeeTypeConditions.add("idEmployeeType = '1'");
        if (administrator_checkBox.isSelected())
            employeeTypeConditions.add("idEmployeeType = '2'");
        if (maid_checkBox.isSelected())
            employeeTypeConditions.add("idEmployeeType = '3'");
        if (!employeeTypeConditions.isEmpty())
            conditions.add(employeeTypeConditions);

        StringBuilder sqlRequestBuilder = new StringBuilder("SELECT * FROM " );

        if (!conditions.isEmpty()) {
            sqlRequestBuilder.append(" WHERE ");
            for (int i = 0; i < conditions.size(); i++) {
                if (i > 0)
                    sqlRequestBuilder.append(" AND ");
                List<String> groupConditions = conditions.get(i);
                if (groupConditions.size() > 1) {
                    sqlRequestBuilder.append("(");
                    sqlRequestBuilder.append(String.join(" OR ", groupConditions));
                    sqlRequestBuilder.append(")");
                } else sqlRequestBuilder.append(groupConditions.get(0));
            }
        }
        return sqlRequestBuilder.append(";").toString();
    }

    private void initData(){
        if(Model.getInstance().getEmployees().isEmpty()){
            Model.getInstance().setEmployees();
        }
    }
}
