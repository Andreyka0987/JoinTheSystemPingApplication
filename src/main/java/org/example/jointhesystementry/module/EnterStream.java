package org.example.jointhesystementry.module;

import javafx.application.Platform;
import org.example.jointhesystementry.HelloController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;


public class EnterStream {
    private static boolean isStreamOnline = false;
    private static boolean isAlreadyEntered = false;
    public static boolean isRadioIsChecked = false;
    private static HelloController controller;
    private static boolean isCheckedIn = false;
    private static Timer timer = new Timer();
    private static Timer statusTimer = new Timer();



    public static void enterStreamButton(String currentStatus){

        if (currentStatus.equals("Stream is offline")){isStreamOnline = false;} else{isStreamOnline = true;}

        if (isStreamOnline){
            try {
                Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            controller.enterStreamStatusLabel.setText("Stream Is currently offline!");


           timer.schedule(new TimerTask() {
               @Override
               public void run() {
                   Platform.runLater(new Runnable() {  // With this class i can refer to the FX main Thread
                       @Override
                       public void run() {
                           controller.enterStreamStatusLabel.setText("");
                       }});
               }},5000);
        }
    }


    public static void enterStreamRadioButton(String currentStatus) throws URISyntaxException, IOException {

        if (currentStatus.equals("Stream is offline")){isStreamOnline = false;} else{isStreamOnline = true;}


            isAlreadyEntered = true;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    if (isRadioIsChecked) {

                        if (PingStreamModule.checkInfo()){isStreamOnline = true;}
                        else{isStreamOnline = false;}


                        System.out.println("Test");

                        if (isStreamOnline && !isCheckedIn) {
                            isCheckedIn = true;

                            try {
                                Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));
                            }catch (IOException e) {throw new RuntimeException(e);}catch (URISyntaxException e) {throw new RuntimeException(e);}



                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {controller.streamStatus.setText("Stream is online");}});
                        } else if (!isStreamOnline) {
                            isCheckedIn = false;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {controller.streamStatus.setText("Stream is offline");}});
                        }

                    }
                    else{
                        isCheckedIn = false;
                    }



                }
            }, 100,100);


    }


    public static void setController(HelloController helloController){controller = helloController;}







}


