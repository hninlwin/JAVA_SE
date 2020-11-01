package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.User;
import com.jdc.mdy.service.UserService;
import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private Label lb_message;

	@FXML
	private TextField tf_userName;

	@FXML
	private PasswordField pf_password;
	
	private UserService service;
	

	@FXML
	void cancel() {

		lb_message.getScene().getWindow().hide();
	}

	@FXML
	void enter(ActionEvent event) {

		try {

			if (tf_userName.getText().isEmpty() || tf_userName.getText() == null) {
				throw new StockException("Please type user name !");
			}

			if (pf_password.getText().isEmpty() || pf_password.getText() == null) {
				throw new StockException("Please type password !");
			}

			User user=service.searchId(tf_userName.getText());
			
			if(user==null) {
				throw new StockException("Please re_type user name !");
			}
			
			if(!user.getPassword().equals(pf_password.getText())) {
				throw new StockException("Please re_type password !");
			}
			
			MainController.showMain(user);
			cancel();

		} catch (Exception e) {
			lb_message.setText(e.getMessage());
			StringUtils.showMessage(lb_message);

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		service=new UserService();
	}

}
