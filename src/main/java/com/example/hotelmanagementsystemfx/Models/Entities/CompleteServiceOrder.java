package com.example.hotelmanagementsystemfx.Models.Entities;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Optional;

public class CompleteServiceOrder {
    private final IntegerProperty idCompleteServiceOrder;
    private final IntegerProperty idServiceOrder;
    private final IntegerProperty idServiceType;
    private final IntegerProperty count;
    private final IntegerProperty idEmployeeComplete;
    private final StringProperty status;
    private final StringProperty completeDate;

    private StringProperty clientName;
    private StringProperty orderDate;

    private final Employee maid;
    private final ServiceType serviceType;

    public CompleteServiceOrder(int idCompleteServiceOrder, int idServiceOrder, int idServiceType, int count,
                                int idEmployeeComplete, String status, String completeDate) {
        this.idCompleteServiceOrder = new SimpleIntegerProperty(this, "idCompleteServiceOrder", idCompleteServiceOrder);
        this.idServiceOrder = new SimpleIntegerProperty(this, "idServiceOrder", idServiceOrder);
        this.idServiceType = new SimpleIntegerProperty(this, "idServiceType", idServiceType);
        this.count = new SimpleIntegerProperty(this, "count", count);
        this.idEmployeeComplete = new SimpleIntegerProperty(this, "idEmployeeComplete", idEmployeeComplete);
        this.status = new SimpleStringProperty(this, "status", status);
        this.completeDate = new SimpleStringProperty(this, "completeDate", completeDate);

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
    public StringProperty completeDateProperty() {
        return this.completeDate;
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
