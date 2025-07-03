module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.example to javafx.fxml;
    opens com.example.controller.auth to javafx.fxml;

    exports com.example;
    exports com.example.controller.auth;
}
