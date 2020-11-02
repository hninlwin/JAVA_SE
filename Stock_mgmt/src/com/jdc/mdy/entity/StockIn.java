package com.jdc.mdy.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StockIn {

	private int id;
	private int in_qty;
	private LocalDate in_date;
	private boolean active;
}
