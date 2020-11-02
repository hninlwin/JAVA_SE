package com.jdc.mdy.entity;

import lombok.Data;

@Data
public class Item {

	private int id;
	private String name;
	private int price;
	private Category category;
	private boolean active;
}
