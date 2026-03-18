package org.example.jointhesystementry.module;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class EnterStream {


    public static void enterStream(String currentStatus){
        if (!currentStatus.equals("Stream is offline")){
            try {
                Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void enterStreamRadioVersion(){
        DelayThread delayThread = new DelayThread();
        delayThread.run();
    }


    public static class DelayThread extends Thread{

        @Override
        public void run() {
            super.run();
            try {
                for (int i=0;i<4;i++) {
                    sleep(2000);
                    Desktop.getDesktop().browse(new URI("https://www.twitch.tv/jointhesystemm"));
                }
            } catch (InterruptedException | URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


