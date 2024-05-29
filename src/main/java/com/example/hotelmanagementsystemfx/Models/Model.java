package com.example.hotelmanagementsystemfx.Models;

import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.DB.DatabaseHandler;
import com.example.hotelmanagementsystemfx.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseHandler databaseHandler;

    private final ObservableList<Employee> employees;
    private final ObservableList<Room> rooms;
    private final ObservableList<ServiceOrdersType> ordersTypes;
    private final ObservableList<Client> clients;
    private final ObservableList<Reservation> reservations;
    private final ObservableList<ServiceOrder> serviceOrders;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseHandler = new DatabaseHandler();
        this.employees = FXCollections.observableArrayList();
        this.rooms = FXCollections.observableArrayList();
        this.ordersTypes = FXCollections.observableArrayList();
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

    /*
    * Manager Section
    */
    public ObservableList<Employee> getEmployees(){
        return employees;
    }
    public ObservableList<Client> getClients(){
        return clients;
    }
    public ObservableList<Room> getRooms(){
        return rooms;
    }
    public ObservableList<ServiceOrdersType> getServiceOrdersTypes(){
        return ordersTypes;
    }
    public ObservableList<ServiceOrder> getServiceOrders(){return serviceOrders;}


    public void setEmployees(){
        ResultSet resultSet = databaseHandler.getAllEmployeesData();
        try{
            while (resultSet.next()){
                String fName = resultSet.getString(Const.EMPLOYEE_FIRSTNAME);
                String lName = resultSet.getString(Const.EMPLOYEE_LASTNAME);
                String email = resultSet.getString(Const.EMPLOYEE_EMAIL);
                String phoneNumber = resultSet.getString(Const.EMPLOYEE_PHONE_NUMBER);
                String profile = resultSet.getString(Const.EMPLOYEE_ID_EMPLOYEE_TYPE);
                if(profile.equals("1")) profile = "Manager";
                if(profile.equals("2")) profile = "Administrator";
                if(profile.equals("3")) profile = "Maid";
                String gender = resultSet.getString(Const.EMPLOYEE_GENDER);
                String status = resultSet.getString(Const.EMPLOYEE_STATUS);
                String login = resultSet.getString(Const.EMPLOYEE_LOGIN);
                String password = resultSet.getString(Const.EMPLOYEE_PASSWORD);
                employees.add(new Employee(fName, lName, email, phoneNumber, profile, gender, login, password, status));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Employee> searchEmployees(String sqlRequest){
        ObservableList<Employee> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseHandler.search(sqlRequest);
        try{
            while (resultSet.next()) {
                String fName = resultSet.getString(Const.EMPLOYEE_FIRSTNAME);
                String lName = resultSet.getString(Const.EMPLOYEE_LASTNAME);
                String email = resultSet.getString(Const.EMPLOYEE_EMAIL);
                String phoneNumber = resultSet.getString(Const.EMPLOYEE_PHONE_NUMBER);
                String profile = resultSet.getString(Const.EMPLOYEE_ID_EMPLOYEE_TYPE);
                if (profile.equals("1")) profile = "Manager";
                if (profile.equals("2")) profile = "Administrator";
                if (profile.equals("3")) profile = "Maid";
                String gender = resultSet.getString(Const.EMPLOYEE_GENDER);
                String status = resultSet.getString(Const.EMPLOYEE_STATUS);
                String login = resultSet.getString(Const.EMPLOYEE_LOGIN);
                String password = resultSet.getString(Const.EMPLOYEE_PASSWORD);
                searchResults.add(new Employee(fName, lName, email, phoneNumber, profile, gender, login, password, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchResults;
    }
    public void setClients(){
        ResultSet resultSet = databaseHandler.getAllClientsData();
        try{
            while (resultSet.next()){
                String fName = resultSet.getString(Const.CLIENT_FIRSTNAME);
                String lName = resultSet.getString(Const.CLIENT_LASTNAME);
                String phoneNumber = resultSet.getString(Const.CLIENT_PHONE_NUMBER);
                String gender = resultSet.getString(Const.CLIENT_GENDER);

                clients.add(new Client(fName, lName, phoneNumber, gender));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Client> searchClients(String sqlRequest){
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseHandler.search(sqlRequest);
        try{
            while (resultSet.next()) {
                String fName = resultSet.getString(Const.CLIENT_FIRSTNAME);
                String lName = resultSet.getString(Const.CLIENT_LASTNAME);
                String phoneNumber = resultSet.getString(Const.CLIENT_PHONE_NUMBER);
                String gender = resultSet.getString(Const.CLIENT_GENDER);
                searchResults.add(new Client(fName, lName, phoneNumber, gender));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchResults;
    }

    /*
     * Administrator Section
     */

    /*
     * Maid Section
     */

    /*
     * Utility Section
     */

    public void setRooms(){
        ResultSet resultSet = databaseHandler.getAllRoomsData();
        try{
            while (resultSet.next()){

                String roomNumber = resultSet.getString(Const.ROOM_NUMBER);
                String roomType = resultSet.getString(Const.ROOM_TYPE);
                int capacity = resultSet.getInt(Const.ROOM_CAPACITY);
                double pricePerNight = resultSet.getDouble(Const.ROOM_PRICE_PER_NIGHT);
                int floor = resultSet.getInt(Const.ROOM_FLOOR);
                String hasRefrigerator = resultSet.getString(Const.ROOM_HAS_REFRIGERATOR);
                String hasAirConditioning = resultSet.getString(Const.ROOM_HAS_AIR_CONDITIONING);
                String status = Model.getInstance().databaseHandler.determineRoomStatusToday(Const.ROOM_ID);
                rooms.add(new Room(roomType, roomNumber, capacity,
                        pricePerNight, floor, hasRefrigerator, hasAirConditioning, status));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Room> sortRooms(String sqlRequest){
        ObservableList<Room> sortResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseHandler.search(sqlRequest);

        try{
            while (resultSet.next()) {
                String roomNumber = resultSet.getString(Const.ROOM_NUMBER);
                String roomType = resultSet.getString(Const.ROOM_TYPE);
                int capacity = resultSet.getInt(Const.ROOM_CAPACITY);
                double pricePerNight = resultSet.getDouble(Const.ROOM_PRICE_PER_NIGHT);
                int floor = resultSet.getInt(Const.ROOM_FLOOR);
                String hasRefrigerator = resultSet.getString(Const.ROOM_HAS_REFRIGERATOR);
                String hasAirConditioning = resultSet.getString(Const.ROOM_HAS_AIR_CONDITIONING);
                String status = Model.getInstance().databaseHandler.determineRoomStatusToday(Const.ROOM_ID);
                sortResults.add(new Room(roomType, roomNumber, capacity,
                        pricePerNight, floor, hasRefrigerator, hasAirConditioning, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sortResults;
    }

    public void setServiceOrdersType(){
        ResultSet resultSet = databaseHandler.getAllServiceOrdersTypeData();
        try{
            while (resultSet.next()){

                String id = resultSet.getString(Const.SERVICE_TYPE_ID);
                String name = resultSet.getString(Const.SERVICE_TYPE_NAME);
                String description = resultSet.getString(Const.SERVICE_TYPE_DESCRIPTION);
                double price = resultSet.getDouble(Const.SERVICE_TYPE_PRICE);
                int orderCount = Model.getInstance().databaseHandler.getOrderCountOfServiceOrdersType(id);
                String status = resultSet.getString(Const.SERVICE_TYPE_STATUS);
                ordersTypes.add(new ServiceOrdersType(id, name, description, price, orderCount, status));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ServiceOrdersType> sortServiceOrdersType(String sqlRequest){
        ObservableList<ServiceOrdersType> sortResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseHandler.search(sqlRequest);

        try{
            while (resultSet.next()) {
                String id = resultSet.getString(Const.SERVICE_TYPE_ID);
                String name = resultSet.getString(Const.SERVICE_TYPE_NAME);
                String description = resultSet.getString(Const.SERVICE_TYPE_DESCRIPTION);
                double price = resultSet.getDouble(Const.SERVICE_TYPE_PRICE);
                int orderCount = Model.getInstance().databaseHandler.getOrderCountOfServiceOrdersType(id);
                String status = resultSet.getString(Const.SERVICE_TYPE_STATUS);
                sortResults.add(new ServiceOrdersType(id, name, description, price, orderCount, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sortResults;
    }
    public ObservableList<Reservation> getReservations(){return reservations;}


    public void setReservations(){
        ResultSet reservationsData = databaseHandler.getAllReservationsData();

        try{
            while (reservationsData.next()){
                String idReservation = reservationsData.getString(Const.RESERVATION_ID);
                String idClient = reservationsData.getString(Const.RESERVATION_ID_CLIENT);
                String idRoom = reservationsData.getString(Const.RESERVATION_ID_ROOM);
                String idEmployee = reservationsData.getString(Const.RESERVATION_ID_EMPLOYEE);
                int numberOfGuests = reservationsData.getInt(Const.RESERVATION_NUMBER_OF_GUESTS);
                String reservationDate = reservationsData.getString(Const.RESERVATION_DATE);
                String checkInDate = reservationsData.getString(Const.RESERVATION_CHECK_IN_DATE);
                String checkOutDate = reservationsData.getString(Const.RESERVATION_CHECK_OUT_DATE);
                double price = reservationsData.getDouble(Const.RESERVATION_PRICE);
                String status = reservationsData.getString(Const.RESERVATION_STATUS);
                int tenure = Model.getInstance().databaseHandler.getStayDuration(idReservation);


                reservations.add(new Reservation(idReservation, idClient, idRoom, idEmployee,
                        numberOfGuests, reservationDate, checkInDate, checkOutDate,
                        price, status, tenure));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<Reservation> searchReservations(String sqlRequest){
        ObservableList<Reservation> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseHandler.search(sqlRequest);
        try{
            while (resultSet.next()) {
                String idReservation = resultSet.getString(Const.RESERVATION_ID);
                String idClient = resultSet.getString(Const.RESERVATION_ID_CLIENT);
                String idRoom = resultSet.getString(Const.RESERVATION_ID_ROOM);
                String idEmployee = resultSet.getString(Const.RESERVATION_ID_EMPLOYEE);
                int numberOfGuests = resultSet.getInt(Const.RESERVATION_NUMBER_OF_GUESTS);
                String reservationDate = resultSet.getString(Const.RESERVATION_DATE);
                String checkInDate = resultSet.getString(Const.RESERVATION_CHECK_IN_DATE);
                String checkOutDate = resultSet.getString(Const.RESERVATION_CHECK_OUT_DATE);
                double price = resultSet.getDouble(Const.RESERVATION_PRICE);
                String status = resultSet.getString(Const.RESERVATION_STATUS);
                int tenure = Model.getInstance().databaseHandler.getStayDuration(idReservation);
                searchResults.add(new Reservation(idReservation, idClient, idRoom, idEmployee,
                        numberOfGuests, reservationDate, checkInDate, checkOutDate,
                        price, status, tenure));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchResults;
    }

    public void setServiceOrders(){
        ResultSet resultSet = databaseHandler.getAllServiceOrdersData();

        try{
            while (resultSet.next()){
                String idServiceOrder = resultSet.getString(Const.SERVICE_ORDER_ID);
                String idClient = resultSet.getString(Const.SERVICE_ORDER_ID_CLIENT);
                String idEmployee = resultSet.getString(Const.SERVICE_ORDER_ID_EMPLOYEE);
                String orderDate = resultSet.getString(Const.SERVICE_ORDER_DATE);

                serviceOrders.add(new ServiceOrder(idServiceOrder, idClient, idEmployee, orderDate));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<ServiceOrder> searchServiceOrders(String sqlRequest){
        ObservableList<ServiceOrder> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseHandler.search(sqlRequest);
        try{
            while (resultSet.next()) {
                String idServiceOrder = resultSet.getString(Const.SERVICE_ORDER_ID);
                String idClient = resultSet.getString(Const.SERVICE_ORDER_ID_CLIENT);
                String idEmployee = resultSet.getString(Const.SERVICE_ORDER_ID_EMPLOYEE);
                String orderDate = resultSet.getString(Const.SERVICE_ORDER_DATE);
                searchResults.add(new ServiceOrder(idServiceOrder, idClient, idEmployee, orderDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchResults;
    }
}
