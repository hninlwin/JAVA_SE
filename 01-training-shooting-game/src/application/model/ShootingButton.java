package application.model;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;

public class ShootingButton extends Button {

	private static final String btn_show_img = "-fx-background-color:transparent;-fx-background-image:url('application/view/images/button-show.png')";
	private static final String btn_press_img = "-fx-background-color:transparent;-fx-background-image:url('application/view/images/button-press.png')";

	public ShootingButton(String name) {

		setText(name);
		setPrefWidth(171);
		setPrefHeight(70);
		getStyleClass().add("button_show");
		setStyle(btn_show_img);
		setInitializer();
	}

	private void setInitializer() {
		setOnMousePressed(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				setStyle(btn_press_img);
				setPrefHeight(69);
				setLayoutY(getLayoutY() - 5);
			}
		});
		setOnMouseReleased(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				setStyle(btn_show_img);
				setPrefHeight(70);
				setLayoutY(getLayoutY() + 5);
			}
		});
	}

}
