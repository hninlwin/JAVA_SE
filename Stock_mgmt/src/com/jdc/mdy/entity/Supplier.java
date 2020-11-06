package com.jdc.mdy.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Supplier {
	
	private int id;
	private String name;
	private boolean active;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

}
