package com.jdc.mdy.utils;

import javafx.scene.control.Label;

public class StringUtils {
	
	
	public static void showMessage(Label lb) {
		
		lb.setVisible(true);
		
		Thread th=new Thread(()->{
			
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
