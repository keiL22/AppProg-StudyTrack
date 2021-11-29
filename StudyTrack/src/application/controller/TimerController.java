package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class TimerController {
	
    @FXML
    private Text timer;
    
    @FXML
    private ImageView playImage;

    private long mins, secs, totalSec = 1500;
    
    //Temp button that takes us to the next/break page
    @FXML
    void finish(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/BreakView.fxml").toURI().toURL();
		AnchorPane root = (AnchorPane)FXMLLoader.load(url);
		Scene scene = new Scene(root,822,488);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
    }
    
    //method that starts the timer upon clicking the play button
    @FXML
    void play(MouseEvent event) {
 
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