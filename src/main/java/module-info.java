module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports com.example.clock;
    opens com.example.clock to javafx.fxml;
    exports com.example.cars;
    opens com.example.cars to javafx.fxml;
    exports com.example.complexNumber;
    opens com.example.complexNumber to javafx.fxml;
    exports com.example.lottery;
    opens com.example.lottery to javafx.fxml;
    exports com.example.cars2;
    opens com.example.cars2 to javafx.fxml;
    exports com.example.shapes;
    opens com.example.shapes to javafx.fxml;
    exports com.example.polygon;
    opens com.example.polygon to javafx.fxml;
}