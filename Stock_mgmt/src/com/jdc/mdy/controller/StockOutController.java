package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.Item;
import com.jdc.mdy.entity.StockOut;
import com.jdc.mdy.entity.Supplier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockOutController implements ButtonManager{

    @FXML
    private ComboBox<Category> cbo_category;

    @FXML
    private ComboBox<Item> cbo_item;

    @FXML
    private ComboBox<Supplier> cbo_supplier;

    @FXML
    private TextField tf_outQty;

    @FXML
    private TextField tf_currentQty;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<StockOut> tv_stock;

    @FXML
    private TableColumn<StockOut, Integer> col_num;

    @FXML
    void addStock(ActionEvent event) {

    }

    @FXML
    void clearStock(ActionEvent event) {

    }

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
