package com.jdc.mdy.controller;


public class ButtonManagerControl {
	
	private static ButtonManager btnManager;

	public ButtonManagerControl(ButtonManager btnManager) {
		super();
		ButtonManagerControl.btnManager = btnManager;
	}
	
	public static void getButtonAction(String name) {
		
		switch(name) {
		case "lb_save":
			btnManager.save();
			break;
		case "lb_update":
			btnManager.update();
			break;
		case "lb_search":
			btnManager.search();
			break;
		case "lb_delete":
			btnManager.delete();
			break;
		}
	}
	
	
}
