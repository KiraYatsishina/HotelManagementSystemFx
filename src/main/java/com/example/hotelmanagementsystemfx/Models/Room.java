package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.*;

public class Room {
    private final StringProperty roomNumber;
    private final StringProperty roomType;
    private final IntegerProperty capacity;
    private final DoubleProperty pricePerNight;
    private final IntegerProperty floor;
    private final StringProperty hasRefrigerator;
    private final StringProperty hasAirConditioning;
    private final StringProperty status;

    public Room(String roomType,String roomNumber, Integer capacity, double pricePerNight,
                int floor, String hasRefrigerator, String hasAirConditioning, String status) {
        this.roomNumber = new SimpleStringProperty(this, "roomNumber", roomNumber);
        this.roomType = new SimpleStringProperty(this, "roomType", roomType);
        this.capacity = new SimpleIntegerProperty(this, "capacity", capacity);
        this.pricePerNight = new SimpleDoubleProperty(this, "pricePerNight", pricePerNight);
        this.floor = new SimpleIntegerProperty(this, "floor", floor);
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
    public IntegerProperty capacityProperty() {return this.capacity;}
    public DoubleProperty pricePerNightProperty() {
        return this.pricePerNight;
    }
    public IntegerProperty floorProperty() {return this.floor;}
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
