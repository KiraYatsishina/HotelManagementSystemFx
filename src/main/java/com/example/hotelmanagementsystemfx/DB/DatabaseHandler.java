package com.example.hotelmanagementsystemfx.DB;

import java.sql.*;
import static com.example.hotelmanagementsystemfx.DB.Const.*;


public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    /*
    * Manager Section
    * */
    public ResultSet getAllEmployeesData(){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = getDbConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + EMPLOYEE_TABLE + ";");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet search(String sqlRequest){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = getDbConnection().createStatement();
            resultSet = statement.executeQuery(sqlRequest);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultSet;
    }
    /*
     * Administrator Section
     * */

    /*
     * Maid Section
     * */

    /*
     * Utility Methods
     * */
    public ResultSet getAccountData(String login, String password){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = getDbConnection().createStatement(); //
            resultSet = statement.executeQuery("SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + EMPLOYEE_LOGIN + "='" + login + "' AND " + EMPLOYEE_PASSWORD + "='" + password+ "';");
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


}
