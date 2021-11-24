package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Course 
{
	int index;
	String courseName;
	public ObservableList<Assignment> storedAssigns = FXCollections.observableArrayList();
	
	//Constructor for Course
	public Course(int index, String courseName) {
		super();
		this.index = index;
		this.courseName = courseName;
	}
	
	//Setters & Getters
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
}