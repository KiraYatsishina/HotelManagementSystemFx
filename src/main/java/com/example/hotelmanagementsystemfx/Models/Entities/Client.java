package com.example.hotelmanagementsystemfx.Models.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final IntegerProperty idClient;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phoneNumber;
    private final StringProperty gender;


    public Client(int idClient,String firstName, String lastName, String phoneNumber,String gender) {
        this.idClient = new SimpleIntegerProperty(this, "idClient", idClient);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.phoneNumber = new SimpleStringProperty(this, "phoneNumber", phoneNumber);
        this.gender = new SimpleStringProperty(this, "gender", gender);
    }

    public IntegerProperty idClientProperty() {
        return this.idClient;
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
    public void setIdClient(int id){
        this.idClient.set(id);
    }
}
