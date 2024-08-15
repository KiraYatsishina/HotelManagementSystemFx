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
import java.util.Optional;
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
    private Circle status_circle;

    @FXML
    private Label status_label;

    @FXML
    private TableView<CompleteServiceOrder> completeServiceOrders_tableView;

    @FXML
    private TreeTableView<CompleteServiceOrder> serviceOrders_treeTableView;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, String> client_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, String> completeDate_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, String> employee_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, String> orderDate_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, Double> price_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, String> status_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, String> type_treeTableCol;

    @FXML
    private TreeTableColumn<CompleteServiceOrder, Integer> count_treeTableCol;

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
            fullTreeTableView();
        }
    }

    private void fullTreeTableView() {
        fillingColumns();

        TreeItem<CompleteServiceOrder> invisibleRoot = new TreeItem<>(null);
        invisibleRoot.setExpanded(true);

        List<ServiceOrder> serviceOrders = Model.getInstance().getDatabaseHandler().getServiceOrderDAO().getAll();
        for (ServiceOrder serviceOrder : serviceOrders){
            List<TreeItem<CompleteServiceOrder>> treeItemList = new ArrayList<>();
            for (CompleteServiceOrder completeServiceOrder : serviceOrder.getCompleteServiceOrders()) {
                TreeItem<CompleteServiceOrder> item = new TreeItem<>(completeServiceOrder);
                treeItemList.add(item);
            }
            CompleteServiceOrder rootServiceOrderItem  = new CompleteServiceOrder(
                    0, serviceOrder.idServiceOrderProperty().get(), 0, treeItemList.size(), serviceOrder.idEmployeeProperty().get(),
                    serviceOrder.getStatus(), serviceOrder.getCompleteDate()
            );
            rootServiceOrderItem.setClientName(serviceOrder.idClientProperty().get());
            rootServiceOrderItem.setOrderDate(serviceOrder.orderDateProperty().get());
            TreeItem<CompleteServiceOrder> root = new TreeItem<>(rootServiceOrderItem);
            root.getChildren().setAll(treeItemList);
            invisibleRoot.getChildren().add(root);
        }
        serviceOrders_treeTableView.setRoot(invisibleRoot);
        serviceOrders_treeTableView.setShowRoot(false);
    }

    private void fillingColumns(){
        client_treeTableCol.setCellValueFactory(cellData -> cellData.getValue().getValue().clientNameProperty());
        completeDate_treeTableCol.setCellValueFactory(cellData -> cellData.getValue().getValue().completeDateProperty());
        employee_treeTableCol.setCellValueFactory(cellData -> {
            Employee maid = cellData.getValue().getValue().getMaid();
            ServiceType serviceType = cellData.getValue().getValue().getServiceType();
            if(serviceType != null){
                boolean assigned = serviceType.assignedsProperty().get();
                if(!assigned) return new SimpleStringProperty("");
            }
            return new SimpleStringProperty(maid != null ? maid.getFullName() : "Unassigned");
        });
        orderDate_treeTableCol.setCellValueFactory(cellData -> cellData.getValue().getValue().orderDateProperty());
        price_treeTableCol.setCellValueFactory(cellData -> {
            ServiceType serviceType = cellData.getValue().getValue().getServiceType();
            int idServiceOrder = cellData.getValue().getValue().idServiceOrderProperty().get();

            Optional<ServiceOrder> optionalServiceOrder = Model.getInstance().getDatabaseHandler().getServiceOrderDAO().get(idServiceOrder);
            double price;
            if (serviceType != null) {
                price = serviceType.priceProperty().get();
            } else if (optionalServiceOrder.isPresent()) {
                price = optionalServiceOrder.get().priceProperty().get();
            } else {
                price = 2;
            }
            return new SimpleDoubleProperty(price).asObject();
        });
        status_treeTableCol.setCellValueFactory(cellData -> cellData.getValue().getValue().statusProperty());
        type_treeTableCol.setCellValueFactory(cellData -> {
            ServiceType serviceType = cellData.getValue().getValue().getServiceType();
            return new SimpleStringProperty(serviceType != null ? serviceType.nameProperty().get() : "");
        });
        count_treeTableCol.setCellValueFactory(cellData -> cellData.getValue().getValue().countProperty().asObject());
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
