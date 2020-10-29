package com.jdc.mdy.entity;

public class User {

	private int id;
	private String name;
	private String password;
	private Role role;
	
	enum Role{
		Admin,Cashier
	}
	
	
	
}
