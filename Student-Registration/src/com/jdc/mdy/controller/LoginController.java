package com.jdc.mdy.controller;


import java.io.File;
import java.io.IOException;

import com.jdc.mdy.utis.StudentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tf_user;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Label lb_message;
    
    private static final String name="admin";
    private static final String pass="admin";

    @FXML
    void cancel() {
    	tf_user.getScene().getWindow().hide();
    	
    }

    @FXML
    void enter(ActionEvent event) {
    	
    	try {
    		
    		if(tf_user.getText()==null ||tf_user.getText().isEmpty()) {
    			throw new StudentException("Please Type user name !");
    		}
    		if(pf_password.getText()==null ||pf_password.getText().isEmpty()) {
    			throw new StudentException("Please Type Password !");
    		}
    		
    		if(tf_user.getText().equals(name) && pf_password.getText().equals(pass)) {
    			StudentController.showMain();
    			cancel();
    		}else {
    			lb_message.setText("plase re-type again");
    		}
    		
    	}catch (Exception e) {
			lb_message.setText(e.getMessage());
		}
    	
    }

	

}
