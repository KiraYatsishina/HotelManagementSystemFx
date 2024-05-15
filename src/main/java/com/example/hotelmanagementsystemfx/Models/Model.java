package com.example.hotelmanagementsystemfx.Models;

import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.DB.DatabaseHandler;
import com.example.hotelmanagementsystemfx.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;


public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseHandler databaseHandler;

    private final ObservableList<Employee> employees;
    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseHandler = new DatabaseHandler();
        this.employees = FXCollections.observableArrayList();
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
    public void setEmployees(){
        ResultSet resultSet = databaseHandler.getAllEmployeesData();
        try{
            while (resultSet.next()){
                String fName = resultSet.getString(Const.EMPLOYEE_FIRSTNAME);
                String lName = resultSet.getString(Const.EMPLOYEE_LASTNAME);
                String email = resultSet.getString(Const.EMPLOYEE_EMAIL);
                String phoneNumber = resultSet.getString(Const.EMPLOYEE_PHONE_NUMBER);
                String profile = resultSet.getString(Const.EMPLOYEE_ID_EMPLOYEE_TYPE);
                String status = resultSet.getString(Const.EMPLOYEE_STATUS);
                employees.add(new Employee(fName + " " + lName, email, phoneNumber, profile, status));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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

}
