package com.jdc.mdy.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController {

    @FXML
    private TextField tf_name;

    @FXML
    private TextArea ta_address;

    @FXML
    private TextField tf_roll;

    @FXML
    private TextField tf_phone;

    @FXML
    private TableView<?> tv_student;

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

	public static void showMain() throws IOException {
		
		Parent root=FXMLLoader.load(StudentController.class.getResource("view/Main.fxml"));
		Stage stage=new Stage();
		stage.setScene(new Scene(root));
		stage.show();
		
		
	}

    
}
