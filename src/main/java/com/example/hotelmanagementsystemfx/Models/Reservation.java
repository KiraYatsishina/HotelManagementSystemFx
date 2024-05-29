package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.*;

public class Reservation {
    private final StringProperty idReservation;
    private final StringProperty idClient;
    private final StringProperty idRoom;
    private final StringProperty idEmployee;
    private final IntegerProperty numberOfGuests;

    private final StringProperty reservationDate;
    private final StringProperty checkInDate;
    private final StringProperty checkOutDate;

    private final DoubleProperty price;
    private final StringProperty status;
    private final IntegerProperty tenure;

    public Reservation(String idReservation, String idClient, String idRoom,String idEmployee,
                       int numberOfGuests, String reservationDate, String checkInDate,String checkOutDate,
                       double price,String status, int tenure) {
        this.idReservation = new SimpleStringProperty(this, "idReservation", idReservation);
        this.idClient = new SimpleStringProperty(this, "idClient", idClient);
        this.idRoom = new SimpleStringProperty(this, "idRoom", idRoom);
        this.idEmployee = new SimpleStringProperty(this, "idEmployee", idEmployee);
        this.numberOfGuests = new SimpleIntegerProperty(this, "numberOfGuests", numberOfGuests);
        this.reservationDate = new SimpleStringProperty(this, "reservationDate", reservationDate);
        this.checkInDate = new SimpleStringProperty(this, "checkInDate", checkInDate);
        this.checkOutDate = new SimpleStringProperty(this, "checkOutDate", checkOutDate);
        this.price = new SimpleDoubleProperty(this, "price", price);
        this.status = new SimpleStringProperty(this, "status", status);
        this.tenure = new SimpleIntegerProperty(this, "tenure", tenure);
    }

    public StringProperty idReservationProperty() {
        return this.idReservation;
    }
    public StringProperty idClientProperty() {
        return this.idClient;
    }
    public StringProperty idRoomProperty() {
        return this.idRoom;
    }
    public StringProperty idEmployeeProperty() {
        return this.idEmployee;
    }
    public IntegerProperty numberOfGuestsProperty() {
        return this.numberOfGuests;
    }
    public StringProperty reservationDateProperty() {
        return this.reservationDate;
    }
    public StringProperty checkInDateProperty() {
        return this.checkInDate;
    }
    public StringProperty checkOutDateProperty() {
        return this.checkOutDate;
    }
    public DoubleProperty priceProperty() {
        return this.price;
    }
    public StringProperty statusProperty() {
        return this.status;
    }
    public IntegerProperty tenureProperty() {
        return this.tenure;
    }
}
