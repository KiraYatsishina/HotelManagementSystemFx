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

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseHandler = new DatabaseHandler();
        this.employees = FXCollections.observableArrayList();
        this.rooms = FXCollections.observableArrayList();
        this.ordersTypes = FXCollections.observableArrayList();
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
    public ObservableList<ServiceOrdersType> getServiceOrdersTypes(){
        return ordersTypes;
    }
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

    public ObservableList<Room> getRooms() {
        return rooms;
    }
}
