package com.example.hotelmanagementsystemfx.DB;

import com.example.hotelmanagementsystemfx.Models.Employee;
import com.example.hotelmanagementsystemfx.Models.Room;

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

    public boolean existEmployee(String column, String value){
        ResultSet resultSet = search("SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + column + " = '" + value + "';");
        boolean exist = false;
        try{
            while (resultSet.next()){
                exist = true;
            }
        }catch (SQLException e) { e.printStackTrace();}
        return exist;
    }
    public void createEmployee(Employee employee){
        String query = "INSERT INTO " + EMPLOYEE_TABLE +
                " (firstName, lastName, email, phoneNumber, idEmployeeType, gender, login, password, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employee.firstNameProperty().get());
            preparedStatement.setString(2, employee.lastNameProperty().get());
            preparedStatement.setString(3, employee.emailProperty().get());
            preparedStatement.setString(4, employee.phoneNumberProperty().get());
            preparedStatement.setString(5, employee.profileProperty().get());
            preparedStatement.setString(6, employee.genderProperty().get());
            preparedStatement.setString(7, employee.loginProperty().get());
            preparedStatement.setString(8, employee.passwordProperty().get());
            preparedStatement.setString(9, employee.statusProperty().get());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void createRoom(Room room){
        String query = "INSERT INTO " + ROOM_TABLE +
                " (roomType, capacity, pricePerNight, floor, roomNumber, hasRefrigerator, hasAirConditioning) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, room.roomTypeProperty().get());
            preparedStatement.setInt(2, room.capacityProperty().get());
            preparedStatement.setDouble(3, room.pricePerNightProperty().get());
            preparedStatement.setInt(4, room.floorProperty().get());
            preparedStatement.setString(5, room.roomNumberProperty().get());
            preparedStatement.setInt(6, Integer.parseInt(room.hasRefrigeratorProperty().get()));
            preparedStatement.setInt(7, Integer.parseInt(room.hasAirConditioningProperty().get()));

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean existRoomByNumber(String roomNumber){
        ResultSet resultSet = search("SELECT * FROM room \n" +
                "WHERE room.roomNumber = '" + roomNumber + "';");
        try {
            return resultSet.next() ? true : false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void deleteEmployee(String password){
        String query = "DELETE FROM " + EMPLOYEE_TABLE + " WHERE password = ?";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, password);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getRoomMostOccupied(){
        return search("SELECT room.roomNumber as rNumb, count(*) as countReserv " +
                "FROM Reservation INNER JOIN Room ON Room.idRoom = Reservation.idRoom " +
                "WHERE Reservation.status <> 'Cancelled' " +
                "group by rNumb " +
                "order by countReserv desc " +
                "limit 1;");
    }
    public ResultSet getClientMostCheckedIn(){
        return search("SELECT Client.idClient, Client.firstName as fName, Client.lastName as lName, count(*) as countcheckin \n" +
                "FROM Reservation INNER JOIN Client ON Client.idClient = Reservation.idClient\n" +
                "WHERE Reservation.status <> 'Cancelled' AND Reservation.status <> 'No-show' AND Reservation.status <> 'Invalid' \n" +
                "group by Client.idClient\n" +
                "order by countcheckin desc\n" +
                "limit 1;");
    }
    public ResultSet getServiceOrderOrderedMost(){
        return search("SELECT Service_type.name as nameServiceType, Count(*) as countOrders\n" +
                "FROM complete_service_order INNER JOIN service_type \n" +
                "On service_type.idServiceType = complete_service_order.idServiceType\n" +
                "GROUP BY nameServiceType\n" +
                "ORDER BY countOrders desc\n" +
                "LIMIT 1;");
    }
    public ResultSet getReservationHifhestPrice(){
        return search("SELECT idReservation, price\n" +
                "FROM Reservation\n" +
                "where price = (select Max(price) FROM Reservation);");
    }
    public ResultSet getReservationLongestTenure(){
        return search("SELECT idReservation, DATEDIFF(checkOutDate, checkInDate) AS stayDuration\n" +
                "FROM Reservation\n" +
                "ORDER BY stayDuration DESC\n" +
                "LIMIT 1;\n");
    }
    public ResultSet getMaidMostserviceOrders(){
        return search("SELECT employee.idEmployee, employee.firstName, employee.lastName, COUNT(*) AS countServiceOrders\n" +
                "FROM complete_service_order\n" +
                "INNER JOIN employee ON employee.idEmployee = complete_service_order.idEmployeeComplete\n" +
                "GROUP BY employee.idEmployee, employee.firstName, employee.lastName\n" +
                "ORDER BY countServiceOrders DESC\n" +
                "LIMIT 1;");
    }
    public ResultSet getAdministratorMostReservations(){
        return search("SELECT employee.idEmployee, employee.firstName, employee.lastName, COUNT(*) AS countReservations\n" +
                "FROM reservation\n" +
                "INNER JOIN employee ON employee.idEmployee = reservation.idEmployee\n" +
                "GROUP BY employee.idEmployee, employee.firstName, employee.lastName\n" +
                "ORDER BY countReservations DESC\n" +
                "LIMIT 1;");
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
    public String getCount(String tableName){
        ResultSet resultSet = search("SELECT count(*) FROM " + tableName + ";");
        String count = null;
        try {
            if(resultSet.next()){
                count = resultSet.getString(1);
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return count;
    }
}
