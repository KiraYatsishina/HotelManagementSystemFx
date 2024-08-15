package com.example.hotelmanagementsystemfx.DB;

import com.example.hotelmanagementsystemfx.Models.DAO.*;

import java.sql.*;

public class DatabaseHandler extends Configs{

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ClientDAO getClientDAO(){
        try {
            return new ClientDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public CompleteServiceOrderDAO getCompleteServiceOrderDAO(){
        try {
            return new CompleteServiceOrderDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public EmployeeDAO getEmployeeDAO(){
        try {
            return new EmployeeDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ReservationDAO getReservationDAO(){
        try {
            return new ReservationDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public RoomDAO getRoomDAO(){
        try {
            return new RoomDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ServiceOrderDAO getServiceOrderDAO(){
        try {
            return new ServiceOrderDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ServiceTypeDAO getServiceTypeDAO(){
        try {
            return new ServiceTypeDAO(getDbConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}