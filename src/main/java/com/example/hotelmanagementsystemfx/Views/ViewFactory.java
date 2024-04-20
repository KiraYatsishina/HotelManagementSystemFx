package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.Manager.ManagerController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private AnchorPane clientsView;

    public ViewFactory(){
        this.managerSelectedMenuItem = new SimpleObjectProperty<>();
    }
    public ObjectProperty<ManagerMenuOptions> getManagerSelectedMenuItem() {
        return managerSelectedMenuItem;
    }
    public AnchorPane getManagerHomePage(){
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
                employeesView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/Employees.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return employeesView;
    }
    public AnchorPane getClientsView() {
        if(clientsView == null){
            try{
                clientsView = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/Clients.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsView;
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

    // Maid Views

}
