package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.service.CategoryService;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;
import com.jdc.mdy.utils.StockException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryController implements ButtonManager{

    @FXML
    private TextField tf_category;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<Category> tv_category;
    @FXML
    private TableColumn<Category, Integer> col_num;
    private CategoryService services;
    
    
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		services=new CategoryService();
		new ButtonManagerControl(this);
		loadView();
		col_num.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Integer>(tv_category.getItems().indexOf(column.getValue()) + 1));
		
	}

	@Override
	public void save() {
		try {
			if(tf_category.getText()==null || tf_category.getText().isEmpty()) {
				throw new StockException("Please type category !");
			}
			services.save(tf_category.getText());
			loadView();
		}catch (Exception e) {
			MessageManager.showMessage(e.getMessage(),MessageStyle.error);
		}
	}

	private void loadView() {
		tv_category.getItems().clear();
		tv_category.getItems().addAll(services.findAll());
		
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
