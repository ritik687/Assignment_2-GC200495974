module com.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;
    requires com.google.gson;
    requires java.desktop;


    opens com.example.assignment2 to javafx.fxml, com.google.gson;
    exports com.example.assignment2;
    exports com.example.assignment2.Controllers;
    opens com.example.assignment2.Controllers to com.google.gson, javafx.fxml;
    exports com.example.assignment2.Utilities;
    opens com.example.assignment2.Utilities to com.google.gson, javafx.fxml;
    exports com.example.assignment2.Models;
    opens com.example.assignment2.Models to com.google.gson, javafx.fxml;
    exports com.example.assignment2.Interfaces;
    opens com.example.assignment2.Interfaces to com.google.gson, javafx.fxml;
}