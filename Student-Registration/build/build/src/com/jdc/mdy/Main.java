package com.jdc.mdy;


import com.jdc.mdy.controller.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	
	
	public static void main(String[] args) {
		launch(args);
			
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root=FXMLLoader.load(LoginController.class.getResource("view/Login.fxml"));
		Scene sc=new Scene(root);
		stage.setScene(sc);
		stage.show();
		
		
	}

}
