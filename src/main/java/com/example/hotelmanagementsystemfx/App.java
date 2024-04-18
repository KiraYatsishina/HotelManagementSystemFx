package com.example.hotelmanagementsystemfx;

import com.example.hotelmanagementsystemfx.DB.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Hotel Management System");
        stage.setScene(scene);
        Image icon = new Image("C:\\Users\\kirus\\IdeaProjects\\HotelManagementSystemFx\\src\\main\\resources\\com\\example\\hotelmanagementsystemfx\\Images\\hotelIcon.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}