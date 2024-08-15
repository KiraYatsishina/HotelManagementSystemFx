package com.example.hotelmanagementsystemfx.Models.Entities;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.controlsfx.control.PropertySheet;

import java.util.List;

public class ServiceOrder {
    private final IntegerProperty idServiceOrder;
    private final IntegerProperty idClient;
    private final IntegerProperty idEmployee;
    private final StringProperty orderDate;
    private final Client client;
    private final Employee employee;
    private final List<CompleteServiceOrder> completeServiceOrders;

    public ServiceOrder(int idServiceOrder, int idClient, int idEmployee,String orderDate) {
        this.idServiceOrder = new SimpleIntegerProperty(this, "idServiceOrder", idServiceOrder);
        this.idClient = new SimpleIntegerProperty(this, "idClient", idClient);
        this.idEmployee = new SimpleIntegerProperty(this, "idEmployee", idEmployee);
        this.orderDate = new SimpleStringProperty(this, "orderDate", orderDate);
        client = Model.getInstance().getDatabaseHandler().getClientDAO().get(idClient).get();
        employee = Model.getInstance().getDatabaseHandler().getEmployeeDAO().get(idEmployee).get();
        completeServiceOrders = Model.getInstance().getDatabaseHandler().getCompleteServiceOrderDAO().getByField("idServiceOrder", idServiceOrder);
    }


    public Employee getEmployee() {
        return employee;
    }

    public Client getClient() {
        return client;
    }

    public List<CompleteServiceOrder> getCompleteServiceOrders() {
        return completeServiceOrders;
    }

    public IntegerProperty idServiceOrderProperty() {
        return this.idServiceOrder;
    }
    public IntegerProperty idClientProperty() {
        return this.idClient;
    }
    public IntegerProperty idEmployeeProperty() {
        return this.idEmployee;
    }
    public StringProperty orderDateProperty() {
        return this.orderDate;
    }


}
