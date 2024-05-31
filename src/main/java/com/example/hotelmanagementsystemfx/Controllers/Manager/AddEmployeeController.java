package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.DB.Const;
import com.example.hotelmanagementsystemfx.Models.Employee;
import com.example.hotelmanagementsystemfx.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddEmployeeController implements Initializable {
    @FXML
    private Label createMessage_label;
    @FXML
    private Button create_button;
    @FXML
    private Button delete_button;
    @FXML
    private Label emailError_label;
    @FXML
    private Label email_label;
    @FXML
    private TextField email_textField;
    @FXML
    private Label employeeType_label;
    @FXML
    private ToggleGroup employeesType;
    @FXML
    private Label employeesTypeError_label;
    @FXML
    private VBox errors_VB;
    @FXML
    private Label firstName_label;
    @FXML
    private TextField firstName_textField;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Label genderErrorLabel;
    @FXML
    private Label lastName_label;
    @FXML
    private TextField lastName_textField;
    @FXML
    private Label login_label;
    @FXML
    private Label passWord_label;
    @FXML
    private Label passwordError_label;
    @FXML
    private TextField password_textField;
    @FXML
    private Label phoneNumberError_label;
    @FXML
    private Label phoneNumber_label;
    @FXML
    private TextField phoneNumber_textField;
    @FXML
    private RadioButton administrator_radioButton;
    @FXML
    private RadioButton maid_radioButton;
    @FXML
    private VBox account_VB;
    @FXML
    private RadioButton man_radioButton;
    @FXML
    private RadioButton manager_radioButton;
    @FXML
    private RadioButton women_radioButton;
    @FXML
    private ImageView account_image;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_button.setOnAction(actionEvent -> onCreate());
        delete_button.setOnAction(actionEvent -> onDelete());
    }

    private void onDelete() {
        Model.getInstance().getDatabaseHandler().deleteEmployee(passWord_label.getText());
        account_VB.setVisible(false);
    }

    private void onCreate() {
        account_VB.setVisible(false);
        clearErrorMessages();
        errors_VB.setVisible(true);
        String phonePattern = "^0\\d{9}$";
        String emailPattern = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

        boolean hasError = false;
        String firstName = firstName_textField.getText();
        if(firstName.isEmpty()) {
            new Shake(firstName_textField).playAnim();
            hasError = true;
        }
        String lastName = lastName_textField.getText();
        if(lastName.isEmpty()) {
            new Shake(lastName_textField).playAnim();
            hasError = true;
        }

        String email = email_textField.getText();
        if(email.isEmpty()) {
            new Shake(email_textField).playAnim();
            hasError = true;
        }else if(!Pattern.compile(emailPattern).matcher(email).matches()){
            emailError_label.setText("* Incorrect format.");
            hasError = true;
        }else if(Model.getInstance().getDatabaseHandler().existEmployee(Const.EMPLOYEE_EMAIL, email)){
            emailError_label.setText("* There's already an employee with this email.");
            hasError = true;
        }

        String phoneNumber = phoneNumber_textField.getText();
        if(phoneNumber.isEmpty()) {
            new Shake(phoneNumber_textField).playAnim();
            hasError = true;
        }else if(!Pattern.compile(phonePattern).matcher(phoneNumber).matches()){
            phoneNumberError_label.setText("* Incorrect format.");
            hasError = true;
        }else if(Model.getInstance().getDatabaseHandler().existEmployee(Const.EMPLOYEE_PHONE_NUMBER, phoneNumber)){
            phoneNumberError_label.setText("* There's already an employee with this phone number.");
            hasError = true;
        }

        String password = password_textField.getText();
        if(password.isEmpty()) {
            new Shake(password_textField).playAnim();
            hasError = true;
        }else if(password.length() < 5){
            passwordError_label.setText("* Incorrect format.");
            hasError = true;
        }else if(Model.getInstance().getDatabaseHandler().existEmployee(Const.EMPLOYEE_PASSWORD, password)){
            passwordError_label.setText("* There's already an employee with this password.");
            hasError = true;
        }

        String employeeType = null;
        if(manager_radioButton.isSelected()) employeeType = "1";
        else if(administrator_radioButton.isSelected()) employeeType = "2";
        else if(maid_radioButton.isSelected()) employeeType = "3";
        else {
            employeesTypeError_label.setText("* Select one options.");
            hasError = true;
        }

        String gender = null;
        if(women_radioButton.isSelected()) gender = "Female";
        else if(man_radioButton.isSelected()) gender = "Male";
        else {
            genderErrorLabel.setText("* Select one options.");
            hasError = true;
        }
        if(!hasError){
            Employee employee = new Employee("", firstName, lastName, email, phoneNumber, employeeType,
                    gender, lastName+firstName, password, "Employed");
            Model.getInstance().getDatabaseHandler().createEmployee(employee);
            createMessage_label.setText("A new employee account has been created ✔");
            account_VB.setVisible(true);
            firstName_label.setText(firstName);
            lastName_label.setText(lastName);

            if(employeeType.equals("1")) employeeType_label.setText("Manager");
            else if(employeeType.equals("2")) employeeType_label.setText("Administrator");
            else if(employeeType.equals("3")) employeeType_label.setText("Maid");

            email_label.setText(email);
            phoneNumber_label.setText(phoneNumber);
            login_label.setText(lastName+firstName);
            passWord_label.setText(password);

            Image femaleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/femaleAccBig.png"));
            Image maleImage = new Image(getClass().getResourceAsStream("/com/example/hotelmanagementsystemfx/Images/maleAcc.png"));
            if(employee.genderProperty().get().equals("Female")) account_image.setImage(femaleImage);
            else if(employee.genderProperty().get().equals("Male")) account_image.setImage(maleImage);
            clearInputFields();
        }else{
            createMessage_label.setText("A new employee account has not been created  ❌");
            new Shake(createMessage_label).playAnim();
        }
    }
    private void clearInputFields() {
        firstName_textField.clear();
        lastName_textField.clear();
        email_textField.clear();
        phoneNumber_textField.clear();
        password_textField.clear();

        manager_radioButton.setSelected(false);
        administrator_radioButton.setSelected(false);
        maid_radioButton.setSelected(false);

        women_radioButton.setSelected(false);
        man_radioButton.setSelected(false);

        clearErrorMessages();
    }
    private void clearErrorMessages() {
        emailError_label.setText("");
        firstName_textField.setStyle("");
        lastName_textField.setStyle("");
        passwordError_label.setText("");
        phoneNumberError_label.setText("");
        employeesTypeError_label.setText("");
        genderErrorLabel.setText("");
    }
}