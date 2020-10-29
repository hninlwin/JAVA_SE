package com.jdc.mdy.controller;



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

    	MainController.showMain();
    	cancel();
    	
    }

	
}
