package com.example.hotelmanagementsystemfx.Controllers;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private TextField repeatPassword_textField;

    @FXML
    private TextField newPassword_textField;

    @FXML
    private Label newPasswordError_label;

    @FXML
    private Label passwordError_label;

    @FXML
    private TextField password_textField;

    @FXML
    private Label profile_label;

    @FXML
    private Label repeatPasswordError_label;

    @FXML
    private Button update_button;

    @FXML
    private Label updateError_label;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profile_label.setText(Model.getInstance().getViewFactory().getEmployeeAccount().profileProperty().get());
        update_button.setOnAction(actionEvent -> onUpdateButtonClicked());
    }

    private void onUpdateButtonClicked() {
        String password = password_textField.getText();
        String newPassword = newPassword_textField.getText();
        String repeatPassword = repeatPassword_textField.getText();

        passwordError_label.setVisible(false);
        newPasswordError_label.setVisible(false);
        repeatPasswordError_label.setVisible(false);
        updateError_label.setVisible(false);

        boolean hasError = false;

        if (password.isEmpty()) {
            hasError = true;
            new Shake(password_textField).playAnim();
        } else if (!password.equals(Model.getInstance().getViewFactory().getEmployeeAccount().passwordProperty().get())) {
            hasError = true;
            new Shake(password_textField).playAnim();
            passwordError_label.setVisible(true);
        }

        if (newPassword.isEmpty()) {
            hasError = true;
            new Shake(newPassword_textField).playAnim();

        }else if(newPassword.length() < 5){
            hasError = true;
            new Shake(newPassword_textField).playAnim();
            newPasswordError_label.setVisible(true);
        }
        if (repeatPassword.isEmpty()) {
            hasError = true;
            new Shake(repeatPassword_textField).playAnim();
        } else if (!newPassword.equals(repeatPassword)) {
            hasError = true;
            new Shake(newPassword_textField).playAnim();
            new Shake(repeatPassword_textField).playAnim();
            repeatPasswordError_label.setVisible(true);
        }

        if (hasError) {
            updateError_label.setText("Please correct the errors above.");
            updateError_label.setVisible(true);
            return;
        }
       Model.getInstance().getViewFactory().getEmployeeAccount().updatePassword(newPassword);
    }
}
