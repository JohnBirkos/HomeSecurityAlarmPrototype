/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityalarm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import progress.ProgressTimer;
import progress.RingProgressIndicator;

import static securityalarm.MainMenuController.password;
import static securityalarm.SecurityAlarm.alarmStatus;

/**
 * FXML Controller class
 *
 * @author giann
 */
public class KeypadController implements Initializable {
	

	@FXML
	private ProgressIndicator pr;
	
	@FXML
	private GridPane g;
	
    @FXML
    private VBox vbox;
    
    @FXML
    private Button btn1;
    
    @FXML
    private Button btn2;
    
    @FXML
    private Button btn3;
    
    @FXML
    private Button btn4;
    
    @FXML
    private Button btn5;
    
    @FXML
    private Button btn6;
    
    @FXML
    private Button btn7;
    
    @FXML
    private Button btn8;
    
    @FXML
    private Button btn9;
    
    @FXML
    private Button btn0;
    
    @FXML
    private Button btnEnter;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Label label;
    
    @FXML
    private Label countdown;
    
    @FXML
    private Label warning;
    
    private String givenPassword = "";
    
    
    
    /**
     * Initializes the controller class.
     */
    
    private int count;
    
    public void onClick(ActionEvent e) throws IOException{ 
          	
        if(count == 0){
            label.setText("");
            count++;
        }
        
        if(e.getSource() == btn1){        	
            label.setText(label.getText() + "*");
            givenPassword += "1";
        }else if(e.getSource() == btn2){
            label.setText(label.getText() + "*");
            givenPassword += "2";
        }else if(e.getSource() == btn3){
            label.setText(label.getText() + "*");
            givenPassword += "3";
        }else if(e.getSource() == btn4){
            label.setText(label.getText() + "*");
            givenPassword += "4";
        }else if(e.getSource() == btn5){
            label.setText(label.getText() + "*");
            givenPassword += "5";
        }else if(e.getSource() == btn6){
            label.setText(label.getText() + "*");
            givenPassword += "6";
        }else if(e.getSource() == btn7){
            label.setText(label.getText() + "*");
            givenPassword += "7";
        }else if(e.getSource() == btn8){
            label.setText(label.getText() + "*");
            givenPassword += "8";
        }else if(e.getSource() == btn9){
            label.setText(label.getText() + "*");
            givenPassword += "9";
        }else if(e.getSource() == btn0){
            label.setText(label.getText() + "*");
            givenPassword += "0";
        }else if(e.getSource() == btnEnter){

            //if(ps.equals(myps))
        	if ( givenPassword.equals(password) ){
    			 //alarmStatus = false;

                 
        		 Stage stage;
                 Scene scene;
                 Parent root;
                 stage = (Stage) btnEnter.getScene().getWindow();         
                 stage.close();

            }else {
            	label.setText("Wrong Password"); 
            	count = 0;
            	givenPassword = "";
            }
        	
            
           
        }else if(e.getSource() == btnCancel){
            label.setText("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        label.setText("Enter Password");
        count = 0;

        
        ProgressTimer pt = new ProgressTimer(countdown,g,pr,label.getScene(),warning);
        pt.start();
        

    }    
    
}
