package com.example.hotelmanagementsystemfx.Controllers;


import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.AccountType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label error_label;

    @FXML
    private Button login_button;

    @FXML
    private TextField password_textField;

    @FXML
    private TextField userName_textField;

    @FXML
    private ImageView visibilityPassword_image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_button.setOnAction(actionEvent -> onLogin());
    }

    private void onLogin(){
        Stage stage =  (Stage) error_label.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.MANAGER){
            Model.getInstance().getViewFactory().showManagerWindow();
        }else if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMINISTRATOR){
            Model.getInstance().getViewFactory().showAdministratorWindow();
        }else{
            Model.getInstance().getViewFactory().showAdministratorWindow();
        }
    }
}