package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.*;

public class ServiceOrdersType {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty description;
    private final DoubleProperty price;
    private final IntegerProperty orderCount;
    private final StringProperty status;

    public ServiceOrdersType(String id, String name,String description, double price,
                int orderCount, String status) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.name = new SimpleStringProperty(this, "name", name);
        this.description = new SimpleStringProperty(this, "description", description);
        this.price = new SimpleDoubleProperty(this, "price", price);
        this.orderCount = new SimpleIntegerProperty(this, "orderCount", orderCount);
        this.status = new SimpleStringProperty(this, "status", status);
    }

    public StringProperty nameProperty() {
        return this.name;
    }
    public StringProperty idProperty() {
        return this.id;
    }
    public StringProperty descriptionProperty() {
        return this.description;
    }
    public DoubleProperty priceProperty() {
        return this.price;
    }
    public IntegerProperty orderCountProperty() {return this.orderCount;}
    public StringProperty statusProperty() {
        return this.status;
    }
}
