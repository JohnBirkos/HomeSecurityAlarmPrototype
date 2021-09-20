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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import securityalarm.SOSCALLController;
import securityalarm.MainMenuController;
import securityalarm.SecurityAlarm;

/**
 *
 * @author Panayiotis
 */
public class ProgressTimer extends Thread {
	
	
	
	private int progress;
    private Label countdown;
    private Label warning;
    private GridPane g;
    private Scene sc;
    private String red = "";
    private static int yolo;
    public static boolean mainFlag = false;
    private static boolean flagdone = false;
	private static boolean flagalert = false;
    ProgressIndicator pr;
    private int z1 = 0;

    
    public ProgressTimer(Label countdown, GridPane g, ProgressIndicator pr, Scene sc,Label warning) {
    	this.countdown = countdown;
    	this.g = g;
    	this.pr = pr;
    	this.sc = sc;
    	this.warning = warning;
    	progress = 15;
    }
    
    @Override
    public void run(){
    	
    	
    	yolo = 1;
    	int thirtysec = 1000;
    	int hundredsec = 250;
    	String dot = ".";

    	
    	int alarm = 75;
    	String r1 = "F";
    	String r2 = "E";
    	String r3 = "D";
    	String r4 = "C";
    	String r5 = "B";
    	String fourred = "";
    	int r = 0;
    	int z = 0;
    	boolean flag = true;
    	while(alarm > 0 ){
    		if ( alarm == 1) {
        		yolo = 0;
    		}

    		if ( progress > 0) {
    			alarm--;
    			try {
                    Thread.sleep(thirtysec);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgressThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(()->{
                   //pr.setProgress(progress); 
                	countdown.setText(Integer.toString(progress));
                });
    		}else {
    			
    			alarm--;
    			if ( alarm%2 == 0) {
    				flagalert = false;
    			}else {
    				flagalert = true;
    			}
    			
    			if ( z%2 == 0) {
    				red = "FF";
    			}else {
    				red = "D9";
    			}
    			z++;
    			
    			try {
                    Thread.sleep(hundredsec);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProgressThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(()->{
                   //pr.setProgress(progress); 
            		g.setStyle("-fx-background-color:#"+red + "0000");
        			warning.setVisible(flagalert);
            		if ( yolo == 0) {
            			//System.out.println("MPIKA");
                		mainFlag = true;
            			MainMenuController.stage2.close();
                        if ( !flagdone ){
                        	Scene scene;
                            Parent root=null;
                            MainMenuController.stageSOS = new Stage();
                            try {
    							root = FXMLLoader.load(getClass().getResource("../securityalarm/SOSCALL.fxml"));
    						} catch (IOException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
                            scene = new Scene(root, 800, 400);
                            
                            MainMenuController.stageSOS.setScene(scene);
                           
                            MainMenuController.stageSOS.show();
                        }
                        flagdone = true;
            		}
                });
    		}
            
            

            if ( progress<=0){
            	pr.setVisible(false);
            	countdown.setVisible(false);
            	//g.setVisible(false);
            }else {
            	progress = progress - 1;
            }

        }
    	
    	SecurityAlarm.flagwrongpassword = true;
        try {
            this.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProgressThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
