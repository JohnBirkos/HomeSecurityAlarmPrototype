/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityalarm;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static securityalarm.SecurityAlarm.alarmStatus;
import static securityalarm.SecurityAlarm.flagDoorStatus;

/**
 * FXML Controller class
 *
 * @author giann
 */
public class MainMenuController implements Initializable {
    
    public static int N = 0;
    
	public static String initDate = "2018-12-21", temp = "\t26 °C";

	public static String time;
	
    public static Stage stage2;
    
    public static Stage stageSOS;
    
    public static String password = "6234";
    
    public static boolean nightflag = false;
    
    private int mpla = 0;
    public static String nightst;
    
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @FXML
    public Button activate;
    
    @FXML
    public Label alarmStatusLabel;
    
    @FXML
    private Button settings;
    
    @FXML
    private Button doorStatus;
    
    @FXML
    private Button soscallbtn;
    
    @FXML
    private Button nightmode;
    
    @FXML
    private Label dateLabel;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void onClick(ActionEvent e) throws InterruptedException, IOException{
        if(e.getSource() == doorStatus){
            if(flagDoorStatus){
                flagDoorStatus = false;
                doorStatus.setText("Lock Door");
            
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Door Status");
                alert.setHeaderText("Door is Unlocked");
                alert.setGraphic(null);
                alert.getButtonTypes().clear();
                alert.getButtonTypes().add(ButtonType.CLOSE);
                alert.show();
                if (alert.isShowing()){
                    Thread.sleep(1500);
                }
                alert.close();
            }else{
                flagDoorStatus = true;
                doorStatus.setText("Unlock Door");
                
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText("Door is Locked");
                alert.setGraphic(null);
                alert.getButtonTypes().clear();
                alert.getButtonTypes().add(ButtonType.CLOSE);
                alert.show();
                if ( alert.isShowing()){
                    Thread.sleep(3000);
                }
                alert.close();
            }
        }else if(e.getSource() == soscallbtn){
            Scene scene;
            Parent root;
            stageSOS = new Stage();
            root = FXMLLoader.load(getClass().getResource("SOSCALL.fxml"));
            scene = new Scene(root, 800, 400);
            
            stageSOS.setScene(scene);
           
            stageSOS.show();
        }else if(e.getSource() == activate){
            
        	if(alarmStatus){

        		stage2 = new Stage();
        		
                Scene scene2;
                Parent root2;
                root2 = FXMLLoader.load(getClass().getResource("keypad.fxml"));
                scene2 = new Scene(root2);
                
                stage2.setScene(scene2);
                stage2.initModality(Modality.APPLICATION_MODAL);              
                stage2.show();
                
                if ( mpla!= 2) {
                	alarmStatus = false;	
            		
            		activate.setText("Arm");
                    alarmStatusLabel.setText("DISARMED");
                    alarmStatusLabel.setTextFill(Color.valueOf("#D40000"));	
                }
        		
            }else{
            	
                alarmStatus = true;
                activate.setText("Disarm");
                alarmStatusLabel.setText("ARMED");
                alarmStatusLabel.setTextFill(Color.valueOf("#187513"));
            }
        	mpla++;
        }else if ( e.getSource() == settings ) {
        	
	    	 Stage stage;
	         Scene scene;
	         Parent root;
	         stage = (Stage) soscallbtn.getScene().getWindow();
	         root = FXMLLoader.load(getClass().getResource("settings.fxml"));
	         scene = new Scene(root, 800, 400);
	         
	         stage.setScene(scene);
	        
	         stage.show();
        }else if ( e.getSource() == nightmode ) {
        	if ( nightflag == false ) {
        		//nightmode.setStyle("-fx-border-color:#FF0000");
        		//nightmode.setStyle("-fx-border-radius:100px");
        		time = sdf.format(cal.getTime());
        		
            	nightst = "Nightmode is Enabled";
                dateLabel.setText(initDate + "\t" + time + temp + "\t" + nightst);
                
        		nightmode.setText("Disable Nightmode");
        		nightflag = true;
        	}else {
        		//nightmode.setStyle("-fx-border-color:#000000; -fx-border-radius:100px;");
        		//nightmode.setStyle("-fx-border-radius:100px");
        		time = sdf.format(cal.getTime());
        		
        		nightst = "Nightmode is Disabled";
                dateLabel.setText(initDate + "\t" + time + temp + "\t" + nightst);
                
        		nightmode.setText("Enable Nightmode");
        		nightflag = false;
        	}
        	
        	
        	
        }
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
        time = sdf.format(cal.getTime());
        
        if(flagDoorStatus){
            doorStatus.setText("Unlock Door");
        }else{
            doorStatus.setText("Lock Door");
        }
        if(alarmStatus){
            activate.setText("Disarm");
            alarmStatusLabel.setText("ARMED");
            alarmStatusLabel.setTextFill(Color.valueOf("#187513"));
        }else{
            activate.setText("Arm");
            alarmStatusLabel.setText("DISARMED");
            alarmStatusLabel.setTextFill(Color.valueOf("#D40000"));
        }
        if ( nightflag == false) {
        	nightmode.setText("Enable Nightmode");
        }else {
        	nightmode.setText("Disable Nightmode");
        }
        
        if(nightmode.getText().equals("Enable Nightmode")) {
        	nightst = "Nightmode is Disabled";
        }else {
        	nightst = "Nightmode is Enabled";
        }
        
        dateLabel.setText(initDate + "\t" + time + temp + "\t" + nightst);
        
    }    
    
}
