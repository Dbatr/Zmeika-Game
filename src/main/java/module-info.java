module org.zmeika {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.zmeika to javafx.fxml;
    exports org.zmeika;
}