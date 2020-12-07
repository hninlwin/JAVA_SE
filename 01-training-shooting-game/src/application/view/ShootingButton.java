package application.view;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class ShootingButton extends Button {
	
	private static final String btn_show_img="-fx-border-radius:50;-fx-background-radius:50;-fx-background-color:transparent;-fx-background-image:url('application/view/images/btn.png')";
	private static final String btn_press_img="-fx-background-color:transparent;-fx-background-image:url('application/view/images/btn_press.png')";

	public ShootingButton(String name) {
		
		setText(name);
		setPrefWidth(150);
		setPrefHeight(50);
		
		setStyle(btn_show_img);
		setInitializer();
	}

	private void setInitializer() {
		setOnMousePressed(e->{
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				setStyle(btn_press_img);
				setPrefHeight(49);
				setLayoutY(getLayoutY()-5);
			}
		});
		setOnMouseReleased(e->{
			if(e.getButton().equals(MouseButton.PRIMARY)) {
				setStyle(btn_show_img);
				setPrefHeight(50);
				setLayoutY(getLayoutY()+5);
			}
		});
	}

	
	
	

}
