package com.jdc.mdy.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.Item;
import com.jdc.mdy.service.CategoryService;
import com.jdc.mdy.service.ItemService;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;
import com.jdc.mdy.utils.StockException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemController implements ButtonManager {

	@FXML
	private TextField tf_itemName;

	@FXML
	private TextField tf_price;

	@FXML
	private ComboBox<Category> cbo_category;

	@FXML
	private CheckBox ch_active;

	@FXML
	private TableView<Item> tv_item;

	@FXML
	private TableColumn<Item, Integer> col_num;
	private ItemService itemService;
	private CategoryService catService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		itemService = new ItemService();
		catService = new CategoryService();
		cbo_category.getItems().addAll(catService.findAll());
		 loadView();
		new ButtonManagerControl(this);
		col_num.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_item.getItems().indexOf(col.getValue()) + 1));
	
	
	}

	@Override
	public void save() {

		try {
			if (tf_itemName.getText().isEmpty() || tf_itemName.getText() == null) {
				throw new StockException("Please type item name !");
			}
			if (tf_price.getText().isEmpty() || tf_price.getText() == null 
					|| !tf_price.getText().matches("\\d+")) {
				
				throw new StockException("Please type price and number only ");
				
			}
			Item item=new Item();
			item.setName(tf_itemName.getText());
			item.setPrice(Integer.parseInt(tf_price.getText()));
			item.setCategory(cbo_category.getValue());
			
			itemService.save(item);
			loadView();
			
		} catch (Exception e) {
			MessageManager.showMessage(e.getMessage(), MessageStyle.error);
		}
	}

	@Override
	public void search() {
		System.out.println("save");
	}

	@Override
	public void delete() {
		System.out.println("save");
	}

	@Override
	public void update() {
		System.out.println("save");
	}

	private void loadView() {

		tv_item.getItems().clear();
		tv_item.getItems().addAll(itemService.findAll());
	}

}
