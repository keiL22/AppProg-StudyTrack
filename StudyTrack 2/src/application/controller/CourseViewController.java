package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import application.model.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class CourseViewController {
	
	@FXML
    private ImageView course4;

    @FXML
    private ImageView course3;

    @FXML
    private ImageView course6;

    @FXML
    private ImageView course5;

    @FXML
    private Label c_label_1;

    @FXML
    private Label c_label_2;

    @FXML
    private Label c_label_5;

    @FXML
    private Label c_label_6;

    @FXML
    private ImageView course2;

    @FXML
    private Label c_label_3;

    @FXML
    private ImageView course1;

    @FXML
    private Label c_label_4;
    
    @FXML
    private Button addCourseButton;
    
    @FXML
    private MenuItem menu_update_course;
    
    @FXML
    private MenuItem menu_remove_course;

    @FXML
    private MenuItem menu_add_course;
    
    @FXML
    private ImageView delete_course1;

    @FXML
    private ImageView delete_course2;

    @FXML
    private ImageView delete_course3;

    @FXML
    private ImageView delete_course4;
    
    @FXML
    private ImageView delete_course5;

    @FXML
    private ImageView delete_course6;
    
    @FXML
    private AnchorPane coursesPane;
    
    public static int numCoursesCreated;
    
    public static int lastCourseClicked;
    
    public static int openedFirstTime = 0; //0 == false, 1 == true
    
    public static ArrayList<Course> courses = new ArrayList<Course>();
    
    public static ArrayList<ImageView> courseIDS = new ArrayList<>();
    
    public static ArrayList<Label> labelIDS = new ArrayList<>();
    
    public static ArrayList<ImageView> deleteIDS = new ArrayList<>();
    
    
    
    @FXML
    public void initialize()
    {
    	if(openedFirstTime == 0)
    	{
	    	initializeCourses();
	    	lastCourseClicked = -1;
	    	numCoursesCreated = 0;
	    	
	    	course1.setOpacity(.2);
	
	    	course2.setOpacity(.2);
	
	    	course3.setOpacity(.2);
	
	    	course4.setOpacity(.2);
	
	    	course5.setOpacity(.2);
	
	    	course6.setOpacity(.2);
	    	
	    	courseIDS.add(null); //just so we can use 1 as the starting index
	    	courseIDS.add(course1);
	    	courseIDS.add(course2);
	    	courseIDS.add(course3);
	    	courseIDS.add(course4);
	    	courseIDS.add(course5);
	    	courseIDS.add(course6);
	    	
	    	labelIDS.add(null);
	    	labelIDS.add(c_label_1);
	    	labelIDS.add(c_label_2);
	    	labelIDS.add(c_label_3);
	    	labelIDS.add(c_label_4);
	    	labelIDS.add(c_label_5);
	    	labelIDS.add(c_label_6);
	    	
	    	deleteIDS.add(null);
	    	deleteIDS.add(delete_course1);
	    	deleteIDS.add(delete_course2);
	    	deleteIDS.add(delete_course3);
	    	deleteIDS.add(delete_course4);
	    	deleteIDS.add(delete_course5);
	    	deleteIDS.add(delete_course6);
	    	
	    	for(int i = 0; i<7; i++)
    		{
    			courses.get(i).setCourseName("null");
    		}
    	}
    	else
    	{
    		//set the opacity of only the courses that have been created to 1
    		if(courses.get(1).getCourseName().equals("null"))
    		{
    			course1.setOpacity(.2);
    			c_label_1.setText("*");
    		}
    		else
    		{
    			course1.setOpacity(1);
    			c_label_1.setText(courses.get(1).getCourseName());
    		}
    		
    		if(courses.get(2).getCourseName().equals("null"))
    		{
    			course2.setOpacity(.2);
    			c_label_2.setText("*");
    		}
    		else
    		{
    			course2.setOpacity(1);
    			c_label_2.setText(courses.get(2).getCourseName());
    		}
    		
    		if(courses.get(3).getCourseName().equals("null"))
    		{
    			course3.setOpacity(.2);
    			c_label_3.setText("*");
    		}
    		else
    		{
    			course3.setOpacity(1);
    			c_label_3.setText(courses.get(3).getCourseName());
    		}
    		
    		if(courses.get(4).getCourseName().equals("null"))
    		{
    			course4.setOpacity(.2);
    			c_label_4.setText("*");
    		}
    		else
    		{
    			course4.setOpacity(1);
    			c_label_4.setText(courses.get(4).getCourseName());
    		}
    		
    		if(courses.get(5).getCourseName().equals("null"))
    		{
    			course5.setOpacity(.2);
    			c_label_5.setText("*");
    		}
    		else
    		{
    			course5.setOpacity(1);
    			c_label_5.setText(courses.get(5).getCourseName());
    		}
    		
    		if(courses.get(6).getCourseName().equals("null"))
    		{
    			course6.setOpacity(.2);
    			c_label_6.setText("*");
    		}
    		else
    		{
    			course6.setOpacity(1);
    			c_label_6.setText(courses.get(6).getCourseName());
    		}
    	}
    }
    
    static Course getCourse()
    {
    	return courses.get(lastCourseClicked);
    }
    
    @FXML
    void openCourse(MouseEvent event) throws MalformedURLException 
    {
    	String courseID = ((Node)event.getSource()).getId();
    	//System.out.println(courseID+"\n");
    	
    	switch(courseID)
    	{
	    	case "course1":
	    		lastCourseClicked = 1;
	    		break;
	    		
	    	case "course2":
	    		lastCourseClicked = 2;
	    		break;
	    		
	    	case "course3":
	    		lastCourseClicked = 3;
	    		break;
	    		
	    	case "course4":
	    		lastCourseClicked = 4;
	    		break;
	    		
	    	case "course5":
	    		lastCourseClicked = 5;
	    		break;
	    		
	    	case "course6":
	    		lastCourseClicked = 6;
	    		break;
	    		
	    	default:
	    		lastCourseClicked = 0;
	    		break;
    	}
    	
    	if(courses.get(lastCourseClicked).getCourseName().equals("null"))
    	{
    		return;
    	}
    	URL url = new File("src/application/view/AssignmentView.fxml").toURI().toURL();
    	Pane pane = null;
    	try 
    	{
			pane = FXMLLoader.load(url);
		}
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
        Scene scene = new Scene(pane);// pane you are GOING TO show
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
        
    }
    
    
	public void initializeCourses()
	{
		openedFirstTime = 1;
		for(int i = 0; i<7; i++)
		{
			Course tempCourse = new Course(i, "null");
			courses.add(tempCourse);
		}
	}
	
	public Integer getNextOpen() {
        int returnVal = -1;
        
        for(Course e: courses){
            String tempKey = e.getCourseName();
            if(tempKey.contains("null")){
                if(e.getIndex() == 0) {
                    continue;
                }
                returnVal = e.getIndex();
                return returnVal;
            }
        }
        return returnVal;
    }
	
	@FXML
    void deleteCourse1(ActionEvent event) {
        labelIDS.get(1).setText("*");
        courseIDS.get(1).setOpacity(.2);
        courses.get(1).setCourseName("null");
        
    }
	
	
	@FXML
    public void createCourse(ActionEvent event)
    {
        //prompt user for the course name when they click the button
        //update the corresponding label to match the input name
        TextInputDialog textInput = new TextInputDialog();
        textInput.setTitle("Create Course");
        textInput.getDialogPane().setContentText("Please enter a course name:");
        textInput.setHeaderText("Course Creation");
        
        Optional<String> result = textInput.showAndWait();
        TextField input = textInput.getEditor();
        
        if(input.getText() != null && input.getText().toString().length() != 0)
        {
            int tempCourseNum = getNextOpen();
            if(tempCourseNum == -1) 
            {
                //no open courses
            	Alert a = new Alert(AlertType.ERROR);
                a.setContentText("no room for more courses");
                a.show();
                return;
            }
            //user input valid
            labelIDS.get(tempCourseNum).setText(input.getText().toString());
            courseIDS.get(tempCourseNum).setOpacity(1);
            courses.get(tempCourseNum).setCourseName(input.getText().toString());
            Course toAdd = new Course(tempCourseNum, input.getText().toString());
            courses.set(tempCourseNum, toAdd);    
        }
        else 
        {
            //user input invalid
        	Alert a = new Alert(AlertType.ERROR);
        	a.setContentText("Invalid input.");
            a.show();
        }
    }
       
	
	@FXML
	public void deleteCourseMenuPressed(ActionEvent event)
	{
		
		delete_course1.setVisible(true);
		delete_course2.setVisible(true);
		delete_course3.setVisible(true);
		delete_course4.setVisible(true);
		delete_course5.setVisible(true);
		delete_course6.setVisible(true);
		Alert invalidAlert = new Alert(AlertType.WARNING);
		invalidAlert.setContentText("The data for whichever course you choose will be deleted.\nProceed with caution, as you could lose data..");
		invalidAlert.show();
		//show the delete buttons
		
	}
	
	public void deleteCourseButton(MouseEvent event)
	{
		//delete the respective course
		String courseToDelete = ((Node)event.getSource()).getId();
		switch(courseToDelete)
		{
			case "delete_course1":
				courses.get(1).setCourseName("null");
				courseIDS.get(1).setOpacity(.2);
				labelIDS.get(1).setText("*");
				break;
				
			case "delete_course3":
				courses.get(3).setCourseName("null");
				courseIDS.get(3).setOpacity(.2);
				labelIDS.get(3).setText("*");
				break;
				
			case "delete_course2":
				courses.get(2).setCourseName("null");
				courseIDS.get(2).setOpacity(.2);
				labelIDS.get(2).setText("*");
				break;
				
			case "delete_course4":
				courses.get(4).setCourseName("null");
				courseIDS.get(4).setOpacity(.2);
				labelIDS.get(4).setText("*");
				break;
				
			case "delete_course5":
				courses.get(5).setCourseName("null");
				courseIDS.get(5).setOpacity(.2);
				labelIDS.get(5).setText("*");
				break;
				
			case "delete_course6":
				courses.get(6).setCourseName("null");
				courseIDS.get(6).setOpacity(.2);
				labelIDS.get(6).setText("*");
				break;
				
			default:
				System.out.println("error!\n");
				break;
		}
		initialize();
		
		//then set them all to invisible:
		delete_course1.setVisible(false);
		delete_course2.setVisible(false);
		delete_course3.setVisible(false);
		delete_course4.setVisible(false);
		delete_course5.setVisible(false);
		delete_course6.setVisible(false);
	}
}