package com.example.hotelmanagementsystemfx.Views;

import com.example.hotelmanagementsystemfx.Controllers.Manager.EmployeeCellController;
import com.example.hotelmanagementsystemfx.Models.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class EmployeeCellFactory extends ListCell<Employee> {
    @Override
    protected void updateItem(Employee employee, boolean empty) {
        super.updateItem(employee, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hotelmanagementsystemfx/Fxml/Manager/EmployeeCell.fxml"));
            EmployeeCellController cellController = new EmployeeCellController(employee);
            loader.setController(cellController);
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
