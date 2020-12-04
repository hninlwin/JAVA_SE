package com.jdc.mdy.model;

import com.jdc.mdy.model.Contact.City;

public class Student {
	
	private int id;
	private String name;
	private String roll;
	private Contact contact;
	private boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoll() {
		return roll;
	}
		
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public String getPhone() {
		return getContact().getPhone();
	}
	
	public City getCity() {
		return getContact().getCity();
	}
	
	public String getAddress() {
		return getContact().getAddress();
	}
	
	public int getContactId(){
		return getContact().getId();
	}
	
	

}
