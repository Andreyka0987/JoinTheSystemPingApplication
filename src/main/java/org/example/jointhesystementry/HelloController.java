package org.example.jointhesystementry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import org.example.jointhesystementry.module.EnterStream;
import org.example.jointhesystementry.module.PingStreamModule;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    public Button pingButton;
    public Button enterStreamButton;
    public Label streamStatus;
    public RadioButton pingRadioButton;
    public Label enterStreamStatusLabel;
    public RadioButton isYouTubeRadio;


    public void pingStream(ActionEvent event) {


        if (PingStreamModule.checkInfo()){
            // don't forget to delete ! mark
            streamStatus.setText("Stream is online");
        }
        else  {
            streamStatus.setText("Stream is offline");
        }

    }



    public void enterStream(ActionEvent event) {
            EnterStream.enterStreamButton(streamStatus.getText());
    }


    public void isStreamCheckerOn(ActionEvent event) {
        if (pingRadioButton.isSelected()){
            EnterStream.isRadioIsChecked = true;

            try {
                EnterStream.enterStreamRadioButton();
            }catch (URISyntaxException e) {throw new RuntimeException(e);}catch (IOException e) {throw new RuntimeException(e);}

        }
        if (!pingRadioButton.isSelected()){
            EnterStream.isRadioIsChecked = false;

            try {
                EnterStream.enterStreamRadioButton();
            }catch (URISyntaxException e) {throw new RuntimeException(e);}catch (IOException e) {throw new RuntimeException(e);}

        }

    }

    public void isYoTubeOn(ActionEvent event) {
        if (isYouTubeRadio.isSelected()){
            EnterStream.isYouTubeRadioChecked = true;
        }
        else {
            EnterStream.isYouTubeRadioChecked = false;
        }
    }
}