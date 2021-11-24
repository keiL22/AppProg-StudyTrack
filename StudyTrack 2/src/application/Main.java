package application;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("src/Sample.fxml"));
			URL url = new File("src/application/view/CoursesView.fxml").toURI().toURL();
	    	Pane root = FXMLLoader.load(url);
			Scene scene = new Scene(root,822,488);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}