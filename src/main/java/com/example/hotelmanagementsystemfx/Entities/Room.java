package com.example.hotelmanagementsystemfx.Entities;

import javafx.beans.property.*;

public class Room {
    private final IntegerProperty idRoom;
    private final StringProperty roomType;
    private final IntegerProperty capacity;
    private final DoubleProperty pricePerNight;

    private final IntegerProperty floor;
    private final StringProperty roomNumber;
    private final BooleanProperty hasRefrigerator;
    private final BooleanProperty hasAirConditioning;

    public Room(int idRoom,String roomNumber,String roomType, Integer capacity, double pricePerNight,
                int floor, boolean hasRefrigerator, boolean hasAirConditioning) {
        this.idRoom = new SimpleIntegerProperty(this, "idRoom", idRoom);
        this.roomNumber = new SimpleStringProperty(this, "roomNumber", roomNumber);
        this.roomType = new SimpleStringProperty(this, "roomType", roomType);
        this.capacity = new SimpleIntegerProperty(this, "capacity", capacity);
        this.pricePerNight = new SimpleDoubleProperty(this, "pricePerNight", pricePerNight);
        this.floor = new SimpleIntegerProperty(this, "floor", floor);
        this.hasRefrigerator = new SimpleBooleanProperty(this, "hasRefrigerator", hasRefrigerator);
        this.hasAirConditioning = new SimpleBooleanProperty(this, "hasAirConditioning", hasAirConditioning);
    }

    public IntegerProperty idRoomProperty() {
        return this.idRoom;
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
    public BooleanProperty hasRefrigeratorProperty() {
        return this.hasRefrigerator;
    }
    public BooleanProperty hasAirConditioningProperty() {
        return this.hasAirConditioning;
    }
}
