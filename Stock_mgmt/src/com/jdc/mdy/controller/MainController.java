package com.jdc.mdy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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

	}

	@FXML
	void formClick(MouseEvent event) {

		HBox hb = (HBox) event.getSource();
		hb.getChildren().filtered(lb->lb instanceof Label).forEach(l->{
			Label ll=(Label) l;
			
			if (ll.getText().equals("EXIT")) {
				lb_form.getScene().getWindow().hide();
			} else {
				lb_form.setText(ll.getText());
				loadView(hb.getId());
			}
			
		});
			
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

	private void loadView(String viewName) {
		try {
			Parent root = FXMLLoader.load(MainController.class.getResource("view/" + viewName + ".fxml"));
			st_pane.getChildren().clear();
			st_pane.getChildren().add(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadView("User");

	}

}
