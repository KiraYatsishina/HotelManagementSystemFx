package com.example.hotelmanagementsystemfx.Models;

import static com.example.hotelmanagementsystemfx.DB.Const.*;
import com.example.hotelmanagementsystemfx.DB.DatabaseHandler;
import com.example.hotelmanagementsystemfx.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseHandler databaseHandler;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseHandler = new DatabaseHandler();
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
    public ResultSet getAccountData(String login, String password){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = this.databaseHandler.getDbConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + EMPLOYEE_LOGIN + "='" + login + "' AND " + EMPLOYEE_PASSWORD + "='" + password+ "';");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
