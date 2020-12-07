package com.jdc.mdy.controller;


import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.utis.Message;
import com.jdc.mdy.utis.Message.MStyle;
import com.jdc.mdy.utis.StudentException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

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
			Message.showMessage(e.getMessage(),MStyle.ERROR);
		}
    	
    }
    
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new Message(lb_message);
	}

	

}
