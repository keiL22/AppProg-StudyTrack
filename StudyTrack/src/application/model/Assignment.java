package application.model;

import javafx.beans.property.SimpleBooleanProperty;

public class Assignment {
    String name;
    String date;
    public boolean need = false;
    Course courseBelongsTo;
    private SimpleBooleanProperty check=new SimpleBooleanProperty(false);
    
    //COnstructors for Assignment
    public Assignment(String name, String date, Course courseBelongsTo, Boolean bool) {
        this.name=name;
        this.date=date;
        this.courseBelongsTo = courseBelongsTo;
        check.set(bool);
    }
    //Setters & Getters 
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
    public SimpleBooleanProperty checking(){
        return check;
    }
}

