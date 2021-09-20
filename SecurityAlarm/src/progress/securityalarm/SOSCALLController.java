/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityalarm;


import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import progress.ProgressThread;
import progress.RingProgressIndicator;

/**
 * FXML Controller class
 *
 * @author giann
 */
public class SOSCALLController implements Initializable {
    
    @FXML
    private Label message;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private Button btn;
    
    
    
    /**
     * Initializes the controller class.
     * 
     * @param e
     */
     
    @FXML
    public void call() throws InterruptedException, IOException{

        Parent root;
        Scene scene;
        Stage stage;
        
        stage = (Stage) message.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RingProgressIndicator rpi = new RingProgressIndicator();
        rpi.setRingWidth(200);
        rpi.makeIndeterminate();
        btn = new Button();
        btn.setDefaultButton(true);
        btn.setText("Home");
        btn.setPrefSize(100, 50);
        btn.setOnAction( e->{
            try {
                this.call();
            } catch (InterruptedException ex) {
                Logger.getLogger(SOSCALLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SOSCALLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        
        btn.setVisible(false);
        
        vbox.getChildren().add(rpi);
        vbox.getChildren().add(btn);
        ProgressThread pt = new ProgressThread(rpi, message, btn);
        pt.start();
        
    }    
    
    
    
    

        /*
        String s = ".";
        for (double i = 0; i <= 1; i=i+0.01){
            progress.setProgress(i);
            message.setText("Calling" + s);
            if ( s.equals(".")){
                s = "..";
            }else if ( s.equals("..")){
                s = "...";
            }else{
                s = ".";
            }
        }
        message.setText("Call was successful");
        */
    
}
