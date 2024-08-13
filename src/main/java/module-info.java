module com.example.hotelmanagementsystemfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.hotelmanagementsystemfx to javafx.fxml;
    exports com.example.hotelmanagementsystemfx;

    exports com.example.hotelmanagementsystemfx.Controllers;
    exports com.example.hotelmanagementsystemfx.Controllers.Administrator;
    exports com.example.hotelmanagementsystemfx.Controllers.Manager;
    exports com.example.hotelmanagementsystemfx.Controllers.Maid;


    exports com.example.hotelmanagementsystemfx.DB;

    exports com.example.hotelmanagementsystemfx.Views;
    exports com.example.hotelmanagementsystemfx.Models;

    opens com.example.hotelmanagementsystemfx.Controllers;
    opens com.example.hotelmanagementsystemfx.Controllers.Manager;
    opens com.example.hotelmanagementsystemfx.Controllers.Administrator;
    opens com.example.hotelmanagementsystemfx.Controllers.Maid;
    exports com.example.hotelmanagementsystemfx.Entities;

}