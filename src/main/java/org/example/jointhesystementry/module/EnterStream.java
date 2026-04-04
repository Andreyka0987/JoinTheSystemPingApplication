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


    public static void enterStreamRadioButton() throws URISyntaxException, IOException {


            isAlreadyEntered = true;
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    setState();

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


    private static void setState(){
        File file = new File("src/main/resources/radio.txt");


            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                if (isRadioIsChecked){
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
                String s = bufferedReader.readLine();
                if (s.equals("true")){
                    isRadioIsChecked = true;
                    controller.pingRadioButton.setSelected(true);
                } else {
                    isRadioIsChecked = false;
                    controller.pingRadioButton.setSelected(false);
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void setController(HelloController helloController){controller = helloController;}







}


