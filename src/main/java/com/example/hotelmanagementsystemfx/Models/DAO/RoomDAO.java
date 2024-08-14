package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.Room;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAO implements Dao<Room>{

    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Room> get(int id) {
        String sql = "SELECT * FROM room WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Room room = new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomNumber"),
                        rs.getString("roomType"),
                        rs.getInt("capacity"),
                        rs.getDouble("pricePerNight"),
                        rs.getInt("floor"),
                        rs.getBoolean("hasRefrigerator"),
                        rs.getBoolean("hasAirConditioning")
                );
                return Optional.of(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomNumber"),
                        rs.getString("roomType"),
                        rs.getInt("capacity"),
                        rs.getDouble("pricePerNight"),
                        rs.getInt("floor"),
                        rs.getBoolean("hasRefrigerator"),
                        rs.getBoolean("hasAirConditioning")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void save(Room room) {
        String sql = "INSERT INTO room (roomNumber, roomType, capacity, pricePerNight, floor, hasRefrigerator, hasAirConditioning) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.roomNumberProperty().get());
            stmt.setString(2, room.roomTypeProperty().get());
            stmt.setInt(3, room.capacityProperty().get());
            stmt.setDouble(4, room.pricePerNightProperty().get());
            stmt.setInt(5, room.floorProperty().get());
            stmt.setBoolean(6, room.hasRefrigeratorProperty().get());
            stmt.setBoolean(7, room.hasAirConditioningProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Room room, String[] params) {
        String sql = "UPDATE room SET roomNumber = ?, roomType = ?, capacity = ?, pricePerNight = ?, floor = ?, hasRefrigerator = ?, hasAirConditioning = ? WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, params[0]);
            stmt.setString(2, params[1]);
            stmt.setInt(3, Integer.parseInt(params[2]));
            stmt.setDouble(4, Double.parseDouble(params[3]));
            stmt.setInt(5, Integer.parseInt(params[4]));
            stmt.setBoolean(6, Boolean.parseBoolean(params[5]));
            stmt.setBoolean(7, Boolean.parseBoolean(params[6]));
            stmt.setInt(8, room.idRoomProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Room room) {
        String sql = "DELETE FROM room WHERE idRoom = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.idRoomProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Room> searchByRequest(String sqlRequest) {
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlRequest);
            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomNumber"),
                        rs.getString("roomType"),
                        rs.getInt("capacity"),
                        rs.getDouble("pricePerNight"),
                        rs.getInt("floor"),
                        rs.getBoolean("hasRefrigerator"),
                        rs.getBoolean("hasAirConditioning")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public ObservableValue<String> getRoomStatus(int id) {
        return null;
    }


//    public void createRoom(Room room){
//        String query = "INSERT INTO " + ROOM_TABLE +
//                " (roomType, capacity, pricePerNight, floor, roomNumber, hasRefrigerator, hasAirConditioning) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = getDbConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, room.roomTypeProperty().get());
//            preparedStatement.setInt(2, room.capacityProperty().get());
//            preparedStatement.setDouble(3, room.pricePerNightProperty().get());
//            preparedStatement.setInt(4, room.floorProperty().get());
//            preparedStatement.setString(5, room.roomNumberProperty().get());
//            preparedStatement.setInt(6, Integer.parseInt(room.hasRefrigeratorProperty().get()));
//            preparedStatement.setInt(7, Integer.parseInt(room.hasAirConditioningProperty().get()));
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public ResultSet getAllRoomsData(){
//        return search("SELECT * FROM " + ROOM_TABLE + ";");
//    }
//    public String determineRoomStatusToday(String roomId) {
//
//        LocalDate today = LocalDate.now();
//        String status = "Available";
//        String date = String.valueOf(java.sql.Date.valueOf(today));
//        String query = "SELECT " + Const.RESERVATION_STATUS +
//                " FROM " + Const.RESERVATION_TABLE +
//                " WHERE " + Const.ROOM_ID + " = '" + roomId + "'" +
//                " AND '" + date + "' BETWEEN " + Const.RESERVATION_CHECK_IN_DATE + " AND " + Const.RESERVATION_CHECK_OUT_DATE + ";";
//
//        ResultSet resultSet = search(query);
//        try {
//            while (resultSet.next()) {
//                String reservationStatus = resultSet.getString(Const.RESERVATION_STATUS);
//                if ("Checked-in".equals(reservationStatus)) {
//                    status = "Occupied";
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return status;
//
//    }
//    public ResultSet getAllNumberOfGuests() {
//        ResultSet roomTypes = search("SELECT distinct capacity FROM room order by capacity asc");
//        return roomTypes;
//    }
//    public ResultSet getAllRoomTypes() {
//        ResultSet roomTypes = search("SELECT distinct room.roomType FROM room");
//        return roomTypes;
//    }
//    public boolean existRoomByNumber(String roomNumber){
//        ResultSet resultSet = search("SELECT * FROM room \n" +
//                "WHERE room.roomNumber = '" + roomNumber + "';");
//        try {
//            return resultSet.next() ? true : false;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public ResultSet getRoomMostOccupied(){
//        return search("SELECT room.roomNumber as rNumb, count(*) as countReserv " +
//                "FROM Reservation INNER JOIN Room ON Room.idRoom = Reservation.idRoom " +
//                "WHERE Reservation.status <> 'Cancelled' " +
//                "group by rNumb " +
//                "order by countReserv desc " +
//                "limit 1;");
//    }
}
