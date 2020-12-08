package application.model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ShootingSubScene extends SubScene {
	
	private boolean isHidden;
	
	private static AnchorPane sub=new AnchorPane();
	

	public ShootingSubScene() {
		super(sub, 550, 350);
		isHidden=true;
		setLayoutX(800);
		setLayoutY(100);
		
	}


	public void moveScene() {
		TranslateTransition tr=new TranslateTransition();
		tr.setDuration(Duration.seconds(1));
		tr.setNode(this);
		tr.setToX(-500);
		tr.play();
	}


	public AnchorPane getPane() {
		return sub;
		
	}

}
