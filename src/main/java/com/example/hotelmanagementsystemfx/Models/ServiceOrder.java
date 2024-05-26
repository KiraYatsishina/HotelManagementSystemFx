package com.example.hotelmanagementsystemfx.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceOrder {
    private final StringProperty idServiceOrder;
    private final StringProperty idClient;
    private final StringProperty idEmployee;
    private final StringProperty orderDate;


    public ServiceOrder(String idServiceOrder, String idClient, String idEmployee,String orderDate) {
        this.idServiceOrder = new SimpleStringProperty(this, "idServiceOrder", idServiceOrder);
        this.idClient = new SimpleStringProperty(this, "idClient", idClient);
        this.idEmployee = new SimpleStringProperty(this, "idEmployee", idEmployee);
        this.orderDate = new SimpleStringProperty(this, "orderDate", orderDate);
    }

    public StringProperty idServiceOrderProperty() {
        return this.idServiceOrder;
    }
    public StringProperty idClientProperty() {
        return this.idClient;
    }
    public StringProperty idEmployeeProperty() {
        return this.idEmployee;
    }
    public StringProperty orderDateProperty() {
        return this.orderDate;
    }
}
