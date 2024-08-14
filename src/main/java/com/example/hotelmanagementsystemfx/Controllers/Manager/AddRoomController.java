package com.example.hotelmanagementsystemfx.Controllers.Manager;

import com.example.hotelmanagementsystemfx.Animations.Shake;
import com.example.hotelmanagementsystemfx.Models.Model;
import com.example.hotelmanagementsystemfx.Models.Entities.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {
    @FXML
    private RadioButton apartments_radioButton;

    @FXML
    private ToggleGroup capacity;

    @FXML
    private RadioButton capacity1_radioButton;

    @FXML
    private RadioButton capacity2_radioButton;

    @FXML
    private RadioButton capacity3_radioButton;

    @FXML
    private RadioButton capacity4_radioButton;

    @FXML
    private RadioButton capacity5_radioButton;

    @FXML
    private RadioButton capacity6_radioButton;

    @FXML
    private Button create_button;

    @FXML
    private Label create_label;

    @FXML
    private RadioButton executiveSuite_radioButton;

    @FXML
    private ToggleGroup flor;

    @FXML
    private RadioButton floor1_radioButton;

    @FXML
    private RadioButton floor2_radioButton;

    @FXML
    private RadioButton floor3_radioButton;

    @FXML
    private RadioButton floor4_radioButton;

    @FXML
    private RadioButton floor5_radioButton;

    @FXML
    private CheckBox hasAirConditioning_checkBox;

    @FXML
    private CheckBox hasRefrigerator_checkBox;

    @FXML
    private RadioButton improved_radioButton;

    @FXML
    private RadioButton lux_radioButton;

    @FXML
    private ToggleGroup number;

    @FXML
    private RadioButton number1_radioButton;

    @FXML
    private RadioButton number2_radioButton;

    @FXML
    private RadioButton number3_radioButton;

    @FXML
    private RadioButton number4_radioButton;

    @FXML
    private RadioButton number5_radioButton;

    @FXML
    private TextField price_textField;

    @FXML
    private ToggleGroup roomType;

    @FXML
    private RadioButton standard_radioButton;
    @FXML
    private Label type_label;
    @FXML
    private Label capacity_label;
    @FXML
    private Label floor_label;
    @FXML
    private Label number_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       //create_button.setOnAction(actionEvent -> onCreate());
    }

//    private void onCreate() {
//        boolean hasError = false;
//        String type = null;
//        if(standard_radioButton.isSelected())
//            type = "Standard";
//        else if(lux_radioButton.isSelected())
//            type = "Lux";
//        else if(improved_radioButton.isSelected())
//            type = "Improved";
//        else if(executiveSuite_radioButton.isSelected())
//            type = "Executive suite";
//        else if(apartments_radioButton.isSelected())
//            type = "Apartments";
//        else {
//            hasError = true;
//            new Shake(type_label).playAnim();
//        }
//
//        int capacity = 0;
//        if(capacity1_radioButton.isSelected())
//            capacity = 1;
//        else if(capacity2_radioButton.isSelected())
//            capacity = 2;
//        else if(capacity3_radioButton.isSelected())
//            capacity = 3;
//        else if(capacity4_radioButton.isSelected())
//            capacity = 4;
//        else if(capacity5_radioButton.isSelected())
//            capacity = 5;
//        else if(capacity6_radioButton.isSelected())
//            capacity = 6;
//        else{
//            hasError = true;
//            new Shake(capacity_label).playAnim();
//        }
//
//        int floor = 0;
//        if(floor1_radioButton.isSelected())
//            floor = 1;
//        else if(floor2_radioButton.isSelected())
//            floor = 2;
//        else if(floor3_radioButton.isSelected())
//            floor = 3;
//        else if(floor4_radioButton.isSelected())
//            floor = 4;
//        else if(floor5_radioButton.isSelected())
//            floor = 5;
//        else{
//            hasError = true;
//            new Shake(floor_label).playAnim();
//        }
//
//        String number = null;
//        if(number1_radioButton.isSelected())
//            number = "1";
//        else if(number2_radioButton.isSelected())
//            number = "2";
//        else if(number3_radioButton.isSelected())
//            number = "3";
//        else if(number4_radioButton.isSelected())
//            number = "4";
//        else if(number5_radioButton.isSelected())
//            number = "5";
//        else{
//            hasError = true;
//            new Shake(number_label).playAnim();
//        }
//        double price = 0;
//        String priceFromTextField = price_textField.getText();
//        if(priceFromTextField.isEmpty()){
//            hasError = true;
//            new Shake(price_textField).playAnim();
//        }else{
//            try{
//                price = Double.parseDouble(priceFromTextField);
//                if (price < 1) throw new NumberFormatException();
//            }catch (NumberFormatException e) {
//                hasError = true;
//                new Shake(price_textField).playAnim();
//            }
//        }
//        String hasRefrigerator = hasRefrigerator_checkBox.isSelected() ? "1" : "0";
//        String hasAirConditioning = hasAirConditioning_checkBox.isSelected() ? "1" : "0";
//        String roomNumber = "#" + floor + number;
//        boolean exist = Model.getInstance().getDatabaseHandler().existRoomByNumber(roomNumber);
//        if(!hasError && !exist){
//            Room room = new Room("", type, roomNumber, capacity, price, floor,
//                    hasRefrigerator, hasAirConditioning, "Available");
//            Model.getInstance().getDatabaseHandler().createRoom(room);
//            create_label.setText("New room is saved ✔");
//        }else{
//            create_label.setText("New room isn`t saved ❌");
//            new Shake(create_label);
//        }
//    }
}