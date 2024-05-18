package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {
    private final StringProperty roomNumber;
    private final StringProperty roomType;
    private final StringProperty pricePerNight;
    private final StringProperty hasRefrigerator;
    private final StringProperty hasAirConditioning;
    private final StringProperty status;

    public Room(String roomNumber, String roomType, String pricePerNight, String hasRefrigerator, String hasAirConditioning, String status) {
        this.roomNumber = new SimpleStringProperty(this, "roomNumber", roomNumber);
        this.roomType = new SimpleStringProperty(this, "roomType", roomType);
        this.pricePerNight = new SimpleStringProperty(this, "pricePerNight", pricePerNight+"$");
        this.hasRefrigerator = new SimpleStringProperty(this, "hasRefrigerator", hasRefrigerator);
        this.hasAirConditioning = new SimpleStringProperty(this, "hasAirConditioning", hasAirConditioning);
        this.status = new SimpleStringProperty(this, "status", status);
    }

    public StringProperty roomNumberProperty() {
        return this.roomNumber;
    }

    public StringProperty roomTypeProperty() {
        return this.roomType;
    }

    public StringProperty pricePerNightProperty() {
        return this.pricePerNight;
    }

    public StringProperty hasRefrigeratorProperty() {
        return this.hasRefrigerator;
    }


    public StringProperty hasAirConditioningProperty() {
        return this.hasAirConditioning;
    }


    public StringProperty statusProperty() {
        return this.status;
    }
}
