package org.example.jointhesystementry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.example.jointhesystementry.module.EnterStream;
import org.example.jointhesystementry.module.PingStreamModule;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    public Button pingButton;
    public Button enterStreamButton;
    public Label streamStatus;
    public RadioButton pingRadioButton;

    public void pingStream(ActionEvent event) {
        PingStreamModule.parseInfo();
    }



    public void enterStream(ActionEvent event) {
        EnterStream.enterStream(streamStatus.getText());
    }


    public void isStreamCheckerOn(ActionEvent event) {
        if (pingRadioButton.isSelected() && !streamStatus.getText().equals("Stream is offline")){
            EnterStream.enterStreamRadioVersion();
        }
        else{
            System.out.println("Didn't entry the stream");
        }

    }
}