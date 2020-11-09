package com.jdc.mdy.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.mdy.entity.Category;
import com.jdc.mdy.entity.StockDetail;
import com.jdc.mdy.entity.Supplier;
import com.jdc.mdy.service.CategoryService;
import com.jdc.mdy.service.StockService;
import com.jdc.mdy.service.SupplierService;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockHistoryController implements Initializable{

    @FXML
    private TextField tf_item;
    @FXML
    private TextField tf_stockIn;
    @FXML
    private TextField tf_stockOut;
    @FXML
    private TextField tf_remain;

    @FXML
    private ComboBox<Supplier> cbo_supplier;

    @FXML
    private ComboBox<Category> cbo_category;

    @FXML
    private CheckBox ch_stockIn;

    @FXML
    private CheckBox ch_active;
 
    @FXML
    private DatePicker dtp_from;

    @FXML
    private DatePicker dtp_to;

    @FXML
    private TableView<StockDetail> tv_stock;

    @FXML
    private TableColumn<StockDetail, Integer> col_num;
    
    private StockService stService;
    private CategoryService cService;
    private SupplierService spService;
    private int inQty;
    private int outQty;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		stService=new StockService();
		cService=new CategoryService();
		spService=new SupplierService();
		
		cbo_category.getItems().addAll(cService.findAll());
		cbo_supplier.getItems().addAll(spService.findAll());
		loadView();
		col_num.setCellValueFactory(col->new ReadOnlyObjectWrapper<Integer>(tv_stock.getItems().indexOf(col.getValue())+1));
		
		cbo_category.valueProperty().addListener((a,b,c)->loadView());
		cbo_supplier.valueProperty().addListener((a,b,c)->loadView());
		ch_stockIn.selectedProperty().addListener((a,b,c)->loadView());
		tf_item.textProperty().addListener((a,b,c)->loadView());
		dtp_from.valueProperty().addListener((a,b,c)->loadView());
		dtp_to.valueProperty().addListener((a,b,c)->loadView());
		
		
		
	}



	private void loadView() {
	
		inQty=0;
		outQty=0;
		tv_stock.getItems().clear();
		
		
		List<StockDetail>list=stService.search(cbo_category.getValue(), cbo_supplier.getValue(), tf_item.getText(),ch_stockIn.isSelected(),dtp_from.getValue(),dtp_to.getValue());
		
		list.forEach(sd->{
		
			tv_stock.getItems().add(sd);
			
			if(sd.getStock().isStockIn()) {
				inQty+=sd.getQty();
			}else {
				outQty+=sd.getQty();
			}
			
		});
		checkStock();
	}
	
	private void checkStock() {
		tf_stockIn.setText(inQty+"");
		tf_stockOut.setText(outQty+"");
		tf_remain.setText((inQty-outQty)+"");
	}

	

}
