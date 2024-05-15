package com.example.hotelmanagementsystemfx.Controllers;


import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Views.AccountType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.hotelmanagementsystemfx.DB.Const.*;

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
        AccountType accountType = null;
        ResultSet account = Model.getInstance().getDatabaseHandler().getAccountData(userName_textField.getText(), password_field.getText());
        try {
            if (!account.next()){
                new Shake(userName_textField).playAnim();
                new Shake(password_field).playAnim();
                error_label.setText("Error: No such login credentials.");
                return;
            }else{
                Statement statement = Model.getInstance().getDatabaseHandler().getDbConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + EMPLOYEE_TYPE_TABLE + " WHERE " + EMPLOYEE_TYPE_ID + "='" + account.getString(Const.EMPLOYEE_ID_EMPLOYEE_TYPE) + "';");
                if (resultSet.next()) {
                    accountType = AccountType.valueOf(resultSet.getString(EMPLOYEE_TYPE_NAME));
                } else {
                    error_label.setText("Error: No employee type found for this account.");
                    return;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
             e.printStackTrace();
        }
        switch (accountType){
            case MANAGER -> Model.getInstance().getViewFactory().showManagerWindow();
            case ADMINISTRATOR -> Model.getInstance().getViewFactory().showAdministratorWindow();
            case MAID -> Model.getInstance().getViewFactory().showMaidWindow();
        }
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}