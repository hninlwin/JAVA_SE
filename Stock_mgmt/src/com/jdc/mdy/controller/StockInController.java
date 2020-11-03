package com.jdc.mdy.controller;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.Item;
import com.jdc.mdy.entity.StockIn;
import com.jdc.mdy.entity.Supplier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockInController {

	@FXML
    private ComboBox<Category> cbo_category;

    @FXML
    private ComboBox<Item> cbo_item;

    @FXML
    private ComboBox<Supplier> cbo_supplier;

    @FXML
    private TextField tf_qty;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<StockIn> tv_stock;

    @FXML
    private TableColumn<StockIn, Integer> col_num;

    @FXML
    void addStock(ActionEvent event) {

    }

    @FXML
    void clearStock(ActionEvent event) {

    }

}
