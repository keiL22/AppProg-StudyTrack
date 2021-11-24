package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;

import application.model.Assignment;
import application.model.Course;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AssignmentViewController {

    @FXML 
    TableView<Assignment> assignmentsTabel;

    @FXML
    private TableColumn<Assignment, String> dateCol;

    @FXML
    private TableColumn<Assignment, Boolean> compCol;

    @FXML
    private TableColumn<Assignment, String> nameCol;

    @FXML
    private TableColumn<Assignment, Assignment> editCol;

    @FXML
    private Label courseNameLabel;
    
    @FXML
    private TextField nameAdd;

    @FXML
    private DatePicker dateAdd;
    
    @FXML
    private Pane rolePane;
    
    Course currentCourse;
    
    static ObservableList<Assignment> storedAssigns = FXCollections.observableArrayList();
    //will need to add more arraylists for the other courses
    
    //stran's addition:
    //static ObservableList<List<Assignment>> storedAssigns = FXCollections.observableArrayList();
    //->this way, we can have a method that reads in and fills up the List. First index will be the course, 
    //  and a .get().get() will retrieve the assignment itself
    

    @FXML
    void backPressed(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/CoursesView.fxml").toURI().toURL();
    	rolePane = FXMLLoader.load(url);// pane you are GOING TO
        Scene scene = new Scene(rolePane);// pane you are GOING TO show
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
       
    }
    
    public void initialize() {
    	courseNameLabel.setText(CourseViewController.courses.get(CourseViewController.lastCourseClicked).getCourseName());
        currentCourse = CourseViewController.getCourse();
    	createTable();//calls the method that fills the table
        //maybe the variable that we pass could be CourseViewController.courseLastClicked (the id of the course we just clicked)
    }   

    @FXML
    void addPressed(ActionEvent event) {
    	if(nameAdd.getText()!=null&&dateAdd.getValue()!=null) {
    		Assignment addedStudent = new Assignment(nameAdd.getText(), dateAdd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), CourseViewController.courses.get(CourseViewController.lastCourseClicked), false); //creasting a new assignment object based on the user input
    		//storedAssigns.add(addedStudent); //adding to the arraylist, will need to make a case statement later since there will be multiple array lists (one for each possiblee course created)
    		currentCourse.storedAssigns.add(addedStudent);
    		nameAdd.clear(); //clear the field so you dont have the back space
    		dateAdd.setValue(null); //clears the date selected
    	}
    	else {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("Please enter valid data before pressing ADD!");
    		a.show();
    	}
    }
    
    public void createTable() {
    	nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name")); //creates values for name column
    	dateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date")); //creates values for date column
    	editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue())); //need to make a detailed column that contains 2 buttons so using wrapper
    	compCol.setCellValueFactory(new Callback<CellDataFeatures<Assignment,Boolean>,ObservableValue<Boolean>>(){//need to do fancy booleanproperty stuff since that what the checkbox is based on, literally just implemented this so they stay checked no matter scene switches
    	            @Override 
    	            public ObservableValue<Boolean> call(CellDataFeatures<Assignment,Boolean> data){
    	                return data.getValue().checking();
    	            }
    	        });
    	        compCol.setCellFactory(new Callback<TableColumn<Assignment,Boolean>,TableCell<Assignment,Boolean>>(){
    	            @Override 
    	            public TableCell<Assignment,Boolean> call(TableColumn<Assignment,Boolean> param){
    	                return new CheckBoxTableCell<>();
    	            }
    	        });
    	        
        editCol.setCellFactory(param -> new TableCell<Assignment, Assignment>() {
        	ImageView deleteView = new ImageView("/data/DeleteIcon.png");//~~~~~~~~~~~~~~~~~~~//NOT WORKING
            Button deleteButton = new Button("", deleteView);//new button that will be in column
            ImageView startView = new ImageView("/data/StartIcon.png");//~~~~~~~~~~~~~~~~~~~//NOT WORKING
            Button beginButton = new Button("", startView);//another button in column
            HBox buttons = new HBox(new Label("      "),deleteButton,new Label("        "),beginButton);//adding both buttons to the hbox, i tried giving it spacing but it kept getting errors so I gave up
           
            @Override
            protected void updateItem(Assignment assign, boolean empty) {
                super.updateItem(assign, empty);
                
                setGraphic(empty ? null : buttons);//making sure it not null or itll mess up
                EventHandler<ActionEvent> startPress = new EventHandler<ActionEvent>() {//making new event handler for the start button
                	@Override
                	public void handle(ActionEvent event) {
                		try {
							URL url = new File("src/application/view/TimerView.fxml").toURI().toURL();
							AnchorPane root = (AnchorPane)FXMLLoader.load(url);
	                		Scene scene = new Scene(root,822,488);
	                		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	                		window.setScene(scene);
	                		window.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                	}
                };
                EventHandler<ActionEvent> deletePress = new EventHandler<ActionEvent>() {//making new event handler for delete button
                	@Override
                	public void handle(ActionEvent event) {
                		currentCourse.storedAssigns.remove(CourseViewController.lastCourseClicked);
                		//storedAssigns.remove(assign);//removes it from the list, again will need case statement to check which list it should be removed from since there will be multiple eventually
                	}
                };
                beginButton.setOnAction(startPress);//setting the action for the button
                deleteButton.setOnAction(deletePress);//setting the action for the button
            }
        });
        assignmentsTabel.setItems(currentCourse.storedAssigns);//another case statment thing you get the jist, this just updates the table with all the items in the array list
    }

}

