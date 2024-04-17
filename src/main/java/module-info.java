module com.example.hotelmanagementsystemfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.hotelmanagementsystemfx to javafx.fxml;
    exports com.example.hotelmanagementsystemfx;
}