package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements Dao<Employee> {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Employee> get(int id) {
        String sql = "SELECT * FROM employee INNER JOIN employee_type ON employee.idEmployeeType = employee_type.idEmployeeType WHERE idEmployee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getDouble("salary")

                );
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee INNER JOIN employee_type ON employee.idEmployeeType = employee_type.idEmployeeType;";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employee (idEmployeeType, firstName, lastName, email, phoneNumber, gender, login, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, translateProfileToId(employee.profileProperty().get()));
            stmt.setString(2, employee.firstNameProperty().get());
            stmt.setString(3, employee.lastNameProperty().get());
            stmt.setString(4, employee.emailProperty().get());
            stmt.setString(5, employee.phoneNumberProperty().get());
            stmt.setString(6, employee.genderProperty().get());
            stmt.setString(7, employee.loginProperty().get());
            stmt.setString(8, employee.passwordProperty().get());
            stmt.setString(9, employee.statusProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String translateProfileToId(String profile) {
        switch (profile) {
            case "Manager": return "1";
            case "Administrator": return "2";
            case "Maid": return "3";
            default: throw new IllegalArgumentException("Unknown profile: " + profile);
        }
    }

    @Override
    public void update(Employee employee, String[] params) {
        String sql = "UPDATE employee SET idEmployeeType = ?, firstName = ?, lastName = ?, email = ?, phoneNumber = ?, gender = ?, login = ?, password = ?, status = ? WHERE idEmployee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, translateProfileToId(employee.profileProperty().get()));
            stmt.setString(2, params[0]); // firstName
            stmt.setString(3, params[1]); // lastName
            stmt.setString(4, params[2]); // email
            stmt.setString(5, params[3]); // phoneNumber
            stmt.setString(6, params[4]); // gender
            stmt.setString(7, params[5]); // login
            stmt.setString(8, params[6]); // password
            stmt.setString(9, params[7]); // status
            stmt.setInt(10, employee.idEmployeeProperty().get());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) {
        String sql = "DELETE FROM employee WHERE idEmployee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employee.idEmployeeProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeByField(int id, String field, String newValue) {
        String sql = "UPDATE employee SET " + field + " = ? WHERE idEmployee = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newValue);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Employee> searchByRequest(String sqlRequest) {
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlRequest);
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Optional<Employee> getAccountData(String login, String password) {
        String sql = "SELECT * FROM employee INNER JOIN employee_type ON employee.idEmployeeType = employee_type.idEmployeeType WHERE login = ? AND password = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("employee_type.name"),
                        rs.getDouble("salary")
                );
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Employee> getEmployeesByType(String profile) {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee INNER JOIN employee_type ON employee.idEmployeeType = employee_type.idEmployeeType WHERE name = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, profile);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("status"),
                        rs.getString("name"),
                        rs.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


//    public ResultSet getAllEmployeesData(){
//        return search("SELECT * FROM " + EMPLOYEE_TABLE + ";");
//    }
//
//    public ResultSet getAdministratorNames() {
//        return search("SELECT CONCAT(firstName, ' ', lastName) " +
//                "AS fullName FROM " + EMPLOYEE_TABLE +
//                " WHERE idEmployeeType = 2 AND status <> 'Terminated';");
//    }
//    public boolean existEmployee(String column, String value){
//        ResultSet resultSet = search("SELECT * FROM " + EMPLOYEE_TABLE + " WHERE " + column + " = '" + value + "';");
//        boolean exist = false;
//        try{
//            while (resultSet.next()){
//                exist = true;
//            }
//        }catch (SQLException e) { e.printStackTrace();}
//        return exist;
//    }
//    public void createEmployee(Employee employee){
//        String query = "INSERT INTO " + EMPLOYEE_TABLE +
//                " (firstName, lastName, email, phoneNumber, idEmployeeType, gender, login, password, status) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = getDbConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, employee.firstNameProperty().get());
//            preparedStatement.setString(2, employee.lastNameProperty().get());
//            preparedStatement.setString(3, employee.emailProperty().get());
//            preparedStatement.setString(4, employee.phoneNumberProperty().get());
//            preparedStatement.setString(5, employee.profileProperty().get());
//            preparedStatement.setString(6, employee.genderProperty().get());
//            preparedStatement.setString(7, employee.loginProperty().get());
//            preparedStatement.setString(8, employee.passwordProperty().get());
//            preparedStatement.setString(9, employee.statusProperty().get());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public void deleteEmployee(String password){
//        String query = "DELETE FROM " + EMPLOYEE_TABLE + " WHERE password = ?";
//        try (Connection connection = getDbConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, password);
//            preparedStatement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public ResultSet getAccountData(String login, String password){
//        return search("SELECT * FROM " + EMPLOYEE_TABLE +
//                " WHERE " + EMPLOYEE_LOGIN + "='" + login +
//                "' AND " + EMPLOYEE_PASSWORD + "='" + password +
//                "' AND " + EMPLOYEE_STATUS + "!='Terminated';");
//    }
//    public ResultSet getAdministratorMostReservations(){
//        return search("SELECT employee.idEmployee, employee.firstName, employee.lastName, COUNT(*) AS countReservations\n" +
//                "FROM reservation\n" +
//                "INNER JOIN employee ON employee.idEmployee = reservation.idEmployee\n" +
//                "GROUP BY employee.idEmployee, employee.firstName, employee.lastName\n" +
//                "ORDER BY countReservations DESC\n" +
//                "LIMIT 1;");
//    }
//    public ResultSet getMaidMostserviceOrders(){
//        return search("SELECT employee.idEmployee, employee.firstName, employee.lastName, COUNT(*) AS countServiceOrders\n" +
//                "FROM complete_service_order\n" +
//                "INNER JOIN employee ON employee.idEmployee = complete_service_order.idEmployeeComplete\n" +
//                "GROUP BY employee.idEmployee, employee.firstName, employee.lastName\n" +
//                "ORDER BY countServiceOrders DESC\n" +
//                "LIMIT 1;");
//    }
//    public void updateEmployeeColById(String email, String col, String newVal) {
//        String sql = "UPDATE employee SET " + col + " = ? WHERE email = ?";
//        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
//            statement.setString(1, newVal);
//            statement.setString(2, email);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public int getStayDuration(String reservationId) {
//        ResultSet resultSet = search("SELECT DATEDIFF(checkOutDate, checkInDate) AS stay_duration " +
//                "FROM reservation WHERE idReservation = '" + reservationId + "'");
//        int count = 0;
//        try {
//            if(resultSet.next())
//                count = resultSet.getInt(1);
//        } catch (SQLException e) {throw new RuntimeException(e);}
//        return count;
//    }
//    public String getTotalToday(){
//        LocalDate today = LocalDate.now();
//        String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
//        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
//        String email = employee.emailProperty().get();
//        String query = "SELECT SUM(total_price) AS total_sum\n" +
//                "FROM \n" +
//                "\t(SELECT SUM(service_type.price) AS total_price\n" +
//                "\tFROM service_order \n" +
//                "\tINNER JOIN employee ON employee.idEmployee = service_order.idEmployee\n" +
//                "\tINNER JOIN complete_service_order ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
//                "\tINNER JOIN service_type ON service_type.idServicetype = complete_service_order.idServiceType\n" +
//                "\tWHERE DATE(service_order.orderDate) = '" + formattedDate + "' \n" +
//                "\tAND employee.email = '" + email + "'\n" +
//                "UNION ALL\n" +
//                "\tSELECT SUM(reservation.price) AS total_price FROM reservation INNER JOIN employee ON employee.idEmployee = reservation.idEmployee\n" +
//                "\tWHERE DATE(reservationdate) = '" + formattedDate +"' \n" +
//                "\tAND employee.email = '" + email +"'\n" +
//                "    AND reservation.status <> 'Invalid'\n" +
//                "    AND reservation.status <> 'Cancelled')\n" +
//                "AS combined_prices";
//        ResultSet resultSet = search(query);
//        String count = null;
//        try {
//            if (resultSet.next())
//                count = resultSet.getString(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return count;
//    }
//    public int countReservationsOfClient(String phoneNumber){
//        ResultSet resultSet = search("SELECT Count(*) FROM reservation \n" +
//                "INNER join client \n" +
//                "ON client.idClient = reservation.idClient \n" +
//                "WHERE phoneNumber = '" + phoneNumber + "'");
//        try {
//            if(resultSet.next()) return resultSet.getInt(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return 0;
//    }
}
