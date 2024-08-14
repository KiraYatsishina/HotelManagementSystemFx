package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceTypeDAO implements Dao<ServiceType>{

    private Connection connection;

    public ServiceTypeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ServiceType> get(int id) {
        String sql = "SELECT * FROM service_type WHERE idServiceType = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ServiceType serviceType = new ServiceType(
                        rs.getInt("idServiceType"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                return Optional.of(serviceType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ServiceType> getAll() {
        List<ServiceType> serviceTypes = new ArrayList<>();
        String sql = "SELECT * FROM service_type";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ServiceType serviceType = new ServiceType(
                        rs.getInt("idServiceType"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                serviceTypes.add(serviceType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceTypes;
    }

    @Override
    public void save(ServiceType serviceType) {
        String sql = "INSERT INTO service_type (name, description, price, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, serviceType.nameProperty().get());
            stmt.setString(2, serviceType.descriptionProperty().get());
            stmt.setDouble(3, serviceType.priceProperty().get());
            stmt.setString(4, serviceType.statusProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ServiceType serviceType, String[] params) {
        String sql = "UPDATE service_type SET name = ?, description = ?, price = ?, status = ? WHERE idServiceType = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, params[0]); // name
            stmt.setString(2, params[1]); // description
            stmt.setDouble(3, Double.parseDouble(params[2])); // price
            stmt.setString(4, params[3]); // status
            stmt.setInt(5, serviceType.idProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ServiceType serviceType) {
        String sql = "DELETE FROM service_type WHERE idServiceType = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, serviceType.idProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ServiceType> searchByRequest(String sqlRequest) {
        ObservableList<ServiceType> serviceTypes = FXCollections.observableArrayList();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlRequest);

            while (rs.next()) {
                ServiceType serviceType = new ServiceType(
                        rs.getInt("idServiceType"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("status")
                );
                serviceTypes.add(serviceType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceTypes;
    }

//    public ResultSet getAllServiceOrdersTypeData(){
//        return search("SELECT * FROM " + SERVICE_TYPE_TABLE + ";");
//    }
//    public void createServiceOrdersType(String name, String description, double price){
//        String query = "INSERT INTO " + SERVICE_TYPE_TABLE +
//                " (name, description, price, status) " +
//                "VALUES (?, ?, ?, ?)";
//        try (Connection connection = getDbConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, description);
//            preparedStatement.setDouble(3, price);
//            preparedStatement.setString(4, "Active");
//            preparedStatement.executeUpdate();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public int getOrderCountOfServiceOrdersType(String serviceTypeId) {
//        ResultSet resultSet = search("SELECT count(*) FROM complete_service_order INNER JOIN service_type\n" +
//                "ON service_type.idServiceType = complete_service_order.idServiceType\n" +
//                "Where service_type.idServiceType = '" + serviceTypeId + "'\n" +
//                "Group By service_type.name ;");
//        try {
//            if(resultSet.next()){
//                return resultSet.getInt(1);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return 0;
//    }
}
