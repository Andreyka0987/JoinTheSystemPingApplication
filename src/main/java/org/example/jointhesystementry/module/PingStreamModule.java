package org.example.jointhesystementry.module;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class PingStreamModule {
   static final String channelName = "joinTheSystem";
   static final String twitchKeyPublic = "a11xo6tbj31ak6gezlmb4zmbhn06nh";
   static final String twitchKeyPrivate = "sofxlt3e6e9qsnmtmmuh5wq1hao1jy";

    private PingStreamModule(){}


    public static boolean parseInfo(){


        System.out.println(returnBearerInfo());


        return false;
    }

    public static ArrayList<String> returnBearerInfo(){
        ArrayList<String> info = new ArrayList<>();
        try {
            URL url = new URL("https://id.twitch.tv/oauth2/token");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-type","application/x-www-form-urlencoded");

            String request = "client_id="+ URLEncoder.encode(twitchKeyPublic, StandardCharsets.UTF_8)
                    +"&client_secret="+URLEncoder.encode(twitchKeyPrivate,StandardCharsets.UTF_8)
                    +"&grant_type=client_credentials";

            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(request.getBytes());

            InputStream inputStream = urlConnection.getInputStream();

            String read;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            while ((read = bufferedReader.readLine()) != null){
                stringBuilder.append(read);
            }

            boolean isTokenIsReady = false;
            StringBuilder token = new StringBuilder();
            StringBuilder time = new StringBuilder();
            for (int i =17;i<stringBuilder.length();i++){
                if (!stringBuilder.toString().split("")[i].equals("\"") && !isTokenIsReady){
                    token.append(stringBuilder.toString().split("")[i]);
                }
                else {
                    isTokenIsReady = true;
                }

                if (Character.isDigit(stringBuilder.toString().toCharArray()[i]) && isTokenIsReady){
                    time.append(stringBuilder.toString().split("")[i]);
                }
            }
            info.add(token.toString());
            info.add(time.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return info;
    }


}
