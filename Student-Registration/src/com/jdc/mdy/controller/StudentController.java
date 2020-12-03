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

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StudentController implements Initializable {

	@FXML
	private TextField tf_name;

	@FXML
	private TextArea ta_address;

	@FXML
	private TextField tf_roll;

	@FXML
	private TextField tf_phone;

	@FXML
	private ComboBox<City> cbo_city;

	@FXML
	private TableView<Student> tv_student;
	@FXML
	private TableColumn<Student, Integer> col_num;
	@FXML
	private Label lb_message;
	@FXML
	private Button btn_save;
	@FXML
	private Button btn_delete;
	@FXML
	private Button btn_clear;
	@FXML
	private Button btn_search;

	private Message message;

	private StudentService service;

	@FXML
	void clear(ActionEvent event) {

		clear();
	}

	@FXML
	void delete(ActionEvent event) {

	}

	@FXML
	void save() {

		try {
			if (tf_name.getText() == null || tf_name.getText().isEmpty()) {
				throw new StudentException("Please type student name !");
			}

			if (tf_phone.getText() == null || tf_phone.getText().isEmpty()) {
				throw new StudentException("Please type student phone !");
			}
			if (ta_address.getText() == null || ta_address.getText().isEmpty()) {
				throw new StudentException("Please type student address !");
			}

			Student st = new Student();
			Contact c = new Contact();
			c.setCity(cbo_city.getValue() == null ? City.Yangon : cbo_city.getValue());
			c.setAddress(ta_address.getText());
			c.setPhone(tf_phone.getText());
			st.setName(tf_name.getText());
			st.setRoll(tf_roll.getText());
			st.setContact(c);

			service.save(st);
			loadView(null);
			clear();

		} catch (Exception e) {
			Message.showMessage(e.getMessage(), MStyle.WARNNING);
		}
	}

	private void clear() {
		tf_name.clear();
		tf_phone.clear();
		tf_roll.clear();
		ta_address.clear();
		cbo_city.getSelectionModel().clearSelection();

	}

	@FXML
	void search(ActionEvent event) {
		
		Student st=new Student();
		st.setName(tf_name.getText());
		st.setRoll(tf_roll.getText());
		Contact c=new Contact();
		c.setAddress(ta_address.getText());
		c.setCity(cbo_city.getValue());
		c.setPhone(tf_phone.getText());
		st.setContact(c);
		loadView(st);
	}

	@FXML
	void enter(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER || event.getCode()==KeyCode.TAB) {
			setCusor();
		}
	}

	void setCusor() {
		String s = this.ta_address.getScene().getFocusOwner().getId();

		switch (s) {
		case "tf_name":
			tf_roll.requestFocus();
			break;
		case "tf_roll":
			tf_phone.requestFocus();
			break;
		case "tf_phone":
			cbo_city.requestFocus();
			break;
		case "cbo_city":
			ta_address.requestFocus();
			break;
		case"ta_address":			
			btn_save.requestFocus();
			break;
		case"btn_save":
			btn_clear.requestFocus();
			break;
		case"btn_clear":
			btn_search.requestFocus();
			break;
		case"btn_search":
			btn_delete.requestFocus();
			break;
		case"btn_delete":
			tf_name.requestFocus();
			break;
				
		}
	}

	public static void showMain() throws IOException {

		Parent root = FXMLLoader.load(StudentController.class.getResource("view/Main.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();

	}

	private void loadView(Student st) {
		tv_student.getItems().clear();
		tv_student.getItems().addAll(service.findStudents(st));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbo_city.getItems().addAll(City.values());
		message = new Message(lb_message);
		service = new StudentService();
		loadView(null);
		col_num.setCellValueFactory(
				e -> new ReadOnlyObjectWrapper<Integer>(tv_student.getItems().indexOf(e.getValue()) + 1));

	}

}
