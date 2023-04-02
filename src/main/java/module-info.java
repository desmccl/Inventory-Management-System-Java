module com.example.newc4823 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.newc4823 to javafx.fxml;
    exports com.example.newc4823;
}