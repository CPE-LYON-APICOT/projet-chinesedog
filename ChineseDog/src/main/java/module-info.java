module com.example.chinesedog {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.chinesedog to javafx.fxml;
    exports com.example.chinesedog;
    exports com.example.chinesedog.Model;
    opens com.example.chinesedog.Model to javafx.fxml;
    exports com.example.chinesedog.Controller;
    opens com.example.chinesedog.Controller to javafx.fxml;
}