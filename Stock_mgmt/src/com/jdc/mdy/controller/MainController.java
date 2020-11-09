package com.jdc.mdy.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.User;
import com.jdc.mdy.utils.MessageManager;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable {

	@FXML
	private Label lb_form;

	@FXML
	private Label lb_message;

	@FXML
	private StackPane st_pane;
	@FXML
	private VBox vb_button;
	@FXML
	private Label lb_user;
	private static User user;
	
	public static User getUser() {
		return user;
	}
	@FXML
	void formClick(MouseEvent event) {

		HBox hb = (HBox) event.getSource();
		hb.getChildren().filtered(lb -> lb instanceof Label).forEach(l -> {
			Label ll = (Label) l;

			if (ll.getText().equals("EXIT")) {
				lb_form.getScene().getWindow().hide();
			} else {
				lb_form.setText(ll.getText());
				loadView(hb.getId());
			}

		});

	}
	
	@FXML
	void btnControls(MouseEvent event) {
		
		Label lb=(Label) event.getSource();
		ButtonManagerControl.getButtonAction(lb.getId());
		
		
	}

	
	public static void showMain(User user) {
		
		MainController.user=user;

		try {

			Parent root = FXMLLoader.load(MainController.class.getResource("view/Main.fxml"));
			Stage stage = new Stage();
			
			//Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadView(String viewName) {
		try {
			Parent root = FXMLLoader.load(MainController.class.getResource("view/" + viewName + ".fxml"));
			st_pane.getChildren().clear();
			st_pane.getChildren().add(root);
			slideAnimation();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void slideAnimation() {
		TranslateTransition tr = new TranslateTransition();
		
		tr.setNode(st_pane);
		tr.setAutoReverse(false);
		tr.setDuration(Duration.seconds(1));

		tr.setFromX(800);
		tr.setToX(0);

		tr.play();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadView("User");
		lb_form.setText("User");
		lb_user.setText(user.getName());
		new MessageManager(lb_message);
	
	}

}
