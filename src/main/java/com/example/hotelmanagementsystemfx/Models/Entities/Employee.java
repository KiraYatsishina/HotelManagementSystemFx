package com.example.hotelmanagementsystemfx.Models.Entities;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.beans.property.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Employee {
    private final IntegerProperty idEmployee;

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    private final StringProperty gender;
    private final StringProperty login;
    private final StringProperty password;
    private final StringProperty status;
    private final StringProperty profile;
    private final DoubleProperty salary;


    public Employee(int idEmployee, String firstName, String lastName, String email, String phoneNumber,
                    String gender,String login,String password, String status, String profile, double salary) {
        this.idEmployee = new SimpleIntegerProperty(this, "idEmployee", idEmployee);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.email = new SimpleStringProperty(this, "email", email);
        this.phoneNumber = new SimpleStringProperty(this, "phoneNumber", phoneNumber);
        this.gender = new SimpleStringProperty(this, "gender", gender);
        this.login = new SimpleStringProperty(this, "login", login);
        this.password = new SimpleStringProperty(this, "password", password);
        this.status = new SimpleStringProperty(this, "status", status);
        this.profile = new SimpleStringProperty(this, "profile", profile);
        this.salary = new SimpleDoubleProperty(this, "salary", salary);

    }
    public IntegerProperty idEmployeeProperty() {
        return this.idEmployee;
    }
    public StringProperty firstNameProperty() {
        return this.firstName;
    }
    public StringProperty lastNameProperty() {
        return this.lastName;
    }
    public StringProperty emailProperty() {
        return this.email;
    }

    public StringProperty phoneNumberProperty() {
        return this.phoneNumber;
    }

    public StringProperty profileProperty() {
        return this.profile;
    }
    public StringProperty genderProperty() {
        return this.gender;
    }
    public StringProperty loginProperty() {
        return this.login;
    }
    public StringProperty passwordProperty() {
        return this.password;
    }
    public StringProperty statusProperty() {
        return this.status;
    }
    public DoubleProperty salaryProperty() {
        return this.salary;
    }


    public void updatePassword(String newPassword) {
        this.password.set(newPassword);
        Model.getInstance().getDatabaseHandler().getEmployeeDAO().updateEmployeeByField(idEmployee.get(), "password", newPassword);
    }
    public String getFullName(){
        return firstNameProperty().get() + " " + lastNameProperty().get();
    }
    public static TableColumn<Employee, String> getFirstName(){
        TableColumn<Employee, String> col = new TableColumn<>("First name");
        col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        return col;
    }
    public static TableColumn<Employee, String> getLastName(){
        TableColumn<Employee, String> col = new TableColumn<>("Last name");
        col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        return col;
    }
    public static TableColumn<Employee, String> getStatus(){
        TableColumn<Employee, String> col = new TableColumn<>("Status");
        col.setCellValueFactory(new PropertyValueFactory<>("status"));
        return col;
    }
    public static TableColumn<Employee, String> getProfile(){
        TableColumn<Employee, String> fNameCol = new TableColumn<>("Profile");
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("profile"));
        return fNameCol;
    }

    public void setIdEmployee(int savedEmployeeId) {
        idEmployeeProperty().set(savedEmployeeId);
    }
}
