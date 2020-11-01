package com.jdc.mdy.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemController {

    @FXML
    private TextField tf_itemName;

    @FXML
    private TextField tf_price;

    @FXML
    private ComboBox<?> cbo_category;

    @FXML
    private CheckBox ch_active;

    @FXML
    private TableView<?> tv_item;

    @FXML
    private TableColumn<?, ?> col_num;

}
