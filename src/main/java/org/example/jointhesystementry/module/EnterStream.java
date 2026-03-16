package org.example.jointhesystementry.module;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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



}
