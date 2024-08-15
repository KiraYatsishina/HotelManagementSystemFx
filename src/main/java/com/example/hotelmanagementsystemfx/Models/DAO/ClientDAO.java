package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO implements Dao<Client> {

    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Client> get(int id) {
        String sql = "SELECT * FROM client WHERE idClient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client(
                        rs.getInt("idClient"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender")
                );
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("idClient"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO client (firstName, lastName, phoneNumber, gender) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.firstNameProperty().get());
            stmt.setString(2, client.lastNameProperty().get());
            stmt.setString(3, client.phoneNumberProperty().get());
            stmt.setString(4, client.genderProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client, String[] params) {
        String sql = "UPDATE client SET firstName = ?, lastName = ?, phoneNumber = ?, gender = ? WHERE idClient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, params[0]);
            stmt.setString(2, params[1]);
            stmt.setString(3, params[2]);
            stmt.setString(4, params[3]);
            stmt.setInt(5, client.idClientProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) {
        String sql = "DELETE FROM client WHERE idClient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, client.idClientProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> searchByRequest(String sqlRequest){
        ObservableList<Client> clients  = FXCollections.observableArrayList();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlRequest);

            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("idClient"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phoneNumber"),
                        rs.getString("gender")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

//    public ResultSet getAllClientsData(){
//        return search("SELECT * FROM " + CLIENT_TABLE + ";");
//    }
//    public ResultSet getClientById(String id){
//        return search("SELECT *\n" +
//                "FROM client WHERE idClient = '" + id + "';");
//    }
//
//    public String existClient(Client client) {
//        try {
//            String sql = "SELECT idClient FROM client WHERE " +
//                    "firstName = ? AND " +
//                    "lastName = ? AND " +
//                    "phoneNumber = ? AND " +
//                    "gender = ?";
//
//            PreparedStatement statement = dbConnection.prepareStatement(sql);
//
//            statement.setString(1, client.firstNameProperty().get());
//            statement.setString(2, client.lastNameProperty().get());
//            statement.setString(3, client.phoneNumberProperty().get());
//            statement.setString(4, client.genderProperty().get());
//
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) return resultSet.getString("idClient");
//            else return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public ResultSet getIdClientByPhoneNumber(String phoneNumber){
//        return search("SELECT idClient\n" +
//                "FROM client WHERE phoneNumber = '" + phoneNumber + "';");
//    }
//    public String createClient(Client client) {
//        String clientId = null;
//        try {
//            String sql = "INSERT INTO client (firstName, lastName, phoneNumber, gender) " +
//                    "VALUES (?, ?, ?, ?)";
//
//            PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            statement.setString(1, client.firstNameProperty().get());
//            statement.setString(2, client.lastNameProperty().get());
//            statement.setString(3, client.phoneNumberProperty().get());
//            statement.setString(4, client.genderProperty().get());
//
//            int affectedRows = statement.executeUpdate();
//
//            if (affectedRows == 0) {
//                throw new SQLException("Вставка клиента не удалась, нет строк, затронутых операцией.");
//            }
//
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) clientId = generatedKeys.getString(1);
//            else throw new SQLException("Не удалось получить идентификатор созданного клиента.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return clientId;
//    }
//    public ResultSet getClientMostCheckedIn(){
//        return search("SELECT Client.idClient, Client.firstName as fName, Client.lastName as lName, count(*) as countcheckin \n" +
//                "FROM Reservation INNER JOIN Client ON Client.idClient = Reservation.idClient\n" +
//                "WHERE Reservation.status <> 'Cancelled' AND Reservation.status <> 'No-show' AND Reservation.status <> 'Invalid' \n" +
//                "group by Client.idClient\n" +
//                "order by countcheckin desc\n" +
//                "limit 1;");
//    }


}
