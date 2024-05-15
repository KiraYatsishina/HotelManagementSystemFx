package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    private final StringProperty fullName;
    private final StringProperty email;
    private final StringProperty phoneNumber;
    private final StringProperty profile;
    private final StringProperty status;

    public Employee(String fullName, String email, String phoneNumber, String profile, String status) {
        this.fullName = new SimpleStringProperty(this, "fullName", fullName);
        this.email = new SimpleStringProperty(this, "email", email);
        this.phoneNumber = new SimpleStringProperty(this, "phoneNumber", phoneNumber);
        this.profile = new SimpleStringProperty(this, "profile", profile);
        this.status = new SimpleStringProperty(this, "status", status);
    }

    public StringProperty fullNameProperty() {
        return this.fullName;
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

    public StringProperty statusProperty() {
        return this.status;
    }
}
