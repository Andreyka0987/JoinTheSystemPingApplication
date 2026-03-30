package org.example.jointhesystementry.module;

import org.example.jointhesystementry.HelloController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class EnterStream {
    private static boolean isStreamOnline = false;
    private static boolean isAlreadyEntered = false;
    public static boolean isRadioIsChecked = false;
    private static RadioButtonThread radioButtonThread = new RadioButtonThread();
    static HelloController controller;

    public static void enterStream(String currentStatus, boolean ifEnterFromRadioButton){

        if (currentStatus.equals("Stream is offline")){isStreamOnline = false;} else{isStreamOnline = true;}

        if (isStreamOnline && !ifEnterFromRadioButton){
            try {
                Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        if (ifEnterFromRadioButton && isRadioIsChecked){
            if (!radioButtonThread.isAlive()){
                radioButtonThread.start();
            }
        }

    }


    public static void setController(HelloController helloController){controller = helloController;}


    static class RadioButtonThread extends Thread{

        @Override
        public void run() {
            super.run();
            try {

                while (true) {
                    if (isRadioIsChecked) {

                        if (isStreamOnline && !isAlreadyEntered) {
                            Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));
                            isAlreadyEntered = true;
                            controller.streamStatus.setText("Stream is online");
                        } else if (!isStreamOnline && isAlreadyEntered) {
                            isAlreadyEntered = false;
                            controller.streamStatus.setText("Stream is offline");
                        }
                    }

                    sleep(2000);

                    if (!isStreamOnline)isStreamOnline = true;
                    if (isStreamOnline)isStreamOnline = false;
                }

            } catch (InterruptedException | URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }


        }
    }




}


