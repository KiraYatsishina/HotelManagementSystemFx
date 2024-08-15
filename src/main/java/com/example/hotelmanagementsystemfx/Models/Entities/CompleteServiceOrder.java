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
    private final IntegerProperty idEmployeeComplete;
    private final StringProperty status;
    private final StringProperty completeDate;

    private final Employee maid;
    private final ServiceType serviceType;

    public CompleteServiceOrder(int idCompleteServiceOrder, int idServiceOrder, int idServiceType, int idEmployeeComplete, String status, String completeDate) {
        this.idCompleteServiceOrder = new SimpleIntegerProperty(this, "idCompleteServiceOrder", idCompleteServiceOrder);
        this.idServiceOrder = new SimpleIntegerProperty(this, "idServiceOrder", idServiceOrder);
        this.idServiceType = new SimpleIntegerProperty(this, "idServiceType", idServiceType);
        this.idEmployeeComplete = new SimpleIntegerProperty(this, "idEmployeeComplete", idEmployeeComplete);
        this.status = new SimpleStringProperty(this, "status", status);
        this.completeDate = new SimpleStringProperty(this, "completeDate", completeDate);
        serviceType = Model.getInstance().getDatabaseHandler().getServiceTypeDAO().get(idServiceType).get();
        Optional<Employee> maid = Model.getInstance().getDatabaseHandler().getEmployeeDAO().get(idEmployeeComplete);
        this.maid = maid.isPresent() ? maid.get() : null;
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
    public IntegerProperty idEmployeeCompleteProperty() {
        return this.idEmployeeComplete;
    }
    public StringProperty statusProperty() {
        return this.status;
    }
    public StringProperty completeDateProperty() {
        return this.completeDate;
    }
}