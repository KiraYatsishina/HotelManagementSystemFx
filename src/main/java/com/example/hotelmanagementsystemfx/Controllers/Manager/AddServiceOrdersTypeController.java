package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Entities.ServiceType;
import com.example.hotelmanagementsystemfx.Views.ServiceOrdersTypeCellFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddServiceOrdersTypeController implements Initializable {
    @FXML
    private Button create_button;

    @FXML
    private Label create_label;

    @FXML
    private TextField description_textField;

    @FXML
    private TextField name_textField;

    @FXML
    private TextField price_textField;

    @FXML
    private ListView<ServiceType> serviceOrdersTupe_listView;

    @FXML
    private ChoiceBox<String> sort_choiceBox;
    @FXML
    private CheckBox reverseSort_checkBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sort_choiceBox.getItems().addAll("Price", "Name", "Сount");
        initData();
        serviceOrdersTupe_listView.setItems(Model.getInstance().getServiceOrdersTypes());
        serviceOrdersTupe_listView.setCellFactory(e -> new ServiceOrdersTypeCellFactory());
        sort_choiceBox.setOnAction(actionEvent -> onTypeSort());
        create_button.setOnAction(actionEvent -> onTypeCreate());
    }

    private void onTypeCreate() {
        boolean hasError = false;
        String name = name_textField.getText();
        if(name.isEmpty() || name.length() < 3) {
            hasError = true;
            new Shake(name_textField).playAnim();
        }

        String description = description_textField.getText();
        if(description.isEmpty() || description.length() < 3) {
            hasError = true;
            new Shake(description_textField).playAnim();
        }
        String price = price_textField.getText();
        Double priceDouble = 0.0;
        if(price.isEmpty()) {
            hasError = true;
            new Shake(price_textField).playAnim();
        } else{
            try {
                priceDouble = Double.parseDouble(price);
                if (priceDouble < 1) throw new NumberFormatException();
            }catch (NumberFormatException e){
                hasError = true;
                new Shake(price_textField).playAnim();
            }
        }
        if(!hasError){
            Model.getInstance().getDatabaseHandler().createServiceOrdersType(name, description, priceDouble);
            create_label.setText("New service orders type is saved ✔");
            onTypeSort();
        }else{
            create_label.setText("New service orders type isn`t saved ❌");
            new Shake(create_label);
        }
    }

    private void onTypeSort() {
        String sqlRequest = makeRequest();
        ObservableList<ServiceType> searchResults = Model.getInstance().sortServiceOrdersType(sqlRequest);
        serviceOrdersTupe_listView.setItems(searchResults);
        serviceOrdersTupe_listView.setCellFactory(e -> new ServiceOrdersTypeCellFactory());

    }

    private String makeRequest() {
        String selectedSort = sort_choiceBox.getValue();
        if (selectedSort == null) {
            return "SELECT * FROM service_type";
        }
        boolean reverseSort = reverseSort_checkBox.isSelected();
        String order = reverseSort ? "DESC" : "ASC";

        String sortBy;
        switch (selectedSort) {
            case "Price" -> sortBy = "price";
            case "name" -> sortBy = "name";
            case "Сount" -> sortBy = "orderCount";
            default -> sortBy = "price";
        }
        if(sortBy.equals("orderCount")){
            return "SELECT service_type.idServiceType, service_type.name, service_type.description, service_type.price, service_type.status, COUNT(*) as orderCount\n" +
                    "FROM complete_service_order \n" +
                    "INNER JOIN service_type \n" +
                    "ON service_type.idServiceType = complete_service_order.idServiceType\n" +
                    "GROUP BY service_type.idServiceType, service_type.name, service_type.description, service_type.price, service_type.status\n" +
                    "ORDER BY orderCount " + order+ ";";
        }
        return "SELECT * FROM service_type ORDER BY " + sortBy + " " + order;
    }

    private void initData(){
        if(Model.getInstance().getServiceOrdersTypes().isEmpty()){
            Model.getInstance().setServiceOrdersType();
        }
    }
}
