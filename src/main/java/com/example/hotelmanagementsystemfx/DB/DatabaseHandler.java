package com.example.hotelmanagementsystemfx.DB;

import com.example.hotelmanagementsystemfx.Models.*;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public ResultSet getAllReservationsData(){
        return search("SELECT * FROM reservation;");
    }
    public ResultSet getAllServiceOrdersData(){return search("SELECT * FROM service_order;");}

    public ResultSet getClientById(String id){
        return search("SELECT *\n" +
                "FROM client WHERE idClient = '" + id + "';");
    }
    public ResultSet getIdClientByPhoneNumber(String phoneNumber){
        return search("SELECT idClient\n" +
                "FROM client WHERE phoneNumber = '" + phoneNumber + "';");
    }
    public ResultSet getAdministratorNames() {
        return search("SELECT CONCAT(firstName, ' ', lastName) " +
                "AS fullName FROM " + EMPLOYEE_TABLE +
                " WHERE idEmployeeType = 2 AND status <> 'Terminated';");
    }

    public ResultSet getAllServiceOrdersTypeData(){
        return search("SELECT * FROM " + SERVICE_TYPE_TABLE + ";");
    }
    public ResultSet getAllClientsData(){
        return search("SELECT * FROM " + CLIENT_TABLE + ";");
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

    public String createReservation(Reservation reservation) {
        String id = null;
        try {
            String sql = "INSERT INTO reservation (idClient, idRoom, idEmployee, numberOfGuests, reservationDate, checkInDate, checkOutDate, price, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, reservation.idClientProperty().get());
            statement.setString(2, reservation.idRoomProperty().get());
            statement.setString(3, reservation.idEmployeeProperty().get());
            statement.setInt(4, reservation.numberOfGuestsProperty().get());
            statement.setString(5, reservation.reservationDateProperty().get());
            statement.setString(6, reservation.checkInDateProperty().get());
            statement.setString(7, reservation.checkOutDateProperty().get());
            statement.setDouble(8, reservation.priceProperty().get());
            statement.setString(9, reservation.statusProperty().get());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next())
                    id = generatedKeys.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String createClient(Client client) {
        String clientId = null;
        try {
            String sql = "INSERT INTO client (firstName, lastName, phoneNumber, gender) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, client.firstNameProperty().get());
            statement.setString(2, client.lastNameProperty().get());
            statement.setString(3, client.phoneNumberProperty().get());
            statement.setString(4, client.genderProperty().get());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Вставка клиента не удалась, нет строк, затронутых операцией.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) clientId = generatedKeys.getString(1);
            else throw new SQLException("Не удалось получить идентификатор созданного клиента.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientId;
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
    public String existClient(Client client) {
        try {
            String sql = "SELECT idClient FROM client WHERE " +
                    "firstName = ? AND " +
                    "lastName = ? AND " +
                    "phoneNumber = ? AND " +
                    "gender = ?";

            PreparedStatement statement = dbConnection.prepareStatement(sql);

            statement.setString(1, client.firstNameProperty().get());
            statement.setString(2, client.lastNameProperty().get());
            statement.setString(3, client.phoneNumberProperty().get());
            statement.setString(4, client.genderProperty().get());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) return resultSet.getString("idClient");
            else return null;
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

    public void createServiceOrdersType(String name, String description, double price){
        String query = "INSERT INTO " + SERVICE_TYPE_TABLE +
                " (name, description, price, status) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(4, "Active");
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getMonthCountReservations(){
        return search("SELECT DATE_FORMAT(reservationDate, '%b') AS month, COUNT(*) / COUNT(DISTINCT YEAR(reservationDate)) AS avgReservations\n" +
                "    FROM Reservation\n" +
                "    GROUP BY DATE_FORMAT(reservationDate, '%b'), MONTH(reservationDate)\n" +
                "    ORDER BY MONTH(reservationDate);");
    }
    public ResultSet getMonthCountServiceOrders(){
        return search("SELECT DATE_FORMAT(orderDate, '%b') AS month, COUNT(*) AS countOrder\n" +
                "FROM service_order inner JOIN complete_service_order \n" +
                "ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
                "GROUP BY DATE_FORMAT(orderDate, '%b'), MONTH(orderDate)\n" +
                "ORDER BY MONTH(orderDate);");
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
                " WHERE " + Const.ROOM_ID + " = '" + roomId + "'" +
                " AND '" + date + "' BETWEEN " + Const.RESERVATION_CHECK_IN_DATE + " AND " + Const.RESERVATION_CHECK_OUT_DATE + ";";

        ResultSet resultSet = search(query);
        try {
            while (resultSet.next()) {
                String reservationStatus = resultSet.getString(Const.RESERVATION_STATUS);
                if ("Checked-in".equals(reservationStatus)) {
                    status = "Occupied";
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;

    }
    public String getCount(String tableName){
        ResultSet resultSet = search("SELECT count(*) FROM " + tableName + ";");
        String count = null;
        try {
            if(resultSet.next())
                count = resultSet.getString(1);
        } catch (SQLException e) {throw new RuntimeException(e);}
        return count;
    }
    public String getCountReservationToday(){
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
        String email = employee.emailProperty().get();
        String query = "SELECT count(*) \n" +
                "FROM reservation INNER JOIN employee \n" +
                "ON employee.idEmployee = reservation.idEmployee\n" +
                "WHERE DATE(reservationDate) = '" + formattedDate + "' \n" +
                "AND employee.email = '" + email + "';";
        ResultSet resultSet = search(query);
        String count = null;
        try {
            if (resultSet.next())
                count = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public String getCountServiceOrdersToday(){
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
        String email = employee.emailProperty().get();
        String query = "SELECT COUNT(*) \n" +
                "FROM service_order \n" +
                "INNER JOIN employee ON employee.idEmployee = service_order.idEmployee\n" +
                "INNER JOIN complete_service_order ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
                "WHERE DATE(orderDate) = '" + formattedDate + "' \n" +
                "AND employee.email = '" + email + "';";
        ResultSet resultSet = search(query);
        String count = null;
        try {
            if (resultSet.next())
                count = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public String getTotalToday(){
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
        String email = employee.emailProperty().get();
        String query = "SELECT SUM(total_price) AS total_sum\n" +
                "FROM \n" +
                "\t(SELECT SUM(service_type.price) AS total_price\n" +
                "\tFROM service_order \n" +
                "\tINNER JOIN employee ON employee.idEmployee = service_order.idEmployee\n" +
                "\tINNER JOIN complete_service_order ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
                "\tINNER JOIN service_type ON service_type.idServicetype = complete_service_order.idServiceType\n" +
                "\tWHERE DATE(service_order.orderDate) = '" + formattedDate + "' \n" +
                "\tAND employee.email = '" + email + "'\n" +
                "UNION ALL\n" +
                "\tSELECT SUM(reservation.price) AS total_price FROM reservation INNER JOIN employee ON employee.idEmployee = reservation.idEmployee\n" +
                "\tWHERE DATE(reservationdate) = '" + formattedDate +"' \n" +
                "\tAND employee.email = '" + email +"'\n" +
                "    AND reservation.status <> 'Invalid'\n" +
                "    AND reservation.status <> 'Cancelled')\n" +
                "AS combined_prices";
        ResultSet resultSet = search(query);
        String count = null;
        try {
            if (resultSet.next())
                count = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
    public int getOrderCountOfServiceOrdersType(String serviceTypeId) {
        ResultSet resultSet = search("SELECT count(*) FROM complete_service_order INNER JOIN service_type\n" +
                "ON service_type.idServiceType = complete_service_order.idServiceType\n" +
                "Where service_type.idServiceType = '" + serviceTypeId + "'\n" +
                "Group By service_type.name ;");
        try {
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int countReservationsOfClient(String phoneNumber){
        ResultSet resultSet = search("SELECT Count(*) FROM reservation \n" +
                "INNER join client \n" +
                "ON client.idClient = reservation.idClient \n" +
                "WHERE phoneNumber = '" + phoneNumber + "'");
        try {
            if(resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int countServiceOrdersOfClient(String phoneNumber) {
        ResultSet resultSet = search("SELECT Count(*) FROM service_order INNER JOIN client ON client.idClient = service_order.idClient\n" +
                "INNER JOIN complete_service_order ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
                "WHERE client.phoneNumber = '" + phoneNumber + "'\n");
        try {
            if(resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getStayDuration(String reservationId) {
        ResultSet resultSet = search("SELECT DATEDIFF(checkOutDate, checkInDate) AS stay_duration " +
                "FROM reservation WHERE idReservation = '" + reservationId + "'");
        int count = 0;
        try {
            if(resultSet.next())
                count = resultSet.getInt(1);
        } catch (SQLException e) {throw new RuntimeException(e);}
        return count;
    }
    public double getPriceServiceOrderById(String id){
        ResultSet resultSet = search("SELECT SUM(service_type.price) \n" +
                "FROM complete_service_order \n" +
                "INNER JOIN service_type \n" +
                "ON service_type.idServiceType = complete_service_order.idServiceType\n" +
                "Where complete_service_order.idServiceOrder = '" + id + "'");
        double price = 0;
        try {
            if(resultSet.next())
                price = resultSet.getDouble(1);
        } catch (SQLException e) {throw new RuntimeException(e);}
        return price;
    }

    public String getStatusServiceOrderById(String id) {
        ResultSet resultSet = search("SELECT CASE \n" +
                "        WHEN COUNT(complete_service_order.idCompleteServiceOrder) = \n" +
                "\t\t\tSUM(CASE WHEN complete_service_order.status = 'Complete' THEN 1 ELSE 0 END) THEN 'Complete'\n" +
                "        WHEN COUNT(complete_service_order.idCompleteServiceOrder) = \n" +
                "\t\t\tSUM(CASE WHEN complete_service_order.status = 'Cancelled' THEN 1 ELSE 0 END) THEN 'Cancelled'\n" +
                "        ELSE 'Accepted'\n" +
                "    END AS service_order_status\n" +
                "FROM service_order\n" +
                "LEFT JOIN complete_service_order \n" +
                "ON service_order.idServiceOrder = complete_service_order.idServiceOrder \n" +
                "WHERE service_order.idServiceOrder = '" + id + "'\n" +
                "GROUP BY service_order.idServiceOrder;");
        try {
            if(resultSet.next()) return resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public void updateEmployeeColById(String email, String col, String newVal) {
        String sql = "UPDATE employee SET " + col + " = ? WHERE email = ?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, newVal);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getAllRoomTypes() {
        ResultSet roomTypes = search("SELECT distinct room.roomType FROM room");
        return roomTypes;
    }

    public ResultSet getAllNumberOfGuests() {
        ResultSet roomTypes = search("SELECT distinct capacity FROM room order by capacity asc");
        return roomTypes;
    }


}
