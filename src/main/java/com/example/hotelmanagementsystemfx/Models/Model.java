package com.example.hotelmanagementsystemfx.Models;

import com.example.hotelmanagementsystemfx.DB.DatabaseHandler;
import com.example.hotelmanagementsystemfx.Models.Entities.*;
import com.example.hotelmanagementsystemfx.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseHandler databaseHandler;

    private final ObservableList<Employee> employees;
    private final ObservableList<Room> rooms;
    private final ObservableList<ServiceType> serviceTypes;
    private final ObservableList<Client> clients;
    private final ObservableList<Reservation> reservations;
    private final ObservableList<ServiceOrder> serviceOrders;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseHandler = new DatabaseHandler();
        this.employees = FXCollections.observableArrayList();
        this.rooms = FXCollections.observableArrayList();
        this.serviceTypes = FXCollections.observableArrayList();
        this.clients = FXCollections.observableArrayList();
        this.reservations = FXCollections.observableArrayList();
        this.serviceOrders = FXCollections.observableArrayList();
    }
    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public ObservableList<Employee> getEmployees(){
        return employees;
    }
    public ObservableList<Client> getClients(){
        return clients;
    }
    public ObservableList<Room> getRooms(){
        return rooms;
    }
    public ObservableList<ServiceType> getServiceOrdersTypes(){
        return serviceTypes;
    }
    public ObservableList<ServiceOrder> getServiceOrders(){return serviceOrders;}
    public ObservableList<Reservation> getReservations(){return reservations;}



    public void setEmployees(){
        employees.addAll(databaseHandler.getEmployeeDAO().getAll());
    }
    public ObservableList<Employee> searchEmployees(String sqlRequest){
        return databaseHandler.getEmployeeDAO().searchByRequest(sqlRequest);
    }

    public void setClients(){
        clients.addAll(databaseHandler.getClientDAO().getAll());
    }
    public ObservableList<Client> searchClients(String sqlRequest){
        return databaseHandler.getClientDAO().searchByRequest(sqlRequest);
    }

    public void setRooms(){
        rooms.addAll(databaseHandler.getRoomDAO().getAll());
    }
    public ObservableList<Room> searchRooms(String sqlRequest){
        return databaseHandler.getRoomDAO().searchByRequest(sqlRequest);
    }

    public void setServiceTypes(){
        serviceTypes.addAll(databaseHandler.getServiceTypeDAO().getAll());
    }
    public ObservableList<ServiceType> searchServiceTypes(String sqlRequest){
        return databaseHandler.getServiceTypeDAO().searchByRequest(sqlRequest);
    }

    public void setReservations(){
        reservations.addAll(databaseHandler.getReservationDAO().getAll());
    }
    public ObservableList<Reservation> searchReservations(String sqlRequest){
        return databaseHandler.getReservationDAO().searchByRequest(sqlRequest);
    }

    public void setServiceOrders(){
        serviceOrders.addAll(databaseHandler.getServiceOrderDAO().getAll());
    }
    public ObservableList<ServiceOrder> searchServiceOrders(String sqlRequest){
        return databaseHandler.getServiceOrderDAO().searchByRequest(sqlRequest);
    }
}
