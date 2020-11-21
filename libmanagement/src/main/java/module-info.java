module com.mycompany.libmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;

    opens com.mycompany.libmanagement to javafx.fxml;
    exports com.mycompany.libmanagement;
}
