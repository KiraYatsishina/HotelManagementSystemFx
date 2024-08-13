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
        return new ClientDAO(dbConnection);
    }
    public CompleteServiceOrderDAO getCompleteServiceOrderDAO(){
        return new CompleteServiceOrderDAO(dbConnection);
    }
    public EmployeeDAO getEmployeeDAO(){
        return new EmployeeDAO(dbConnection);
    }
    public ReservationDAO getReservationDAO(){
        return new ReservationDAO(dbConnection);
    }
    public RoomDAO getRoomDAO(){
        return new RoomDAO(dbConnection);
    }
    public ServiceOrderDAO getServiceOrderDAO(){
        return new ServiceOrderDAO(dbConnection);
    }
    public ServiceTypeDAO getServiceTypeDAO(){
        return new ServiceTypeDAO(dbConnection);
    }
}
