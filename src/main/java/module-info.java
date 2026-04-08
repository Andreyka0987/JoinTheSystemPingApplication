module org.example.jointhesystementry {
    requires javafx.fxml;
    requires java.sql;
    requires com.dustinredmond.fxtrayicon;
    requires javafx.graphics;
    requires javafx.controls;

    requires java.desktop;


    opens org.example.jointhesystementry to javafx.fxml;
    exports org.example.jointhesystementry;
}