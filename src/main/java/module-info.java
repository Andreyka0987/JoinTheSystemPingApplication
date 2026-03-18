module org.example.jointhesystementry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.example.jointhesystementry to javafx.fxml;
    exports org.example.jointhesystementry;
}