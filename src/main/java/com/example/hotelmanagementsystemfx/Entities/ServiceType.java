package com.example.hotelmanagementsystemfx.Entities;

import javafx.beans.property.*;

public class ServiceType {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty description;
    private final DoubleProperty price;
    private final StringProperty status;

    public ServiceType(int id, String name, String description, double price, String status) {
        this.id = new SimpleIntegerProperty(this, "idServiceType", id);
        this.name = new SimpleStringProperty(this, "name", name);
        this.description = new SimpleStringProperty(this, "description", description);
        this.price = new SimpleDoubleProperty(this, "price", price);
        this.status = new SimpleStringProperty(this, "status", status);
    }

    public StringProperty nameProperty() {
        return this.name;
    }
    public IntegerProperty idProperty() {
        return this.id;
    }
    public StringProperty descriptionProperty() {
        return this.description;
    }
    public DoubleProperty priceProperty() {
        return this.price;
    }
    public StringProperty statusProperty() {
        return this.status;
    }
}
