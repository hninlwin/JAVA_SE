package application.model;


import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ShootingSubScene extends SubScene {

	private boolean isHidden;

	public ShootingSubScene() {
		super(new AnchorPane(), 500, 350);
		AnchorPane pane=getPane();
		pane.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%); -fx-background-insets: 0;-fx-background-radius:30px;-fx-border-radius:30px;");
		setLayoutX(800);
		setLayoutY(150);
		isHidden = true;
		
	}

	
	public void moveScene() {
		TranslateTransition tr = new TranslateTransition();
		tr.setDuration(Duration.seconds(1));
		tr.setNode(this);
		
		if (isHidden) {
			tr.setToX(-570);
			isHidden = false;
		} else {
			tr.setToX(500);
			isHidden = true;
		}
		tr.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();

	}

}
