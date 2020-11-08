package com.jdc.mdy.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockDetail {

	private int id;
	private int qty;
	private Item item;
	private Stock stock;
	private boolean active;
	
	public Category getCategory() {
		return item.getCategory();
	}
	
	public int getPrice() {
		return item.getPrice();
	}
	
	public Supplier getSupplier() {
		return stock.getSupplier();
	}
	
	public LocalDate getDate() {
		return stock.getDate();
	}
	
	
	
	public String getStockIn() {
		
		return stock.isStockIn()?"✓":"✘";
	}
	
	
	
}
