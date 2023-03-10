module maddux.firstscreen {
    requires javafx.controls;
    requires javafx.fxml;


    opens maddux.firstscreen to javafx.fxml;
    exports maddux.firstscreen;
}