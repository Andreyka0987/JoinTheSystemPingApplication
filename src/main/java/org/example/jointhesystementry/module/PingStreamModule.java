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
   static final String twitchKey = "kimne78kx3ncx6brgo4mv6wki5h1ko";
   static final String jsonIWant = "[{\"operationName\":\"ChannelShell\",\"variables\":{\"login\":\"" + channelName + "\"},\"extensions\":{\"persistedQuery\":{\"version\":1,\"sha256Hash\":\"0bceec4391f0d59f3f2e1f9c52b2a391c57f5cfb7be79b2bcd183cbfa1f8f3de\"}}}]";

    private PingStreamModule(){}


    public static boolean parseInfo(){
        try {
            URL url = new URL("https://gql.twitch.tv/gql");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Client-ID",twitchKey);
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
