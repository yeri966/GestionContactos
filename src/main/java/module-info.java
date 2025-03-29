module co.edu.uniquindio.contactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens co.edu.uniquindio.contactos.app to javafx.graphics;
    opens co.edu.uniquindio.contactos.controllers to javafx.fxml;
    opens co.edu.uniquindio.contactos.model to javafx.base;

    exports co.edu.uniquindio.contactos.app;
    exports co.edu.uniquindio.contactos.controllers;
    exports co.edu.uniquindio.contactos.model;

}