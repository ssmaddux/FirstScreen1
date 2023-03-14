module maddux.firstscreen {
    requires javafx.controls;
    requires javafx.fxml;


    opens maddux.firstscreen to javafx.fxml;
    exports maddux.firstscreen;
    exports maddux.firstscreen.model;
    opens maddux.firstscreen.model to javafx.fxml;
    exports maddux.firstscreen.controller;
    opens maddux.firstscreen.controller to javafx.fxml;
}