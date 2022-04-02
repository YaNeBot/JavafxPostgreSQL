module com.example.securitydb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.securitydb to javafx.fxml;
    exports com.example.securitydb;
}