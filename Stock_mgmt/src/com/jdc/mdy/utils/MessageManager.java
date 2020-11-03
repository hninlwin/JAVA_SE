package com.jdc.mdy.utils;

import javafx.scene.control.Label;

public class MessageManager {

	private static Label lb;

	public enum MessageStyle {
		success, warning, error
	}

	public MessageManager(Label lb) {
		MessageManager.lb = lb;
	}

	private static void setLabelColor(MessageStyle styleType) {

		switch (styleType) {
		case success:
			lb.setStyle("-fx-text-fill: green;");
			break;
		case warning:
			lb.setStyle("-fx-text-fill: orange;");
			break;
		case error:
			lb.setStyle("-fx-text-fill: red;");
			break;

		default:
			lb.setStyle("-fx-text-fill: #0277BD;");
			break;
		}
		
	}

	public static void showMessage(String message, MessageStyle styleType) {

		lb.setVisible(true);
		lb.setText(message);
		
		setLabelColor(styleType);
		
		Thread th = new Thread(() -> {

			try {

				Thread.sleep(3000);
				lb.setVisible(false);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		});
		th.start();
	}
}
