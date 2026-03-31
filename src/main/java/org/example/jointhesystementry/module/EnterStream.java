package org.example.jointhesystementry.module;

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
    }


    public static void enterStreamRadioButton(String currentStatus) throws URISyntaxException, IOException {

        if (currentStatus.equals("Stream is offline")){isStreamOnline = false;} else{isStreamOnline = true;}


            isAlreadyEntered = true;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    if (isRadioIsChecked) {
                        System.out.println("Test");

                        if (isStreamOnline && !isCheckedIn) {
                            isCheckedIn = true;

                            try {
                                Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));
                            }catch (IOException e) {throw new RuntimeException(e);}catch (URISyntaxException e) {throw new RuntimeException(e);}


                            controller.streamStatus.setText("Stream is online");
                        } else if (!isStreamOnline && isCheckedIn) {
                            isCheckedIn = true;

                            controller.streamStatus.setText("Stream is offline");
                        }

                            if (controller.streamStatus.getText().equals("Stream is online")){
                                isStreamOnline = true;
                            }
                            else {
                                isStreamOnline = false;
                            }

//                        if (!isStreamOnline)isStreamOnline = true;
//                        if (isStreamOnline)isStreamOnline = false;

                    }



                }
            }, 2000,2000);


    }


    public static void setController(HelloController helloController){controller = helloController;}







}


