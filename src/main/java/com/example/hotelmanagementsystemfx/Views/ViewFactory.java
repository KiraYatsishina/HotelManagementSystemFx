package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.Administrator.AdministratorController;
import com.example.hotelmanagementsystemfx.Controllers.Maid.MaidController;
import com.example.hotelmanagementsystemfx.Controllers.Manager.ManagerController;
import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    // Manager Views
    private final ObjectProperty<ManagerMenuOptions> managerSelectedMenuItem;
    private AnchorPane managerHomePageView;
    private AnchorPane employeesView;
    private AnchorPane addRoomView;
    private AnchorPane addEmployeeView;
    private AnchorPane addServiceOrdersTypeView;

    // Administrator Views
    private final ObjectProperty<AdministratorMenuOptions> administratorSelectedMenuItem;
    private AnchorPane administratorHomePageView;
    private AnchorPane newReservationView;
    private AnchorPane newServiceOrderView;

    // Maid Views
    private final ObjectProperty<MaidMenuOptions> maidSelectedMenuItem;
    private AnchorPane maidHomePageView;
    private AnchorPane myServiceOrdersView;

    // General
    private AnchorPane clientsView;
    private AnchorPane reservationsView;
    private AnchorPane serviceOrdersView;
    private AnchorPane roomsView;
    private AnchorPane settingsView;

    Employee employeeAccount;

    public ViewFactory(){
        this.managerSelectedMenuItem = new SimpleObjectProperty<>();
        this.administratorSelectedMenuItem = new SimpleObjectProperty<>();
        this.maidSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public Employee getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(Employee employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public ObjectProperty<ManagerMenuOptions> getManagerSelectedMenuItem() {
        return managerSelectedMenuItem;
    }
    public ObjectProperty<AdministratorMenuOptions> getAdministratorSelectedMenuItem() {
        return administratorSelectedMenuItem;
    }
    public ObjectProperty<MaidMenuOptions> getMaidSelectedMenuItem() {
        return maidSelectedMenuItem;
    }
    public AnchorPane getManagerHomePageView(){
        if(managerHomePageView == null){
            try{
                managerHomePageView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/HomePage.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return managerHomePageView;
    }
    public AnchorPane getEmployeesView() {
        if(employeesView == null){
            try{
                employeesView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/EmployeesCopy.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return employeesView;
    }


    public AnchorPane getClientsView() {
        if(clientsView == null){
            try{
                clientsView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Clients.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public AnchorPane getReservationsView() {
        if(reservationsView == null){
            try{
                reservationsView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Reservations.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return reservationsView;
    }
    public AnchorPane getServiceOrdersView() {
        if(serviceOrdersView == null){
            try{
                serviceOrdersView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/ServiceOrders.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return serviceOrdersView;
    }
    public AnchorPane getRoomsView() {
        if(roomsView == null){
            try{
                roomsView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Rooms.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return roomsView;
    }
    public AnchorPane getAddRoomView() {
        if(addRoomView == null){
            try{
                addRoomView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/AddRoom.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return addRoomView;
    }
    public AnchorPane getAddEmployeeView() {
        if(addEmployeeView == null){
            try{
                addEmployeeView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/AddEmployee.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return addEmployeeView;
    }
    public AnchorPane getAddServoceOrdersTypeView() {
        if(addServiceOrdersTypeView == null){
            try{
                addServiceOrdersTypeView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/AddServiceOrdersType.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return addServiceOrdersTypeView;
    }
    public AnchorPane getSettingsView() {
        if(settingsView == null){
            try{
                settingsView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Settings.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return settingsView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Login.fxml"));
        Scene scene = null;
        createStage(loader);
    }

    public void showManagerWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/Manager.fxml"));
        ManagerController managerController = new ManagerController();
        loader.setController(managerController);
        createStage(loader);
    }

    public void showAdministratorWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Administrator/Administrator.fxml"));
        AdministratorController administratorController = new AdministratorController();
        loader.setController(administratorController);
        createStage(loader);
    }
    public void showMaidWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Maid/Maid.fxml"));
        MaidController maidController = new MaidController();
        loader.setController(maidController);
        createStage(loader);
    }
    public void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());

        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Hotel Management System");

        stage.setResizable(false);
        stage.setScene(scene);
        Image icon = new Image("C:\\Users\\kirus\\IdeaProjects\\HotelManagementSystemFx\\src\\main\\resources\\com\\example\\hotelmanagementsystemfx\\Images\\hotelIcon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
    // Administrator Views
    public AnchorPane getAdministratorHomePageView() {
        if(administratorHomePageView == null){
            try{
                administratorHomePageView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Administrator/HomePage.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return administratorHomePageView;
    }
    public AnchorPane getNewReservationView() {
        if(newReservationView == null){
            try{
                newReservationView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Administrator/NewReservation.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return newReservationView;
    }
    public AnchorPane getNewServiceOrderView() {
        if(newServiceOrderView == null){
            try{
                newServiceOrderView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Administrator/NewServiceOrder.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return newServiceOrderView;
    }

    // Maid Views
    public AnchorPane getMaidHomePageView() {
        if(maidHomePageView == null){
            try{
                maidHomePageView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Maid/HomePage.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return maidHomePageView;
    }
    public AnchorPane getMyServiceOrdersView() {
        if(myServiceOrdersView == null){
            try{
                myServiceOrdersView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Maid/MyServiceOrders.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return myServiceOrdersView;
    }


}
