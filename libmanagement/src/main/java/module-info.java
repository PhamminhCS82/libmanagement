module com.mycompany.libmanagement {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    uses java.sql.Driver;
    opens com.mycompany.libmanagement to javafx.fxml;
    exports com.mycompany.libmanagement;
    requires javafx.graphicsEmpty;
}
