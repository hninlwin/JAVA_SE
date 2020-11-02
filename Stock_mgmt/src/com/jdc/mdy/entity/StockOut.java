package com.jdc.mdy.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StockOut {

	private int id;
	private int out_qty;
	private LocalDate out_date;
	private Supplier supplier;
	private boolean active;
}
