package com.jdc.mdy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

	private int id;
	private String name;
	private int price;
	private Category category;
	private boolean active;
	
	@Override
	public String toString() {
		return name;
	}
}
