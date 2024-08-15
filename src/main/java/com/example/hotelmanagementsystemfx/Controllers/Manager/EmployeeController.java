package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Models.Entities.*;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.ManagerMenuOptions;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    private Button back_button;

    @FXML
    private Button change_button;

    @FXML
    private Label email_label;

    @FXML
    private AnchorPane employee_anchorPane;

    @FXML
    private Label fullName_label;

    @FXML
    private ImageView gender_image;

    @FXML
    private ChoiceBox<String> period_choiceBox;

    @FXML
    private Label phoneNumber_label;

    @FXML
    private Label profile_label;

    @FXML
    private TableView<Reservation> reservations_tableView;

    @FXML
    private Label salary_label;

    @FXML
    private TreeTableView<CompleteServiceOrder> serviceOrders_treeTableView;

    @FXML
    private Circle status_circle;

    @FXML
    private Label status_label;

    @FXML
    private TableView<CompleteServiceOrder> completeServiceOrders_tableView;

    @FXML
    private TreeTableColumn<ServiceOrder, String> client_treeTableCol;

    @FXML
    private TreeTableColumn<ServiceOrder, String> completeDate_treeTableCol;

    @FXML
    private TreeTableColumn<ServiceOrder, String> employee_treeTableCol;

    @FXML
    private TreeTableColumn<ServiceOrder, String> orderDate_treeTableCol;

    @FXML
    private TreeTableColumn<ServiceOrder, Double> price_treeTableCol;

    @FXML
    private TreeTableColumn<ServiceOrder, String> status_treeTableCol;

    @FXML
    private TreeTableColumn<ServiceOrder, String> type_treeTableCol;

    private Employee employee;

    public EmployeeController(Employee employee) {
        this.employee = employee;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back_button.setOnAction(event -> onBackButton());
        change_button.setOnAction(event -> onChangeButton());
        period_choiceBox.getItems().setAll("All", "Day", "Month", "Year");
        period_choiceBox.setValue("All");
        fullAccInfo();
        if(employee.profileProperty().get().equals("Manager")){
            reservations_tableView.setVisible(false);
            serviceOrders_treeTableView.setVisible(false);
            period_choiceBox.setVisible(false);
        }else if(employee.profileProperty().get().equals("Maid")){
            reservations_tableView.setVisible(false);
            serviceOrders_treeTableView.setVisible(false);
            completeServiceOrders_tableView.setVisible(true);
            fullServiceOrdersForMaid(employee);
        }else{
            fullTableView();
            //fullTreeTableView();
        }
    }

    private void fullTreeTableView() {
        List<ServiceOrder> serviceOrders = Model.getInstance().getDatabaseHandler().getServiceOrderDAO().getAll();
        for (ServiceOrder serviceOrder : serviceOrders){
            List<TreeItem<CompleteServiceOrder>> treeItemList = new ArrayList<>();
            for (CompleteServiceOrder completeServiceOrder : serviceOrder.getCompleteServiceOrders()) {

            }
        }

//        client_treeTableCol.setCellValueFactory(
//                (TreeTableColumn.CellDataFeatures<CompleteServiceOrder, String> param) ->
//                        new ReadOnlyStringWrapper(param.getValue().getValue().)
//        );
    }

    private void fullServiceOrdersForMaid(Employee employee) {

    }

    private void onChangeButton() {
        // всплывающее окно
    }

    private void onBackButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/EmployeesCopy.fxml"));
            AnchorPane employeesPane = loader.load();
            employee_anchorPane.getChildren().setAll(employeesPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fullAccInfo() {
        Image femaleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/femaleAcc.png"));
        Image maleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/maleAcc.png"));

        if(employee.genderProperty().get().equals("Female")) gender_image.setImage(femaleImage);
        else gender_image.setImage(maleImage);
        fullName_label.setText(employee.firstNameProperty().get() + " " + employee.lastNameProperty().get());
        profile_label.setText(employee.profileProperty().get());
        status_label.setText(employee.statusProperty().get());
        switch (employee.statusProperty().get()) {
            case "Employed" -> status_circle.setStyle("-fx-fill: #00FF00;");
            case "Terminated" -> status_circle.setStyle("-fx-fill: #708090;");
            case "Vocation" -> status_circle.setStyle("-fx-fill: #1E90FF;");
            case "Sick Leave" -> status_circle.setStyle("-fx-fill: #FFFF00;");
            case "Maternity Leave" -> status_circle.setStyle("-fx-fill: #800080;");
        }
        email_label.setText(employee.emailProperty().get());
        phoneNumber_label.setText(employee.phoneNumberProperty().get());
        salary_label.setText(employee.salaryProperty().get() + " $");
    }

    private void fullTableView() {
        reservations_tableView.getColumns().addAll(
                Reservation.getClientFullName(),
                Reservation.getRoomNumber(),
                Reservation.getEmployeeFullName(),
                Reservation.getNumberOfGuests(),
                Reservation.getReservationDate(),
                Reservation.getCheckInDate(),
                Reservation.getCheckOutDate(),
                Reservation.getTenure(),
                Reservation.getPrice(),
                Reservation.getStatus()

        );
        List<Reservation> reservations = Model.getInstance().getDatabaseHandler().getReservationDAO().getByEmployee(employee.idEmployeeProperty().get());
        ObservableList<Reservation> obsReservation = FXCollections.observableArrayList(reservations);
        reservations_tableView.setItems(obsReservation);
    }
}
