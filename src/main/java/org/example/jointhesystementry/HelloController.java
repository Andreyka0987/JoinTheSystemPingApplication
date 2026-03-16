package org.example.jointhesystementry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.jointhesystementry.module.EnterStream;
import org.example.jointhesystementry.module.PingStreamModule;

public class HelloController {
    public Button pingButton;
    public Button enterStreamButton;
    public Label streamStatus;


    public void pingStream(ActionEvent event) {

        PingStreamModule.parseInfo();

    }


    public void enterStream(ActionEvent event) {
        EnterStream.enterStream(streamStatus.getText());
    }
}