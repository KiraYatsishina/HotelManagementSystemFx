package com.example.hotelmanagementsystemfx.DB;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

// test connection
//    public void sing(){
//        String insert = "INSERT INTO client(firstName,lastName,phoneNumber)VALUES(?,?,?)";
//        try(PreparedStatement prSt = getDbConnection().prepareStatement(insert);) {
//            prSt.setString(1, "firstName2");
//            prSt.setString(2, "lastName2");
//            prSt.setString(3, "phoneNumber2");
//            prSt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
