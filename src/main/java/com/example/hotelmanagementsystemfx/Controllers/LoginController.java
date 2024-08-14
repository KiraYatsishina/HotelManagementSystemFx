package com.example.hotelmanagementsystemfx.Controllers;


import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Entities.Employee;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Label error_label;

    @FXML
    private Button login_button;

    @FXML
    private TextField password_field;

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
        Optional<Employee> employee = Model.getInstance().getDatabaseHandler().getEmployeeDAO().getAccountData(userName_textField.getText(), password_field.getText());
        if(employee.isPresent()) {
            Model.getInstance().getViewFactory().setEmployeeAccount(employee.get());
            String profile = employee.get().profileProperty().get();
            AccountType accountType = AccountType.valueOf(profile.toUpperCase());
            switch (accountType){
                case MANAGER -> Model.getInstance().getViewFactory().showManagerWindow();
                case ADMINISTRATOR -> Model.getInstance().getViewFactory().showAdministratorWindow();
                case MAID -> Model.getInstance().getViewFactory().showMaidWindow();
            }
            Model.getInstance().getViewFactory().closeStage(stage);
        }else {
            new Shake(userName_textField).playAnim();
            new Shake(password_field).playAnim();
            error_label.setText("Error: No such login credentials.");
        }
    }
}