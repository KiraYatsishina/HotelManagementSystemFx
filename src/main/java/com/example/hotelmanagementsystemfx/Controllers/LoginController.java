package com.example.hotelmanagementsystemfx.Controllers;


import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.Models.Employee;
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
            }

            ResultSet resultSet = Model.getInstance().getDatabaseHandler().search("SELECT * FROM " + EMPLOYEE_TYPE_TABLE +
                    " WHERE " + EMPLOYEE_TYPE_ID + "='" + account.getString(Const.EMPLOYEE_ID_EMPLOYEE_TYPE) + "';");
            if (!resultSet.next()){
                error_label.setText("Error: No employee type found for this account.");
                return;
            }

            accountType = AccountType.valueOf(resultSet.getString(EMPLOYEE_TYPE_NAME));
            String fName = account.getString(EMPLOYEE_FIRSTNAME);
            String lName = account.getString(EMPLOYEE_LASTNAME);
            String email = account.getString(EMPLOYEE_EMAIL);
            String phoneNumber = account.getString(EMPLOYEE_PHONE_NUMBER);
            String profile = account.getString(EMPLOYEE_ID_EMPLOYEE_TYPE);
            if(profile.equals("1")) profile = "Manager";
            if(profile.equals("2")) profile = "Administrator";
            if(profile.equals("3")) profile = "Maid";
            String gender = account.getString(EMPLOYEE_GENDER);
            String status = account.getString(EMPLOYEE_STATUS);
            String login = account.getString(EMPLOYEE_LOGIN);
            String password = account.getString(EMPLOYEE_PASSWORD);
            Employee employeeAcc = new Employee(fName, lName, email, phoneNumber, profile, gender, login, password, status);
            Model.getInstance().getViewFactory().setEmployeeAccount(employeeAcc);

        }catch (SQLException e) {
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