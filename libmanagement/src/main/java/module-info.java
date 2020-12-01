module com.mycompany.libmanagement {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    uses java.sql.Driver;
    opens com.mycompany.libmanagement to javafx.fxml;
    exports com.mycompany.libmanagement;
    requires javafx.graphicsEmpty;
}
