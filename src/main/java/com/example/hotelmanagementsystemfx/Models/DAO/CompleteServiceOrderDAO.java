package com.example.hotelmanagementsystemfx.Models.DAO;

import com.example.hotelmanagementsystemfx.Entities.CompleteServiceOrder;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CompleteServiceOrderDAO implements Dao<CompleteServiceOrder>{

    private Connection connection;

    public CompleteServiceOrderDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<CompleteServiceOrder> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<CompleteServiceOrder> getAll() {
        return List.of();
    }

    @Override
    public void save(CompleteServiceOrder completeServiceOrder) {

    }

    @Override
    public void update(CompleteServiceOrder completeServiceOrder, String[] params) {

    }

    @Override
    public void delete(CompleteServiceOrder completeServiceOrder) {

    }

    @Override
    public ObservableList<CompleteServiceOrder> searchByRequest(String sqlRequest) {
        return null;
    }
}
