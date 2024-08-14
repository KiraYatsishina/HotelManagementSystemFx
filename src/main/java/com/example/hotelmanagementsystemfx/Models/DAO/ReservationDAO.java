package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDAO implements Dao<Reservation>{

    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Reservation> get(int id) {
        String sql = "SELECT * FROM reservation WHERE idReservation = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("idReservation"),
                        rs.getInt("idClient"),
                        rs.getInt("idRoom"),
                        rs.getInt("idEmployee"),
                        rs.getInt("numberOfGuests"),
                        rs.getString("reservationDate"),
                        rs.getString("checkInDate"),
                        rs.getString("checkOutDate"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                return Optional.of(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("idReservation"),
                        rs.getInt("idClient"),
                        rs.getInt("idRoom"),
                        rs.getInt("idEmployee"),
                        rs.getInt("numberOfGuests"),
                        rs.getString("reservationDate"),
                        rs.getString("checkInDate"),
                        rs.getString("checkOutDate"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void save(Reservation reservation) {
        String sql = "INSERT INTO reservation (idClient, idRoom, idEmployee, numberOfGuests, reservationDate, checkInDate, checkOutDate, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.idClientProperty().get());
            stmt.setInt(2, reservation.idRoomProperty().get());
            stmt.setInt(3, reservation.idEmployeeProperty().get());
            stmt.setInt(4, reservation.numberOfGuestsProperty().get());
            stmt.setString(5, reservation.reservationDateProperty().get());
            stmt.setString(6, reservation.checkInDateProperty().get());
            stmt.setString(7, reservation.checkOutDateProperty().get());
            stmt.setDouble(8, reservation.priceProperty().get());
            stmt.setString(9, reservation.statusProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Reservation reservation, String[] params) {
        String sql = "UPDATE reservation SET idClient = ?, idRoom = ?, idEmployee = ?, numberOfGuests = ?, reservationDate = ?, checkInDate = ?, checkOutDate = ?, price = ?, status = ? WHERE idReservation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(params[0]));
            stmt.setInt(2, Integer.parseInt(params[1]));
            stmt.setInt(3, Integer.parseInt(params[2]));
            stmt.setInt(4, Integer.parseInt(params[3]));
            stmt.setString(5, params[4]);
            stmt.setString(6, params[5]);
            stmt.setString(7, params[6]);
            stmt.setDouble(8, Double.parseDouble(params[7]));
            stmt.setString(9, params[8]);
            stmt.setInt(10, reservation.idReservationProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reservation reservation) {
        String sql = "DELETE FROM reservation WHERE idReservation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.idReservationProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Reservation> searchByRequest(String sqlRequest) {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlRequest);
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("idReservation"),
                        rs.getInt("idClient"),
                        rs.getInt("idRoom"),
                        rs.getInt("idEmployee"),
                        rs.getInt("numberOfGuests"),
                        rs.getString("reservationDate"),
                        rs.getString("checkInDate"),
                        rs.getString("checkOutDate"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }


//    public ResultSet getAllReservationsData(){
//        return search("SELECT * FROM reservation;");
//    }
//    public ResultSet getMonthCountReservations(){
//        return search("SELECT DATE_FORMAT(reservationDate, '%b') AS month, COUNT(*) / COUNT(DISTINCT YEAR(reservationDate)) AS avgReservations\n" +
//                "    FROM Reservation\n" +
//                "    GROUP BY DATE_FORMAT(reservationDate, '%b'), MONTH(reservationDate)\n" +
//                "    ORDER BY MONTH(reservationDate);");
//    }
//    public ResultSet getReservationHifhestPrice(){
//        return search("SELECT idReservation, price\n" +
//                "FROM Reservation\n" +
//                "where price = (select Max(price) FROM Reservation);");
//    }
//    public ResultSet getReservationLongestTenure(){
//        return search("SELECT idReservation, DATEDIFF(checkOutDate, checkInDate) AS stayDuration\n" +
//                "FROM Reservation\n" +
//                "ORDER BY stayDuration DESC\n" +
//                "LIMIT 1;\n");
//    }
//    public String getCountReservationToday(){
//        LocalDate today = LocalDate.now();
//        String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
//        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
//        String email = employee.emailProperty().get();
//        String query = "SELECT count(*) \n" +
//                "FROM reservation INNER JOIN employee \n" +
//                "ON employee.idEmployee = reservation.idEmployee\n" +
//                "WHERE DATE(reservationDate) = '" + formattedDate + "' \n" +
//                "AND employee.email = '" + email + "';";
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
//    public String createReservation(Reservation reservation) {
//        String id = null;
//        try {
//            String sql = "INSERT INTO reservation (idClient, idRoom, idEmployee, numberOfGuests, reservationDate, checkInDate, checkOutDate, price, status) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            statement.setString(1, reservation.idClientProperty().get());
//            statement.setString(2, reservation.idRoomProperty().get());
//            statement.setString(3, reservation.idEmployeeProperty().get());
//            statement.setInt(4, reservation.numberOfGuestsProperty().get());
//            statement.setString(5, reservation.reservationDateProperty().get());
//            statement.setString(6, reservation.checkInDateProperty().get());
//            statement.setString(7, reservation.checkOutDateProperty().get());
//            statement.setDouble(8, reservation.priceProperty().get());
//            statement.setString(9, reservation.statusProperty().get());
//
//            int affectedRows = statement.executeUpdate();
//
//            if (affectedRows == 1) {
//                ResultSet generatedKeys = statement.getGeneratedKeys();
//                if (generatedKeys.next())
//                    id = generatedKeys.getString(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
}

