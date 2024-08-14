package com.example.hotelmanagementsystemfx.Controllers.Administrator;

import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private LineChart<String, Integer> totalSum_lineChart;
    @FXML
    private Label countEarning_label;
    @FXML
    private Label countReservationsToday_label;
    @FXML
    private Label countServiceOrdersToday_lable;
    @FXML
    private Label fullName_label;
    @FXML
    private Button update_button;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onUpdate();
        update_button.setOnAction(actionEvent -> onUpdate());
    }

    private void onUpdate() {
        String firstName = Model.getInstance().getViewFactory().getEmployeeAccount().firstNameProperty().get();
        String lastName = Model.getInstance().getViewFactory().getEmployeeAccount().lastNameProperty().get();
        fullName_label.setText(firstName + " " + lastName);
//        countReservationsToday_label.setText(Model.getInstance().getDatabaseHandler()
//                .getCountReservationToday() + " Reservations");
//        countServiceOrdersToday_lable.setText(Model.getInstance().getDatabaseHandler()
//                .getCountServiceOrdersToday() + " Service orders");
//        countEarning_label.setText("$  " + Model.getInstance().getDatabaseHandler()
//                .getTotalToday());
        chartCompletion();
    }

    private void chartCompletion(){
        totalSum_lineChart.getData().clear();
//        createSeries(Model.getInstance().getDatabaseHandler().getMonthCountReservations());
//        createSeries(Model.getInstance().getDatabaseHandler().getMonthCountServiceOrders());
    }
    private void createSeries(ResultSet resultSet){

        XYChart.Series<String, Integer> seriesHigh = new XYChart.Series<>();
        try {
            while (resultSet.next())
                seriesHigh.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
        }catch (Exception e){e.printStackTrace();}
        totalSum_lineChart.getData().add(seriesHigh);
    }
}
