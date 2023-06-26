module com.example.towersofhanoi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.sql;
    requires java.desktop;

    requires mongo.java.driver;

    exports com.example.towersofhanoi;
    opens com.example.towersofhanoi to javafx.fxml;
    exports com.example.towersofhanoi.Controller;
    opens com.example.towersofhanoi.Controller to javafx.fxml;
    exports com.example.towersofhanoi.Model;
    opens com.example.towersofhanoi.Model to javafx.fxml;
    exports com.example.towersofhanoi.View;
    opens com.example.towersofhanoi.View to javafx.fxml;
}