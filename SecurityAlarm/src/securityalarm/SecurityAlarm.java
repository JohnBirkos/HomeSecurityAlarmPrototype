/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityalarm;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.css.*;

/**
 *
 * @author giann
 */
public class SecurityAlarm extends Application {
    
    public static boolean flagDoorStatus;
    public static boolean alarmStatus;
    public static boolean flagwrongpassword;
    public static ArrayList<String> savedList = new ArrayList<String>();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        flagDoorStatus = true; //door is locked
        alarmStatus = true; //alarm is activated
        flagwrongpassword = false;
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                 
        Scene scene = new Scene(root, 800, 400);
        
        primaryStage.setTitle("JPM Security");
        //primaryStage.setOnCloseRequest();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
