package com.jdc.mdy.entity;


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
	
}
