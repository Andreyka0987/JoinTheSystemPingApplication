module org.example.jointhesystementry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.jointhesystementry to javafx.fxml;
    exports org.example.jointhesystementry;
}