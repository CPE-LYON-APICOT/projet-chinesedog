module com.example.chinesedog {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.chinesedog to javafx.fxml;
    exports com.example.chinesedog;
}