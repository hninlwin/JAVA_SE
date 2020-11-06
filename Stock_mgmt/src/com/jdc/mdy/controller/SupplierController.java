package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Supplier;
import com.jdc.mdy.service.SupplierService;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;
import com.jdc.mdy.utils.StockException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SupplierController implements ButtonManager{

    @FXML
    private TextField tf_supplier;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<Supplier> tv_supplier;
    @FXML
    private TableColumn<Supplier, Integer> col_num;
    private SupplierService services;
    
    
   
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		services=new SupplierService();
		new ButtonManagerControl(this);
		loadView();
		col_num.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Integer>(tv_supplier.getItems().indexOf(column.getValue()) + 1));
		
	}

	@Override
	public void save() {
		try {
			if(tf_supplier.getText()==null || tf_supplier.getText().isEmpty()) {
				throw new StockException("Please type supplier name !");
			}
			services.save(tf_supplier.getText());
			loadView();
		}catch (Exception e) {
			MessageManager.showMessage(e.getMessage(),MessageStyle.error);
		}
	}

	private void loadView() {
		tv_supplier.getItems().clear();
		tv_supplier.getItems().addAll(services.findAll());
		
	}

	@Override
	public void search() {
		System.out.println("search supplier");
	}

	@Override
	public void delete() {
		System.out.println("delete supplier");
	}

	@Override
	public void update() {
		System.out.println("update supplier");
	}

}
