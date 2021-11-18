package application.model;

import javafx.scene.control.CheckBox;

public class Assignment {
	String name;
	String date;
	CheckBox completion;
	
	public Assignment(String name, String date) {
		this.name=name;
		this.date=date;
		this.completion=new CheckBox();
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setDate(String date) {
		this.date=date;
	}
	public String getDate() {
		return date;
	}
}
