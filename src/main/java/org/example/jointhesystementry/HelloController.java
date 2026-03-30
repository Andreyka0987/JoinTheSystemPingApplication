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
    int a = 0;
    public void pingStream(ActionEvent event) {
        if (!PingStreamModule.checkInfo()){
            streamStatus.setText("Stream is online");
        }
        else {
            streamStatus.setText("Stream is offline");
        }
    }



    public void enterStream(ActionEvent event) {
        EnterStream.enterStream(streamStatus.getText(), false);
    }


    public void isStreamCheckerOn(ActionEvent event) {
        if (pingRadioButton.isSelected() && !streamStatus.getText().equals("Stream is offline")){
            EnterStream.enterStream(streamStatus.getText(),true);
            EnterStream.isRadioIsChecked = true;
        }
        if (!pingRadioButton.isSelected() && !streamStatus.getText().equals("Stream is offline")){
            EnterStream.isRadioIsChecked = false;
        }

    }



}