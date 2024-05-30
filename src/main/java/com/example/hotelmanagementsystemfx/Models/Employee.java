package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    private final StringProperty profile;
    private final StringProperty gender;
    private final StringProperty login;
    private final StringProperty password;
    private final StringProperty status;

    public Employee(String firstName, String lastName, String email, String phoneNumber, String profile,String gender,String login,String password, String status) {
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.email = new SimpleStringProperty(this, "email", email);
        this.phoneNumber = new SimpleStringProperty(this, "phoneNumber", phoneNumber);
        this.profile = new SimpleStringProperty(this, "profile", profile);
        this.gender = new SimpleStringProperty(this, "gender", gender);
        this.login = new SimpleStringProperty(this, "login", login);
        this.password = new SimpleStringProperty(this, "password", password);
        this.status = new SimpleStringProperty(this, "status", status);
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

    public void updatePassword(String newPassword) {
        this.password.set(newPassword);
        Model.getInstance().getDatabaseHandler().updateEmployeeColById(email.get(), "password", newPassword);
    }
}
