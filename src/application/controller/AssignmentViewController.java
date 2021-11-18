package application.controller;

import java.time.format.DateTimeFormatter;

import application.model.Assignment;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


public class AssignmentViewController {

    @FXML TableView<Assignment> assignmentsTabel;

    @FXML
    private TableColumn<Assignment, String> dateCol;

    @FXML
    private TableColumn<Assignment, CheckBox> compCol;

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
    
    Assignment addedStudent = new Assignment(null, null);

    @FXML
    void backPressed(ActionEvent event) {

    }
    
    public void initialize() {
        createTable();
    }   

    @FXML
    void addPressed(ActionEvent event) {
    	addedStudent.setName(nameAdd.getText());
    	addedStudent.setDate(dateAdd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    	assignmentsTabel.getItems().add(addedStudent);
    }
    
    public void createTable() {
    	compCol.setCellFactory(column -> new CheckBoxTableCell<>());
        nameCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("date"));
        editCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        
        editCol.setCellFactory(param -> new TableCell<Assignment, Assignment>() {
            private final Button deleteButton = new Button("Delete");
            private final Button beginButton = new Button("Start");
            private final HBox buttons = new HBox(deleteButton, beginButton);
            //buttons.setSpacing(10);

            @Override
            protected void updateItem(Assignment assign, boolean empty) {
                super.updateItem(assign, empty);

                if (assign == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(empty ? null : buttons);
                deleteButton.setOnAction(
                    event -> getTableView().getItems().remove(assign)
                );
                beginButton.setOnAction(
                        event -> getTableView().getItems().remove(assign)
                    );
            }
        });
    }

}

