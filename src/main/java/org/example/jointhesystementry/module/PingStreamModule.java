package org.example.jointhesystementry.module;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class PingStreamModule {
   static final String channelName = "joinTheSystem";
   static final String twitchKeyPublic = "a11xo6tbj31ak6gezlmb4zmbhn06nh";
   static final String twitchKeyPrivate = "0quia8dngi8npiy8rpicdofeg8pl9r";
    static final String jsonIWant = "[{\"operationName\":\"PlaybackAccessToken\",\"variables\":{\"isLive\":true,\"login\":\""
            + channelName + "\",\"playerType\":\"site\"},\"extensions\":{\"persistedQuery\":{\"version\":1,\"sha256Hash\":\"0828119ded8c1340f41a98b72d86d02c3ed44c5d21b8c702d5f574ba9de3f2e6\"}}}]";

    private PingStreamModule(){}


    public static boolean parseInfo(){
        try {
            URL url = new URL("https://gql.twitch.tv/gql");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Client-ID",twitchKeyPublic);
            urlConnection.setRequestProperty("Authorization", "Bearer " + twitchKeyPrivate);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(jsonIWant.getBytes());
            outputStream.close();

            InputStream inputStream = urlConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String tempReader;

            while ((tempReader = bufferedReader.readLine()) != null){
                stringBuilder.append(tempReader);
            }

            System.out.println(stringBuilder);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return false;
    }


}
