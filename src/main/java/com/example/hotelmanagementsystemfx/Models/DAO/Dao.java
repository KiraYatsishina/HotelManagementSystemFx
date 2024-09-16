package com.example.hotelmanagementsystemfx.Models.DAO;

import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id);

    List<T> getAll();

    int save(T t);

    void update(T t, String[] params);

    void delete(T t);

    public ObservableList<T> searchByRequest(String sqlRequest);
}
