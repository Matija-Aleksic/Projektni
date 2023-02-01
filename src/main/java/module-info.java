module com.example.projektni {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens com.example.projektni to javafx.fxml;
    exports com.example.projektni;
}