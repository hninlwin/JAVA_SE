package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryController implements ButtonManager{

    @FXML
    private TextField tf_category;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<?> tv_category;
   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new ButtonManagerControl(this);
	}

	@Override
	public void save() {
		System.out.println("save category");
	}

	@Override
	public void search() {
		System.out.println("search category");
	}

	@Override
	public void delete() {
		System.out.println("delete category");
	}

	@Override
	public void update() {
		System.out.println("update category");
	}

}
