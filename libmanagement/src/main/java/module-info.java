module com.mycompany.libmanagement {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    uses java.sql.Driver;
    opens com.mycompany.libmanagement to javafx.fxml;
    opens com.pqm.pojo to javafx.base;
    exports com.mycompany.libmanagement;
    requires javafx.graphicsEmpty;
}
