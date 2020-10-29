package com.jdc.mdy.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private HBox supplier;

    @FXML
    private HBox item;

    @FXML
    private HBox category;

    @FXML
    private HBox stock;

    @FXML
    private HBox stockHistory;

    @FXML
    private HBox user;

    @FXML
    private HBox exit;

    @FXML
    private Label lb_form;

    @FXML
    private Label lb_message;

    @FXML
    private StackPane st_pane;

    @FXML
    void delete() {

    }

    @FXML
    void save() {

    }

    @FXML
    void search() {

    }

    @FXML
    void update() {

    	System.out.println("hello");
    }
    
    public static void showMain() {

		try {
			
			Parent root = FXMLLoader.load(MainController.class.getResource("view/Main.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.centerOnScreen();			
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


