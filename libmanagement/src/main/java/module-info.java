module com.mycompany.libmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.libmanagement to javafx.fxml;
    exports com.mycompany.libmanagement;
}
