package securityalarm;


import static securityalarm.SecurityAlarm.flagDoorStatus;
import static securityalarm.MainMenuController.initDate;
import static securityalarm.MainMenuController.temp;
import static securityalarm.SecurityAlarm.savedList;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
	
	@FXML
	private MenuButton roombox;
	
	@FXML
	private CheckMenuItem kitchen;

	@FXML
	private CheckMenuItem livingroom;

	@FXML
	private CheckMenuItem bedroom;

	@FXML
	private CheckMenuItem diningroom;

	@FXML
	private CheckMenuItem guestroom;
	
	@FXML
	private Button cancel;
	
	@FXML
	private Button restore;
	
	@FXML
	private Button save;
	
	@FXML
	private Button change;
	
	@FXML
	private DatePicker dp;
	
	@FXML
	private Label lb;
	
	@FXML
	private Label nightlb;
	
	@FXML
	private Button home;
	
	private String date = "";
	private final String defDate = "2018-12-21";
	private ArrayList<String> roomList = new ArrayList<String>();
	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	public void onClick(ActionEvent e) throws IOException {
		
		if(e.getSource() == restore) {
			MainMenuController.time = sdf.format(cal.getTime());
			
			lb.setText(defDate + "\t" + MainMenuController.time + temp + "\t" + MainMenuController.nightst);
			MainMenuController.initDate = defDate;
			
			kitchen.setSelected(false);
			livingroom.setSelected(false);
			guestroom.setSelected(false);
			bedroom.setSelected(false);
			diningroom.setSelected(false);
			
			savedList.clear();
			roomList.clear();
			nightlb.setText("");
			
			Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Settings");
            alert.setHeaderText("The settings have been reset to default");
            alert.setGraphic(null);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.CLOSE);
            alert.show();
            if (alert.isShowing()){
                try {
					Thread.sleep(1200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            alert.close();
            
		}else if(e.getSource() == home) {
			
			Parent root;
		    Scene scene;
		    Stage stage;
		        
	        stage = (Stage) home.getScene().getWindow();
	        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	        
		}else if(e.getSource() == save) {
			
			if(dp.getValue() != null) {
				date = dp.getValue().toString();
			}
			
			MainMenuController.time = sdf.format(cal.getTime());
			
			if(!date.equals("")) {
				lb.setText(date + "\t" + MainMenuController.time + temp + "\t" + MainMenuController.nightst);
				MainMenuController.initDate = date;
			}else {
				lb.setText(defDate + "\t" + MainMenuController.time + temp + "\t" + MainMenuController.nightst);
				MainMenuController.initDate = defDate;
			}
			
			savedList.clear();
			
			if(kitchen.isSelected()) {
				savedList.add("Kitchen    ");
			}else {
				if(savedList.contains("Kitchen    ")) {
					savedList.remove("Kitchen    ");
				}
			}
			if(livingroom.isSelected()) {
				savedList.add("Living Room");
			}else {
				if(savedList.contains("Living Room")) {
					savedList.remove("Living Room");
				}
			}
			if(bedroom.isSelected()) {
				savedList.add("Bedroom    ");
			}else {
				if(savedList.contains("Bedroom    ")) {
					savedList.remove("Bedroom    ");
				}
			}
			if(guestroom.isSelected()) {
				savedList.add("Guest Room ");
			}else {
				if(savedList.contains("Guest Room ")) {
					savedList.remove("Guest Room ");
				}
			}
			if(diningroom.isSelected()) {
				savedList.add("Dining Room");
			}else {
				if(savedList.contains("Dining Room")) {
					savedList.remove("Dining Room");
				}
			}
			
			for(int i = 0; i < savedList.size(); i++) {
				if(i ==0) {
					nightlb.setText(savedList.get(i));
				}else if(i == 2 || i == 4) {
					nightlb.setText(nightlb.getText() + "\n" + savedList.get(i));
				}else {
					nightlb.setText(nightlb.getText() + "\t\t" + savedList.get(i));
				}
			}
			
			Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Settings");
            alert.setHeaderText("The changes have been saved");
            alert.setGraphic(null);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.CLOSE);
            alert.show();
            if (alert.isShowing()){
                try {
					Thread.sleep(1200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            alert.close();
            
		}else if(e.getSource() == cancel) {
			
			Parent root;
		    Scene scene;
		    Stage stage;
		        
	        stage = (Stage) save.getScene().getWindow();
	        root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	        
		}else if ( e.getSource() == change ) {
			
			Stage stage;
	        Scene scene;
	        Parent root;
	        stage = (Stage) change.getScene().getWindow();
	        root = FXMLLoader.load(getClass().getResource("keypad2.fxml"));
	        scene = new Scene(root, 600,400);
	        stage.setScene(scene);
	        stage.show();
	        
		}else if(e.getSource() == kitchen) {
			
			if(kitchen.isSelected()) {
				roomList.add("Kitchen    ");
			}else {
				roomList.remove("Kitchen    ");
			}
			
			if(!roomList.isEmpty()) {
				for(int i = 0; i < roomList.size(); i++) {
					if(i ==0) {
						nightlb.setText(roomList.get(i));
					}else if(i == 2 || i == 4) {
						nightlb.setText(nightlb.getText() + "\n" + roomList.get(i));
					}else {
						nightlb.setText(nightlb.getText() + "\t\t" + roomList.get(i));
					}
				}
			}else {
				nightlb.setText("");
			}
		}else if(e.getSource() == livingroom) {
			
			if(livingroom.isSelected()) {
				roomList.add("Living Room");
			}else {
				roomList.remove("Living Room");
			}

			if(!roomList.isEmpty()) {
				for(int i = 0; i < roomList.size(); i++) {
					if(i ==0) {
						nightlb.setText(roomList.get(i));
					}else if(i == 2 || i == 4) {
						nightlb.setText(nightlb.getText() + "\n" + roomList.get(i));
					}else {
						nightlb.setText(nightlb.getText() + "\t\t" + roomList.get(i));
					}
				}
			}else {
				nightlb.setText("");
			}
			
		}else if(e.getSource() == diningroom) {
			
			if(diningroom.isSelected()) {
				roomList.add("Dining Room");
			}else {
				roomList.remove("Dining Room");
			}

			if(!roomList.isEmpty()) {
				for(int i = 0; i < roomList.size(); i++) {
					if(i ==0) {
						nightlb.setText(roomList.get(i));
					}else if(i == 2 || i == 4) {
						nightlb.setText(nightlb.getText() + "\n" + roomList.get(i));
					}else {
						nightlb.setText(nightlb.getText() + "\t\t" + roomList.get(i));
					}
				}
			}else {
				nightlb.setText("");
			}
		}else if(e.getSource() == bedroom) {
			
			if(bedroom.isSelected()) {
				roomList.add("Bedroom    ");
			}else {
				roomList.remove("Bedroom    ");
			}

			if(!roomList.isEmpty()) {
				for(int i = 0; i < roomList.size(); i++) {
					if(i ==0) {
						nightlb.setText(roomList.get(i));
					}else if(i == 2 || i == 4) {
						nightlb.setText(nightlb.getText() + "\n" + roomList.get(i));
					}else {
						nightlb.setText(nightlb.getText() + "\t\t" + roomList.get(i));
					}
				}
			}else {
				nightlb.setText("");
			}
		}else if(e.getSource() == guestroom) {
			
			if(guestroom.isSelected()) {
				roomList.add("Guest Room ");
			}else {
				roomList.remove("Guest Room ");
			}

			if(!roomList.isEmpty()) {
				for(int i = 0; i < roomList.size(); i++) {
					if(i ==0) {
						nightlb.setText(roomList.get(i));
					}else if(i == 2 || i == 4) {
						nightlb.setText(nightlb.getText() + "\n" + roomList.get(i));
					}else {
						nightlb.setText(nightlb.getText() + "\t\t" + roomList.get(i));
					}
				}
			}else {
				nightlb.setText("");
			}
		}
	}
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		MainMenuController.time = sdf.format(cal.getTime());
		
		lb.setText(MainMenuController.initDate + "\t" + MainMenuController.time + temp + "\t" + MainMenuController.nightst);
		
		if(savedList.size() > 0) {
			for(int i = 0; i < savedList.size(); i++) {
				if(i ==0) {
					nightlb.setText(savedList.get(i));
				}else if(i == 2 || i == 4) {
					nightlb.setText(nightlb.getText() + "\n" + savedList.get(i));
				}else {
					nightlb.setText(nightlb.getText() + "\t\t" + savedList.get(i));
				}
			}
		}else {
			nightlb.setText("");
		}
		
		if(savedList.contains("Kitchen    ")) {
			kitchen.setSelected(true);
		}
		if(savedList.contains("Living Room")) {
			livingroom.setSelected(true);
		}
		if(savedList.contains("Bedroom    ")) {
			bedroom.setSelected(true);
		}
		if(savedList.contains("Guest Room ")) {
			guestroom.setSelected(true);
		}
		if(savedList.contains("Dining Room")) {
			diningroom.setSelected(true);
		}
		
		if(!savedList.isEmpty()) {
			for(int i = 0; i < savedList.size(); i++) {
				roomList.add(savedList.get(i));
			}
		}
	}

}
