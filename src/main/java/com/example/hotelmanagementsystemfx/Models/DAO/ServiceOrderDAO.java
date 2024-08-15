package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Models.Entities.ServiceOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceOrderDAO implements Dao<ServiceOrder>{

    private Connection connection;

    public ServiceOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ServiceOrder> get(int id) {
        String sql = "SELECT * FROM service_order WHERE idServiceOrder = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ServiceOrder serviceOrder = new ServiceOrder(
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idClient"),
                        rs.getInt("idEmployee"),
                        rs.getString("orderDate"),
                        rs.getDouble("price")
                );
                return Optional.of(serviceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ServiceOrder> getAll() {
        List<ServiceOrder> serviceOrders = new ArrayList<>();
        String sql = "SELECT * FROM service_order";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ServiceOrder serviceOrder = new ServiceOrder(
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idClient"),
                        rs.getInt("idEmployee"),
                        rs.getString("orderDate"),
                        rs.getDouble("price")
                );
                serviceOrders.add(serviceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceOrders;
    }

    @Override
    public void save(ServiceOrder serviceOrder) {
        String sql = "INSERT INTO service_order (idClient, idEmployee, orderDate, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, serviceOrder.idClientProperty().get());
            stmt.setInt(2, serviceOrder.idEmployeeProperty().get());
            stmt.setString(3, serviceOrder.orderDateProperty().get());
            stmt.setDouble(4, serviceOrder.priceProperty().get());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ServiceOrder serviceOrder, String[] params) {
        String sql = "UPDATE service_order SET idClient = ?, idEmployee = ?, orderDate = ?, price = ? WHERE idServiceOrder = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(params[0]));
            stmt.setInt(2, Integer.parseInt(params[1]));

            stmt.setDouble(4, Double.parseDouble(params[3]));
            stmt.setInt(5, serviceOrder.idServiceOrderProperty().get());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ServiceOrder serviceOrder) {
        String sql = "DELETE FROM service_order WHERE idServiceOrder = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, serviceOrder.idServiceOrderProperty().get());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ServiceOrder> searchByRequest(String sqlRequest) {
        ObservableList<ServiceOrder> serviceOrders = FXCollections.observableArrayList();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlRequest);
            while (rs.next()) {
                ServiceOrder serviceOrder = new ServiceOrder(
                        rs.getInt("idServiceOrder"),
                        rs.getInt("idClient"),
                        rs.getInt("idEmployee"),
                        rs.getString("orderDate"),
                        rs.getDouble("price")
                );
                serviceOrders.add(serviceOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceOrders;
    }



    public String getStatusServiceOrderById(int id) {
        return "In process";
    }

    public String getCompleteDateById(int id) {
        return "Not completed";
    }

//    public String getStatusServiceOrderById(String id) {
//        ResultSet resultSet = search("SELECT CASE \n" +
//                "        WHEN COUNT(complete_service_order.idCompleteServiceOrder) = \n" +
//                "\t\t\tSUM(CASE WHEN complete_service_order.status = 'Complete' THEN 1 ELSE 0 END) THEN 'Complete'\n" +
//                "        WHEN COUNT(complete_service_order.idCompleteServiceOrder) = \n" +
//                "\t\t\tSUM(CASE WHEN complete_service_order.status = 'Cancelled' THEN 1 ELSE 0 END) THEN 'Cancelled'\n" +
//                "        ELSE 'Accepted'\n" +
//                "    END AS service_order_status\n" +
//                "FROM service_order\n" +
//                "LEFT JOIN complete_service_order \n" +
//                "ON service_order.idServiceOrder = complete_service_order.idServiceOrder \n" +
//                "WHERE service_order.idServiceOrder = '" + id + "'\n" +
//                "GROUP BY service_order.idServiceOrder;");
//        try {
//            if(resultSet.next()) return resultSet.getString(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return "";
//    }
//    public ResultSet getAllServiceOrdersData(){return search("SELECT * FROM service_order;");}
//    public ResultSet getMonthCountServiceOrders(){
//        return search("SELECT DATE_FORMAT(orderDate, '%b') AS month, COUNT(*) AS countOrder\n" +
//                "FROM service_order inner JOIN complete_service_order \n" +
//                "ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
//                "GROUP BY DATE_FORMAT(orderDate, '%b'), MONTH(orderDate)\n" +
//                "ORDER BY MONTH(orderDate);");
//    }
//    public String getStatusServiceOrderById(String id) {
//        ResultSet resultSet = search("SELECT CASE \n" +
//                "        WHEN COUNT(complete_service_order.idCompleteServiceOrder) = \n" +
//                "\t\t\tSUM(CASE WHEN complete_service_order.status = 'Complete' THEN 1 ELSE 0 END) THEN 'Complete'\n" +
//                "        WHEN COUNT(complete_service_order.idCompleteServiceOrder) = \n" +
//                "\t\t\tSUM(CASE WHEN complete_service_order.status = 'Cancelled' THEN 1 ELSE 0 END) THEN 'Cancelled'\n" +
//                "        ELSE 'Accepted'\n" +
//                "    END AS service_order_status\n" +
//                "FROM service_order\n" +
//                "LEFT JOIN complete_service_order \n" +
//                "ON service_order.idServiceOrder = complete_service_order.idServiceOrder \n" +
//                "WHERE service_order.idServiceOrder = '" + id + "'\n" +
//                "GROUP BY service_order.idServiceOrder;");
//        try {
//            if(resultSet.next()) return resultSet.getString(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return "";
//    }
//    public ResultSet getServiceOrderOrderedMost(){
//        return search("SELECT Service_type.name as nameServiceType, Count(*) as countOrders\n" +
//                "FROM complete_service_order INNER JOIN service_type \n" +
//                "On service_type.idServiceType = complete_service_order.idServiceType\n" +
//                "GROUP BY nameServiceType\n" +
//                "ORDER BY countOrders desc\n" +
//                "LIMIT 1;");
//    }
//    public double getPriceServiceOrderById(String id){
//        ResultSet resultSet = search("SELECT SUM(service_type.price) \n" +
//                "FROM complete_service_order \n" +
//                "INNER JOIN service_type \n" +
//                "ON service_type.idServiceType = complete_service_order.idServiceType\n" +
//                "Where complete_service_order.idServiceOrder = '" + id + "'");
//        double price = 0;
//        try {
//            if(resultSet.next())
//                price = resultSet.getDouble(1);
//        } catch (SQLException e) {throw new RuntimeException(e);}
//        return price;
//    }
//    public String getCountServiceOrdersToday(){
//        LocalDate today = LocalDate.now();
//        String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
//        Employee employee = Model.getInstance().getViewFactory().getEmployeeAccount();
//        String email = employee.emailProperty().get();
//        String query = "SELECT COUNT(*) \n" +
//                "FROM service_order \n" +
//                "INNER JOIN employee ON employee.idEmployee = service_order.idEmployee\n" +
//                "INNER JOIN complete_service_order ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
//                "WHERE DATE(orderDate) = '" + formattedDate + "' \n" +
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
//    public int countServiceOrdersOfClient(String phoneNumber) {
//        ResultSet resultSet = search("SELECT Count(*) FROM service_order INNER JOIN client ON client.idClient = service_order.idClient\n" +
//                "INNER JOIN complete_service_order ON complete_service_order.idServiceOrder = service_order.idServiceOrder\n" +
//                "WHERE client.phoneNumber = '" + phoneNumber + "'\n");
//        try {
//            if(resultSet.next()) return resultSet.getInt(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return 0;
//    }
}
