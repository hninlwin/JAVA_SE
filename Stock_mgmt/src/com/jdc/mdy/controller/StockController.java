package com.jdc.mdy.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.Item;
import com.jdc.mdy.entity.Stock;
import com.jdc.mdy.entity.StockDetail;
import com.jdc.mdy.entity.Supplier;
import com.jdc.mdy.service.CategoryService;
import com.jdc.mdy.service.ItemService;
import com.jdc.mdy.service.StockService;
import com.jdc.mdy.service.SupplierService;
import com.jdc.mdy.utils.MessageManager;
import com.jdc.mdy.utils.MessageManager.MessageStyle;
import com.jdc.mdy.utils.StockException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class StockController implements Initializable {

	@FXML
	private ComboBox<Category> cbo_category;
	@FXML
	private ComboBox<Supplier> cbo_supplier;

	@FXML
	private TextField tf_item;
	@FXML
	private TextField tf_prev_qty;

	@FXML
	private CheckBox ch_active;
	@FXML
	private CheckBox ch_isStockIn;
	@FXML
	private Label lb_stock;
	@FXML
	private TableView<Item> tv_item;

	@FXML
	private TableColumn<Item, Integer> col_num_item;

	@FXML
	private TableView<StockDetail> tv_stock;

	@FXML
	private TableColumn<StockDetail, Integer> col_num_stock;

	@FXML
	private TableColumn<StockDetail, Integer> col_qty;

	private CategoryService cService;
	private ItemService iService;
	private SupplierService sService;
	private StockService stock_service;

	@FXML
	void addStock(ActionEvent event) {
		save();
		clear();
	}

	@FXML
	void clearStock() {
		cbo_category.getSelectionModel().clearSelection();
		clear();
		tv_stock.getItems().clear();
		tv_item.setDisable(false);
		loadViewItem(null, null);
	}

	void clear() {
		tf_item.clear();
		tf_prev_qty.clear();
		ch_isStockIn.setSelected(true);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cService = new CategoryService();
		iService = new ItemService();
		sService = new SupplierService();
		stock_service = new StockService();
		

		cbo_category.getItems().addAll(cService.findAll());
		cbo_supplier.getItems().addAll(sService.findAll());
		
		tv_item.getItems().addAll(iService.findAll(null, null));

		ch_isStockIn.selectedProperty().addListener((a, b, c) -> {
			cbo_supplier.setDisable(c);		
			lb_stock.setText(c ? "StockIn Table" : "StockOut Table");
			
		});

		col_num_item.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_item.getItems().indexOf(col.getValue()) + 1));
		col_num_stock.setCellValueFactory(
				col -> new ReadOnlyObjectWrapper<Integer>(tv_stock.getItems().indexOf(col.getValue()) + 1));

		cbo_category.valueProperty().addListener((a, b, c) -> loadViewItem(c, tf_item.getText()));

		tf_item.textProperty().addListener((a, b, c) -> loadViewItem(cbo_category.getValue(), c));

		tv_item.setOnMouseClicked(e -> {
			
			tf_prev_qty.setText(checkQty());
		
			if (e.getClickCount() == 2) {
				addStockIn();
			}
		});

		col_qty.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {

			@Override
			public String toString(Integer object) {

				if (null != object) {
					return object.toString();
				}
				return null;
			}

			@Override
			public Integer fromString(String string) {
				if (null != string && !string.isEmpty()) {
					return Integer.parseInt(string);
				}
				return null;
			}
		}));
		tv_stock.setEditable(true);
		col_qty.setOnEditCommit(event -> {
			StockDetail sd = event.getRowValue();
			sd.setQty(event.getNewValue());
			tv_stock.refresh();
		});

	}

	private String checkQty() {
		
		try {
			Item item = tv_item.getSelectionModel().getSelectedItem();

			String total = String.valueOf(
					stock_service.totalStockInByItem(item, true) - stock_service.totalStockInByItem(item, false));
			
			if (!ch_isStockIn.isSelected() && (total == null || total.equals("0"))) {
				tv_item.setDisable(true);
				throw new StockException("There is no item stock left !");
			}
			return total;

		} catch (Exception e) {
			MessageManager.showMessage(e.getMessage(), MessageStyle.error);
		}
		
		return "0";
	}

	private void addStockIn() {

		Item item = tv_item.getSelectionModel().getSelectedItem();
		StockDetail sd = findByItem(item);

		if (null != sd) {
			sd.setQty(sd.getQty() + 1);
			tv_stock.refresh();

		} else {
			sd = new StockDetail();
			sd.setQty(1);
			sd.setItem(item);
			sd.setActive(true);
			tv_stock.getItems().add(sd);

		}

	}

	private StockDetail findByItem(Item item) {
		for (StockDetail stock : tv_stock.getItems()) {
			if (stock.getItem().getId() == item.getId()) {
				return stock;
			}
		}
		return null;
	}

	void loadViewItem(Category cat, String name) {

		tv_item.getItems().clear();
		tv_item.getItems().addAll(iService.findAll(name, cat));
	}

	
	public void save() {
		try {

			Stock stock = new Stock();

			stock.setStDetails(tv_stock.getItems());
			stock.setSupplier(cbo_supplier.getValue());
			stock.setUser(MainController.getUser());
			stock.setDate(LocalDate.now());
			stock.setStockIn(ch_isStockIn.isSelected());
			stock.setActive(true);
			stock_service.save(stock);
			clearStock();

		} catch (Exception e) {
			MessageManager.showMessage(e.getMessage(), MessageStyle.error);
		}
	}

	

}
