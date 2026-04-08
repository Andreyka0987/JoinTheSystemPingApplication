package org.example.jointhesystementry;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.jointhesystementry.module.EnterStream;
import org.example.jointhesystementry.module.PingStreamModule;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application {
    HelloController helloController;


    @Override
    public void start(Stage stage) throws IOException {
        // main part if it won't load i couldn't take any info

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 455, 312);



            FXTrayIcon fxTrayIcon = new FXTrayIcon(stage,getClass().getResource("/trayicon.gif"));
            fxTrayIcon.addExitItem("Exit", new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Runtime.getRuntime().exit(0);}});
            fxTrayIcon.show();



        stage.setTitle("StreamPinger");
        stage.setScene(scene);
        stage.show();





        helloController = fxmlLoader.getController();
        EnterStream.setController(helloController);
        autoStartup();
        EnterStream.setIsRadioIsChecked();
        checkIfEnterStream();
    }

    public static void main(String[] args) {
        launch();
    }


     private void autoStartup(){
        if (PingStreamModule.checkInfo()){helloController.streamStatus.setText("Stream is online");}
        else {helloController.streamStatus.setText("Stream is offline");}
    }

    private void checkIfEnterStream(){
        if (EnterStream.isRadioIsChecked){
            try {
                EnterStream.enterStreamRadioButton();
            } catch (URISyntaxException e) {throw new RuntimeException(e);}catch (IOException e) {throw new RuntimeException(e);}
        }
    }



}