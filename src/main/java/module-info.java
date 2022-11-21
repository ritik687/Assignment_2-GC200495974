module com.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;
    requires com.google.gson;

    opens com.example.assignment2 to javafx.fxml, com.google.gson;
    exports com.example.assignment2;
}