package com.jdc.mdy;

import com.jdc.mdy.controller.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws Exception {
		
		
		Parent root=FXMLLoader.load(LoginController.class.getResource("view/Login.fxml"));
		stage.setScene(new Scene(root));
		stage.centerOnScreen();
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
			
		
	}

}
