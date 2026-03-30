package org.example.jointhesystementry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.jointhesystementry.module.EnterStream;
import org.example.jointhesystementry.module.PingStreamModule;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
    HelloController helloController;


    @Override
    public void start(Stage stage) throws IOException {
        // main part if it won't load i couldn't take any info

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 455, 312);
        stage.setTitle("StreamPinger");
        stage.setScene(scene);
        stage.show();

        helloController = fxmlLoader.getController();
        EnterStream.setController(helloController);
        autoStartup();
    }

    public static void main(String[] args) {
        launch();
    }


     private void autoStartup(){
        if (PingStreamModule.checkInfo()){helloController.streamStatus.setText("Stream is online");}
        else {helloController.streamStatus.setText("Stream is offline");}
    }





}