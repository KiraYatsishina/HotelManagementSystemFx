package com.example.hotelmanagementsystemfx.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceOrder {
    private final IntegerProperty idServiceOrder;
    private final IntegerProperty idClient;
    private final IntegerProperty idEmployee;
    private final StringProperty orderDate;


    public ServiceOrder(int idServiceOrder, int idClient, int idEmployee,String orderDate) {
        this.idServiceOrder = new SimpleIntegerProperty(this, "idServiceOrder", idServiceOrder);
        this.idClient = new SimpleIntegerProperty(this, "idClient", idClient);
        this.idEmployee = new SimpleIntegerProperty(this, "idEmployee", idEmployee);
        this.orderDate = new SimpleStringProperty(this, "orderDate", orderDate);
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
