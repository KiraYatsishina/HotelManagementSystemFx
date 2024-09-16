package com.example.hotelmanagementsystemfx.Models.Entities;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.beans.property.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.PrimitiveIterator;

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

    private final Client client;
    private final Room room;
    private final Employee employee;


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
        this.tenure = new SimpleIntegerProperty(this, "tenure", getTenurePeriod());
        client = Model.getInstance().getDatabaseHandler().getClientDAO().get(idClient).get();
        room = Model.getInstance().getDatabaseHandler().getRoomDAO().get(idRoom).get();
        employee = Model.getInstance().getDatabaseHandler().getEmployeeDAO().get(idEmployee).get();
    }

    private int getTenurePeriod() {
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


    public static TableColumn<Reservation, String> getClientFullName(){
        TableColumn<Reservation, String> col = new TableColumn<>("Client");
        col.setCellValueFactory(cellData -> {
            Reservation reservation = cellData.getValue();
            return new SimpleStringProperty(reservation.client.getFullName());
        });
        return col;
    }

    public static TableColumn<Reservation, String> getRoomNumber(){
        TableColumn<Reservation, String> col = new TableColumn<>("Room");
        col.setCellValueFactory(cellData -> {
            Reservation reservation = cellData.getValue();
            return new SimpleStringProperty(reservation.room.roomNumberProperty().get());
        });
        return col;
    }

    public static TableColumn<Reservation, String> getEmployeeFullName(){
        TableColumn<Reservation, String> col = new TableColumn<>("Employee");
        col.setCellValueFactory(cellData -> {
            Reservation reservation = cellData.getValue();
            return new SimpleStringProperty(reservation.employee.getFullName());

        });
        return col;
    }

    public static TableColumn<Reservation, String> getNumberOfGuests(){
        TableColumn<Reservation, String> col = new TableColumn<>("Guests");
        col.setCellValueFactory(new PropertyValueFactory<>("numberOfGuests"));
        return col;
    }

    public static TableColumn<Reservation, Integer> getTenure() {
        TableColumn<Reservation, Integer> col = new TableColumn<>("Tenure");
        col.setCellValueFactory(new PropertyValueFactory<>("tenure"));
        return col;
    }

    public static TableColumn<Reservation, String> getReservationDate(){
        TableColumn<Reservation, String> col = new TableColumn<>("Reservation date");
        col.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        return col;
    }

    public static TableColumn<Reservation, String> getCheckInDate(){
        TableColumn<Reservation, String> col = new TableColumn<>("Check in date");
        col.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        return col;
    }

    public static TableColumn<Reservation, String> getCheckOutDate(){
        TableColumn<Reservation, String> col = new TableColumn<>("Check out date");
        col.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        return col;
    }

    public static TableColumn<Reservation, String> getPrice(){
        TableColumn<Reservation, String> col = new TableColumn<>("Price");
        col.setCellValueFactory(new PropertyValueFactory<>("price"));
        return col;
    }

    public static TableColumn<Reservation, String> getStatus(){
        TableColumn<Reservation, String> col = new TableColumn<>("Status");
        col.setCellValueFactory(new PropertyValueFactory<>("status"));
        return col;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public Employee getEmployee() {
        return employee;
    }
}
