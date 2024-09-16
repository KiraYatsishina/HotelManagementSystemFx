package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.CompleteServiceOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompleteServiceOrderDAO implements Dao<CompleteServiceOrder>{

    private Connection connection;

    public CompleteServiceOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<CompleteServiceOrder> get(int id) {
        String sql = "SELECT * FROM complete_service_order WHERE idCompleteServiceOrder = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                CompleteServiceOrder completeServiceOrder = new CompleteServiceOrder(
                        rs.getInt("idCompleteServiceOrder"),
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idServiceType"),
                        rs.getInt("count"),
                        rs.getInt("idEmployeeComplete"),
                        rs.getString("status")
                );
                return Optional.of(completeServiceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CompleteServiceOrder> getAll() {
        List<CompleteServiceOrder> completeServiceOrders = new ArrayList<>();
        String sql = "SELECT * FROM complete_service_order";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CompleteServiceOrder completeServiceOrder = new CompleteServiceOrder(
                        rs.getInt("idCompleteServiceOrder"),
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idServiceType"),
                        rs.getInt("count"),
                        rs.getInt("idEmployeeComplete"),
                        rs.getString("status")
                );
                completeServiceOrders.add(completeServiceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completeServiceOrders;
    }

    @Override
    public int save(CompleteServiceOrder completeServiceOrder) {
        String sql = "INSERT INTO complete_service_order (idServiceOrder, idServiceType, count, idEmployeeComplete, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, completeServiceOrder.idServiceOrderProperty().get());
            pstmt.setInt(2, completeServiceOrder.idServiceTypeProperty().get());
            pstmt.setInt(3, completeServiceOrder.countProperty().get());
            pstmt.setInt(4, completeServiceOrder.idEmployeeCompleteProperty().get());
            pstmt.setString(5, completeServiceOrder.statusProperty().get());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(CompleteServiceOrder completeServiceOrder, String[] params) {
        String sql = "UPDATE complete_service_order SET idServiceOrder = ?, idServiceType = ?, count = ?, idEmployeeComplete = ?, status = ? WHERE idCompleteServiceOrder = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, completeServiceOrder.idServiceOrderProperty().get());
            pstmt.setInt(2, completeServiceOrder.idServiceTypeProperty().get());
            pstmt.setInt(3, completeServiceOrder.countProperty().get());
            pstmt.setInt(4, completeServiceOrder.idEmployeeCompleteProperty().get());
            pstmt.setString(5, completeServiceOrder.statusProperty().get());
            pstmt.setInt(6, completeServiceOrder.idCompleteServiceOrderProperty().get());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CompleteServiceOrder completeServiceOrder) {
        String sql = "DELETE FROM complete_service_order WHERE idCompleteServiceOrder = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, completeServiceOrder.idCompleteServiceOrderProperty().get());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<CompleteServiceOrder> searchByRequest(String sqlRequest) {
        ObservableList<CompleteServiceOrder> completeServiceOrders = FXCollections.observableArrayList();
        try (PreparedStatement pstmt = connection.prepareStatement(sqlRequest)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CompleteServiceOrder completeServiceOrder = new CompleteServiceOrder(
                        rs.getInt("idCompleteServiceOrder"),
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idServiceType"),
                        rs.getInt("count"),
                        rs.getInt("idEmployeeComplete"),
                        rs.getString("status")
                );
                completeServiceOrders.add(completeServiceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completeServiceOrders;
    }

    public List<CompleteServiceOrder> getByField(String fieldName, int fieldValue) {
        List<CompleteServiceOrder> completeServiceOrders = new ArrayList<>();
        String sql = "SELECT * FROM complete_service_order WHERE " + fieldName + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, fieldValue);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CompleteServiceOrder completeServiceOrder = new CompleteServiceOrder(
                        rs.getInt("idCompleteServiceOrder"),
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idServiceType"),
                        rs.getInt("count"),
                        rs.getInt("idEmployeeComplete"),
                        rs.getString("status")
                );
                completeServiceOrders.add(completeServiceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completeServiceOrders;
    }
}
