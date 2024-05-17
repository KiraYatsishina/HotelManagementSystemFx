package com.example.hotelmanagementsystemfx.DB;

import java.sql.*;
import java.time.LocalDate;

import static com.example.hotelmanagementsystemfx.DB.Const.*;


public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
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
    * Manager Section
    * */
    public ResultSet getAllEmployeesData(){
        return search("SELECT * FROM " + EMPLOYEE_TABLE + ";");
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
        return search("SELECT * FROM " + EMPLOYEE_TABLE +
                " WHERE " + EMPLOYEE_LOGIN + "='" + login +
                "' AND " + EMPLOYEE_PASSWORD + "='" + password +
                "' AND " + EMPLOYEE_STATUS + "!='Terminated';");
    }
    public ResultSet getAllRoomsData(){
        return search("SELECT * FROM " + ROOM_TABLE + ";");
    }
    public String determineRoomStatusToday(String roomId) {
        LocalDate today = LocalDate.now();
        String status = "Available";
        String date = String.valueOf(java.sql.Date.valueOf(today));
        String query = "SELECT " + Const.RESERVATION_STATUS +
                " FROM " + Const.RESERVATION_TABLE +
                " WHERE " + Const.ROOM_ID + " = " + roomId +
                " AND " + date + " BETWEEN " + Const.RESERVATION_CHECK_IN_DATE + " AND " + Const.RESERVATION_CHECK_OUT_DATE + ";";

        ResultSet resultSet = search(query);
        try {
            while (resultSet.next()) {
                String reservationStatus = resultSet.getString(Const.RESERVATION_STATUS);
                if ("Checked-In".equals(reservationStatus)) {
                    status = "Occupied";
                    break;
                } else if ("Pre-booked".equals(reservationStatus)) {
                    status = "Reserved";
                }
            }
        }catch (SQLException e){
            e.printStackTrace();}
        return status;
    }
}
