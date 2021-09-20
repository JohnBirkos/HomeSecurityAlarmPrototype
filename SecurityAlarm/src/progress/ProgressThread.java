/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progress;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import securityalarm.SOSCALLController;
import securityalarm.MainMenuController;

/**
 *
 * @author Panayiotis
 */
public class ProgressThread extends Thread {
    private RingProgressIndicator rpi;
    private int progress;
    private Label label;
    String str = ".";
    Button btn;
    private static int counter;
    
    public ProgressThread(RingProgressIndicator rpi, Label label,Button btn){
        this.rpi = rpi;
        progress = 0;
        this.label = label;
        this.label.setText("Notifing Security Company.");
        this.btn = btn;
    }
    
    @Override
    public void run(){
        counter = 0;
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgressThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            counter++;
            Platform.runLater(()->{
                
                rpi.setProgress(progress);     
                if(progress % 10  == 0){
                    label.setText("Notifing Security Company" + str);
                    if(str.equals("...")){
                        str = ".";
                    }else{
                        str += ".";
                    }
                }
                if(progress >= 100){
                    label.setText("Message was sent successfully!\nOur security personel is on the way.");
                }
                
                if ( counter == 200) {
                	MainMenuController.stageSOS.close();
                }
            });
            
            progress++;
            
            if ( counter>=200){
                break;
            }
        }
        //btn.setVisible(true);
        
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
