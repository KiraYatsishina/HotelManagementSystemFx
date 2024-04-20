package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.Manager.ManagerController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ViewFactory {
    // Manager Views
    private AnchorPane managerHomePage;

    public ViewFactory(){
    }
    public AnchorPane getManagerHomePage(){
        if(managerHomePage == null){
            try{
                managerHomePage = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/ManagerMenu.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return managerHomePage;
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
