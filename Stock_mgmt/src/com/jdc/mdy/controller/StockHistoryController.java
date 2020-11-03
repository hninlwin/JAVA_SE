package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.StockDetail;
import com.jdc.mdy.entity.Supplier;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockHistoryController implements ButtonManager{

    @FXML
    private DatePicker dtp_from;

    @FXML
    private DatePicker dtp_to;

    @FXML
    private ComboBox<Supplier> cbo_supplier;

    @FXML
    private ComboBox<Category> cbo_category;

    @FXML
    private TextField tf_item;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<StockDetail> tv_stock;

    @FXML
    private TableColumn<StockDetail, Integer> col_num;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
