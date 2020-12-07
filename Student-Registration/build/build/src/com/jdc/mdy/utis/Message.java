package com.jdc.mdy.utis;

import javafx.scene.control.Label;

public class Message {

	private static Label lb;
	
	public enum MStyle{
		SUCCESS,WARNNING,ERROR
	}

	public Message(Label lb) {
		this.lb = lb;
	}

	public static void showMessage(String message,MStyle style) {
		lb.setText(message);
		lb.setVisible(true);
		selectColor(style);
		
		
		Thread th = new Thread(() -> {

			try {
				
				Thread.sleep(3000);
				lb.setVisible(false);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		th.start();

	}

	private static void selectColor(MStyle style) {
		
		switch(style) {
		
		case SUCCESS:
			lb.setStyle("-fx-text-fill:green;");
			break;
			
		case WARNNING:
			lb.setStyle("-fx-text-fill:blue;");
			break;
			
		case ERROR:
			lb.setStyle("-fx-text-fill:red;");
			break;
		}
	}

}
