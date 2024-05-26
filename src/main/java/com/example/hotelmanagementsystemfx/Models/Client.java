package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phoneNumber;
    private final StringProperty gender;


    public Client(String firstName, String lastName, String phoneNumber,String gender) {
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.phoneNumber = new SimpleStringProperty(this, "phoneNumber", phoneNumber);
        this.gender = new SimpleStringProperty(this, "gender", gender);
    }

    public StringProperty firstNameProperty() {
        return this.firstName;
    }
    public StringProperty lastNameProperty() {
        return this.lastName;
    }
    public StringProperty phoneNumberProperty() {
        return this.phoneNumber;
    }
    public StringProperty genderProperty() {
        return this.gender;
    }
}
