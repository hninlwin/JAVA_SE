package com.jdc.mdy.controller;

import com.jdc.mdy.utils.StockException;
import com.jdc.mdy.utils.StringUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private Label lb_message;

	@FXML
	private TextField tf_userName;

	@FXML
	private PasswordField pf_password;

	@FXML
	void cancel() {

		lb_message.getScene().getWindow().hide();
	}

	@FXML
	void enter(ActionEvent event) {

		try {
			
			if(tf_userName.getText().isEmpty() || tf_userName.getText()==null) {
				throw new StockException("Please type user name !");
			}
			
			if(pf_password.getText().isEmpty() || pf_password.getText()==null) {
				throw new StockException("Please type password !");
			}

			MainController.showMain();
			cancel();
			
		} catch (Exception e) {
			lb_message.setText(e.getMessage());
			StringUtils.showMessage(lb_message);
			
		}

	}

}
