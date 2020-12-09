package application.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class ShootingBGLabel extends Label{
	
	public ShootingBGLabel(String text,String color,int size) {
		setText(text);
		setStyle("-fx-text-fill:"+color+";-fx-font-size:"+size+"px;");
		setPrefSize(300, 80);
		setAlignment(Pos.CENTER);
		
	}

	
	/* ----------create background to anchor pane ---- */
	public static Background createBrackground(String img_path) {
		Image img = new Image(img_path);
		
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, null);
		Background backGround=new Background(bg);
		return backGround;

	}

}
