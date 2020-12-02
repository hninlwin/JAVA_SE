package com.jdc.mdy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.model.Contact;
import com.jdc.mdy.model.Contact.City;
import com.jdc.mdy.model.Student;
import com.jdc.mdy.service.StudentService;
import com.jdc.mdy.utis.Message;
import com.jdc.mdy.utis.Message.MStyle;
import com.jdc.mdy.utis.StudentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController implements Initializable{

    @FXML
    private TextField tf_name;

    @FXML
    private TextArea ta_address;

    @FXML
    private TextField tf_roll;

    @FXML
    private TextField tf_phone;
    
    @FXML
    private ComboBox<City>cbo_city;

    @FXML
    private TableView<Student> tv_student;
    @FXML
    private Label lb_message;
    
    private Message message;
    
    private StudentService service;

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    	try {
    		if(tf_name.getText()==null || tf_name.getText().isEmpty()) {
    			throw new StudentException("Please type student name !");
    		}
    		
    		if(tf_phone.getText()==null || tf_phone.getText().isEmpty()) {
    			throw new StudentException("Please type student phone !");
    		}
    		if(ta_address.getText()==null || ta_address.getText().isEmpty()) {
    			throw new StudentException("Please type student address !");
    		}
    		
    		
    		Student st=new Student();
    		Contact c=new Contact();
    		c.setCity(cbo_city.getValue()==null?City.Yangon:cbo_city.getValue());
    		c.setAddress(ta_address.getText());
    		c.setPhone(tf_phone.getText());
    		st.setName(tf_name.getText());
    		st.setRoll(tf_roll.getText());
    		st.setContact(c);
    		
    		service.save(st);
    		
    		
    	}catch (Exception e) {
			Message.showMessage(e.getMessage(), MStyle.WARNNING);
		}
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbo_city.getItems().addAll(City.values());
		 message=new Message(lb_message);
		 service=new StudentService();
	}

    
}
