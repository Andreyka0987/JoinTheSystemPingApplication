package org.example.jointhesystementry.module;

import javafx.application.Platform;
import org.example.jointhesystementry.HelloController;

import java.awt.*;
import java.io.*;
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
    public static boolean isYouTubeRadioChecked = false;
    private static final String TWITCH_URI = "https://www.twitch.tv/jointhesystemm";
    private static final String YOUTUBE_URI = "www.youtube.com/@JoinTheSystemm/live";



    public static void enterStreamButton(String currentStatus){

        if (currentStatus.equals("Stream is offline")){isStreamOnline = false;} else{isStreamOnline = true;}

        if (isStreamOnline){
            if (!isYouTubeRadioChecked) {
                try {
                    Desktop.getDesktop().browse(new URI(TWITCH_URI));
                } catch (IOException e) {throw new RuntimeException(e);
                } catch (URISyntaxException e) {throw new RuntimeException(e);}
            }
            if (isYouTubeRadioChecked){
                try {
                    Desktop.getDesktop().browse(new URI(YOUTUBE_URI));
                } catch (IOException e) {throw new RuntimeException(e);
                } catch (URISyntaxException e) {throw new RuntimeException(e);}
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


    public static void enterStreamRadioButton() throws URISyntaxException, IOException {


            isAlreadyEntered = true;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    setState();

                    if (isRadioIsChecked) {

                        if (PingStreamModule.checkInfo()){isStreamOnline = true;}
                        else{isStreamOnline = false;}



                        if (isStreamOnline && !isCheckedIn) {
                            isCheckedIn = true;

                            if (!isYouTubeRadioChecked) {
                                try {
                                    Desktop.getDesktop().browse(new URI(TWITCH_URI));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (URISyntaxException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            else {
                                try {
                                    Desktop.getDesktop().browse(new URI(YOUTUBE_URI));
                                } catch (IOException e) {throw new RuntimeException(e);
                                } catch (URISyntaxException e) {throw new RuntimeException(e);}
                            }

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


    private static void setState(){
        File file = new File("src/main/resources/radio.txt");


            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                if (isRadioIsChecked){
                    bufferedWriter.write("true");
                }else {
                    bufferedWriter.write("false");
                }
                bufferedWriter.newLine();
                if (isYouTubeRadioChecked){
                    bufferedWriter.write("true");
                }else {
                    bufferedWriter.write("false");
                }

                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }



    public static void setIsRadioIsChecked(){
        File file = new File("src/main/resources/radio.txt");

        if (file.exists()){
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String s1 = bufferedReader.readLine();
                if (s1.equals("true")){
                    isRadioIsChecked = true;
                    controller.pingRadioButton.setSelected(true);
                } else {
                    isRadioIsChecked = false;
                    controller.pingRadioButton.setSelected(false);
                }


                String s2 = bufferedReader.readLine();
                System.out.println(s2);

                if (s2.equals("true")){
                    isYouTubeRadioChecked = true;
                    controller.isYouTubeRadio.setSelected(true);
                }else {
                    isYouTubeRadioChecked = false;
                    controller.isYouTubeRadio.setSelected(false);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void setController(HelloController helloController){controller = helloController;}







}


