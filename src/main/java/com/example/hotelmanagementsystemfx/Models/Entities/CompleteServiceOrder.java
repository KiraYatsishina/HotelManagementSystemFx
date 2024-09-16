package com.example.hotelmanagementsystemfx.Models.Entities;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.beans.property.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class CompleteServiceOrder {
    private final IntegerProperty idCompleteServiceOrder;
    private final IntegerProperty idServiceOrder;
    private final IntegerProperty idServiceType;
    private final IntegerProperty count;
    private final IntegerProperty idEmployeeComplete;
    private final StringProperty status;

    private StringProperty clientName;
    private StringProperty orderDate;

    private final Employee maid;
    private final ServiceType serviceType;

    public CompleteServiceOrder(int idCompleteServiceOrder, int idServiceOrder, int idServiceType, int count,
                                int idEmployeeComplete, String status) {
        this.idCompleteServiceOrder = new SimpleIntegerProperty(this, "idCompleteServiceOrder", idCompleteServiceOrder);
        this.idServiceOrder = new SimpleIntegerProperty(this, "idServiceOrder", idServiceOrder);
        this.idServiceType = new SimpleIntegerProperty(this, "idServiceType", idServiceType);
        this.count = new SimpleIntegerProperty(this, "count", count);
        this.idEmployeeComplete = new SimpleIntegerProperty(this, "idEmployeeComplete", idEmployeeComplete);
        this.status = new SimpleStringProperty(this, "status", status);

        this.clientName = new SimpleStringProperty(this, "clientName", "");
        this.orderDate = new SimpleStringProperty(this, "orderDate", "");

        Optional<ServiceType> optionalServiceType = Model.getInstance().getDatabaseHandler().getServiceTypeDAO().get(idServiceType);
        if (optionalServiceType.isPresent()) {
            this.serviceType = optionalServiceType.get();
        } else {
            this.serviceType = null;
        }

        Optional<Employee> maid = Model.getInstance().getDatabaseHandler().getEmployeeDAO().get(idEmployeeComplete);
        this.maid = maid.orElse(null);
    }



    public static TableColumn<CompleteServiceOrder, String> getClient() {
        TableColumn<CompleteServiceOrder, String> col = new TableColumn<>("Client");
        col.setCellValueFactory(cellData -> {
            Optional<ServiceOrder> serviceOrder = Model.getInstance()
                    .getDatabaseHandler()
                    .getServiceOrderDAO()
                    .get(cellData.getValue().idServiceOrder.get());

            if (serviceOrder.isPresent()) {
                cellData.getValue().setClientName(serviceOrder.get().idClientProperty().get());
                return new SimpleStringProperty(cellData.getValue().clientNameProperty().get());
            } else {
                return new SimpleStringProperty("Unknown Client");
            }
        });
        return col;
    }

    public static TableColumn<CompleteServiceOrder, String> getOrderDate() {
        TableColumn<CompleteServiceOrder, String> col = new TableColumn<>("Order Date");
        col.setCellValueFactory(cellData -> {
            Optional<ServiceOrder> serviceOrder = Model.getInstance()
                    .getDatabaseHandler()
                    .getServiceOrderDAO()
                    .get(cellData.getValue().idServiceOrder.get());

            if (serviceOrder.isPresent()) {
                cellData.getValue().setOrderDate(serviceOrder.get().orderDateProperty().get());
                return new SimpleStringProperty(cellData.getValue().orderDateProperty().get());
            } else {
                return new SimpleStringProperty("Unknown Date");
            }
        });
        return col;
    }

    public static TableColumn<CompleteServiceOrder, String> getType() {
        TableColumn<CompleteServiceOrder, String> col = new TableColumn<>("Type");
        col.setCellValueFactory(cellData -> {
            ServiceType serviceType = cellData.getValue().getServiceType();
            if (serviceType != null) {
                return new SimpleStringProperty(serviceType.nameProperty().get());
            } else {
                return new SimpleStringProperty("Unknown Type");
            }
        });
        return col;
    }

    public static TableColumn<CompleteServiceOrder, Double> getPrice() {
        TableColumn<CompleteServiceOrder, Double> col = new TableColumn<>("Price");
        col.setCellValueFactory(cellData -> {
            ServiceType serviceType = cellData.getValue().getServiceType();
            if (serviceType != null) {
                return new SimpleDoubleProperty(serviceType.priceProperty().get()).asObject();
            } else {
                return new SimpleDoubleProperty(0.0).asObject();
            }
        });
        return col;
    }

    public static TableColumn<CompleteServiceOrder, String> getAdministratorFullName() {
        TableColumn<CompleteServiceOrder, String> col = new TableColumn<>("Administrator");
        col.setCellValueFactory(cellData -> {
            Optional<ServiceOrder> serviceOrder = Model.getInstance()
                    .getDatabaseHandler()
                    .getServiceOrderDAO()
                    .get(cellData.getValue().idServiceOrder.get());

            if (serviceOrder.isPresent()) {
                Employee admin = serviceOrder.get().getEmployee();
                if (admin != null) {
                    return new SimpleStringProperty(admin.getFullName());
                } else {
                    return new SimpleStringProperty("Unknown Administrator");
                }
            } else {
                return new SimpleStringProperty("Unknown Administrator");
            }
        });
        return col;
    }

    public static TableColumn<CompleteServiceOrder, Integer> getCount() {
        TableColumn<CompleteServiceOrder, Integer> col = new TableColumn<>("Count");
        col.setCellValueFactory(new PropertyValueFactory<>("count"));
        return col;
    }

    public static TableColumn<CompleteServiceOrder, String> getStatus() {
        TableColumn<CompleteServiceOrder, String> col = new TableColumn<>("Status");
        col.setCellValueFactory(new PropertyValueFactory<>("status"));
        return col;
    }


    public Employee getMaid() {
        return maid;
    }
    public ServiceType getServiceType() {
        return serviceType;
    }

    public IntegerProperty idCompleteServiceOrderProperty() {
        return this.idCompleteServiceOrder;
    }
    public IntegerProperty idServiceOrderProperty() {
        return this.idServiceOrder;
    }
    public IntegerProperty idServiceTypeProperty() {
        return this.idServiceType;
    }
    public IntegerProperty countProperty() {
        return this.count;
    }
    public IntegerProperty idEmployeeCompleteProperty() {
        return this.idEmployeeComplete;
    }
    public StringProperty statusProperty() {
        return this.status;
    }
    public StringProperty clientNameProperty() {
        return this.clientName;
    }
    public StringProperty orderDateProperty() {
        return this.orderDate;
    }

    public void setClientName(int clientId) {
        Optional<Client> optionalClient = Model.getInstance().getDatabaseHandler().getClientDAO().get(clientId);
        if (optionalClient.isPresent()) {
            this.clientName.set(optionalClient.get().getFullName());
        } else {
            this.clientName.set("Unknown Client");
        }
    }

    public void setOrderDate(String date) {
        orderDate.set(date);
    }
}
