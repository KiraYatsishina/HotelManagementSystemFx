package com.example.hotelmanagementsystemfx.Models.Entities;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private final IntegerProperty idReservation;
    private final IntegerProperty idClient;
    private final IntegerProperty idRoom;
    private final IntegerProperty idEmployee;
    private final IntegerProperty numberOfGuests;

    private final StringProperty reservationDate;
    private final StringProperty checkInDate;
    private final StringProperty checkOutDate;

    private final DoubleProperty price;
    private final StringProperty status;
    private final IntegerProperty tenure;

    public Reservation(int idReservation, int idClient, int idRoom,int idEmployee,
                       int numberOfGuests, String reservationDate, String checkInDate,String checkOutDate,
                       double price,String status) {
        this.idReservation = new SimpleIntegerProperty(this, "idReservation", idReservation);
        this.idClient = new SimpleIntegerProperty(this, "idClient", idClient);
        this.idRoom = new SimpleIntegerProperty(this, "idRoom", idRoom);
        this.idEmployee = new SimpleIntegerProperty(this, "idEmployee", idEmployee);
        this.numberOfGuests = new SimpleIntegerProperty(this, "numberOfGuests", numberOfGuests);
        this.reservationDate = new SimpleStringProperty(this, "reservationDate", reservationDate);
        this.checkInDate = new SimpleStringProperty(this, "checkInDate", checkInDate);
        this.checkOutDate = new SimpleStringProperty(this, "checkOutDate", checkOutDate);
        this.price = new SimpleDoubleProperty(this, "price", price);
        this.status = new SimpleStringProperty(this, "status", status);
        this.tenure = new SimpleIntegerProperty(this, "tenure", getTenure());
    }

    private int getTenure() {
        LocalDate checkIn = LocalDate.parse(checkInDate.get());
        LocalDate checkOut = LocalDate.parse(checkOutDate.get());
        return (int) ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public IntegerProperty idReservationProperty() {
        return this.idReservation;
    }
    public IntegerProperty idClientProperty() {
        return this.idClient;
    }
    public IntegerProperty idRoomProperty() {
        return this.idRoom;
    }
    public IntegerProperty idEmployeeProperty() {
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
