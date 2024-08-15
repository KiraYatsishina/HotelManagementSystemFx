package com.example.hotelmanagementsystemfx.Models.Entities;

import javafx.beans.property.*;

public class ServiceType {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty description;
    private final DoubleProperty price;
    private final StringProperty status;
    private final BooleanProperty assigned;

    public ServiceType(int id, String name, String description, double price, String status, boolean assigned) {
        this.id = new SimpleIntegerProperty(this, "idServiceType", id);
        this.name = new SimpleStringProperty(this, "name", name);
        this.description = new SimpleStringProperty(this, "description", description);
        this.price = new SimpleDoubleProperty(this, "price", price);
        this.status = new SimpleStringProperty(this, "status", status);
        this.assigned = new SimpleBooleanProperty(this, "assigned", assigned);
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
    public BooleanProperty assignedsProperty() {
        return this.assigned;
    }
}
