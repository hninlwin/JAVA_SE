package application.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox{
	
	private ImageView circleImg;
	private ImageView shipImg;
	private SHIP ship;
	private static final String choosenImg="application/view/images/greenCircle.png";
	private static final String notChoosenImg="application/view/images/blueCircle.png";
	
	private boolean isChoose;
	
	public ShipPicker(SHIP ship) {
		circleImg=new ImageView(new Image(notChoosenImg));
		shipImg=new ImageView(new Image(ship.getUrl()));
		shipImg.setRotate(180);
		this.ship=ship;
		isChoose=false;
		this.getChildren().addAll(circleImg,shipImg);
	}

	public void ChoosenShip(boolean choose) {
		isChoose=choose;
		String setImage=isChoose?choosenImg:notChoosenImg;
		circleImg.setImage(new Image(setImage));
	}
	public boolean getChoosen() {
		return isChoose;
	}

	public SHIP getShip() {
		
		return ship;
	}
	
}
