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
    /*
    * Manager Section
    * */

    /*
     * Administrator Section
     * */

    /*
     * Maid Section
     * */

    /*
     * Utility Methods
     * */

}
