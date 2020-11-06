package com.jdc.mdy.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Stock {

	private int id;
	private boolean isStockIn;
	private LocalDateTime date;
	private User user;
	private Supplier supplier;
	
	private List<StockDetail> stDetails;
	private boolean active;
}
