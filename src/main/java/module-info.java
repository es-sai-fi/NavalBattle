module org.example.navalbattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.navalbattle to javafx.fxml;
    exports org.example.navalbattle.model;
    exports org.example.navalbattle;
    exports org.example.navalbattle.controller;
    opens org.example.navalbattle.controller to javafx.fxml;
}