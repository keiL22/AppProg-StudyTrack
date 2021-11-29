package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BreakController {

    @FXML
    private Text timer;
    
    @FXML
    private Pane rolePane;

    //Takes the user to he HomePage
    @FXML
    void home(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/CoursesView.fxml").toURI().toURL();
    	rolePane = FXMLLoader.load(url);// pane you are GOING TO
        Scene scene = new Scene(rolePane);// pane you are GOING TO show
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
    
    //Takes the user to he AssignmentPage
    @FXML
    void assignment(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/AssignmentView.fxml").toURI().toURL();
    	rolePane = FXMLLoader.load(url);// pane you are GOING TO
        Scene scene = new Scene(rolePane);// pane you are GOING TO show
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
    
    private long mins, secs, totalSec = 300;
    
    //This method starts the timer as soon as the user gets onto the BreakPage
    @FXML
    public void initialize() throws IOException {
    	Timer myTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
        	@Override
        	public void run() {	
        		convertTime();
                if (totalSec <= 0) {
                	myTimer.cancel(); 
                    timer.setText("00:00");
                }
        	}
        };
        myTimer.schedule(timerTask, 0, 1000);
    }
    
    //method that will the timer from secs to the proper format
    public void convertTime() {

        mins = TimeUnit.SECONDS.toMinutes(totalSec);
        secs = totalSec - (mins * 60);
        timer.setText(format(mins) + ":" + format(secs));
  
        totalSec--;
    }
    
    //String mehod to return the right format of the timer
    private String format(long value) {
        if (value < 10) {
            return 0 + "" + value;
        }

        return value + "";
    }
}
