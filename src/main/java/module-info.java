module com.example.projektni {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;


    opens com.example.projektni to javafx.fxml;
    exports com.example.projektni;
}