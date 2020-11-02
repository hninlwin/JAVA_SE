package com.jdc.mdy.entity;

import lombok.Data;

@Data
public class StockDetail {

	private int id;
	private int initialQty;
	private int currentQty;
	private User user;
	private StockIn stockIn;
	private StockOut stockOut;
	private Item item;
}
