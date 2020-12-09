package application.view;

import java.util.ArrayList;
import java.util.List;

import application.model.SHIP;
import application.model.ShipPicker;
import application.model.ShootingBGLabel;
import application.model.ShootingButton;
import application.model.ShootingSubScene;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewManager {

	private Stage mainStage;
	private AnchorPane mainPane;
	private Scene mainScene;
	private ShootingSubScene shipChooserScene;
	private ShootingSubScene shipScoreScene;
	private ShootingSubScene shipHelpScene;
	private ShootingSubScene isHiddenScene;

	private static final int button_x = 50, button_y = 170;

	private static final String bg_img = "application/view/images/background.jpg";
	private static final String lb_img = "application/view/images/label_1.png";
	private static final String lb_img2 = "application/view/images/label_2.png";
	
	private static List<ShipPicker>list;
	private SHIP ship;

	public enum BtnName {
		START, SCORE, HELP, EXIT
	}

	public ViewManager() {
		mainStage = new Stage();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, 800, 550);
		mainStage.setScene(mainScene);
		mainPane.setBackground(ShootingBGLabel.createBrackground(bg_img));
		createButton();
		createLogo();
	}
	/* -----------create logo-------------- */

	private void createLogo() {
		
		ShootingBGLabel logo = new ShootingBGLabel("SHIP SHOOTING GAME !","#672006",30);
		logo.setPrefSize(500, 83);
		logo.setLayoutX(220);
		logo.setLayoutY(30);
		logo.setBackground(ShootingBGLabel.createBrackground(lb_img));
		mainPane.getChildren().add(logo);
	}

	/* ------------create button and set layout---------- */

	private void createButton() {

		int i = 0;

		for (BtnName btn : BtnName.values()) {
			ShootingButton button = new ShootingButton(btn.name());
			addMenuButton(button, i);
			++i;
		}
	}

	private void addMenuButton(ShootingButton button, int i) {
		button.setLayoutX(button_x);
		button.setLayoutY(button_y + i * 80);
		button.setTextFill(Color.WHITE);
		mainPane.getChildren().add(button);

		button.setOnAction(e -> getActionButton(button.getText()));
	}

	/*-------------- action button -------*/
	private void getActionButton(String text) {
		switch (text) {
		case "START":
			shipChooserScene = getSceneForm(shipChooserScene);
			slideSubScene(shipChooserScene);
			createShipChooser(shipChooserScene);
			break;
		case "SCORE":
			shipScoreScene = getSceneForm(shipScoreScene);
			slideSubScene(shipScoreScene);
			break;
		case "HELP":
			shipHelpScene = getSceneForm(shipHelpScene);
			slideSubScene(shipHelpScene);
			break;
		default:
			Platform.exit();
			break;
		}
	}

	/* -------------------------ship chooser scene label----------------- */
	private void createShipChooser(ShootingSubScene ss) {
		ShootingBGLabel lb = new ShootingBGLabel("CHOOSE YOUR SHIP !","white",20);		
		lb.setLayoutX(100);
		lb.setLayoutY(30);
		lb.setBackground(ShootingBGLabel.createBrackground(lb_img2));
		ss.getPane().getChildren().addAll(lb, createSelectShip(), createStartButton());
	}
	/* -------------------------ship chooser scene button----------------- */
	private ShootingButton createStartButton() {
		ShootingButton sb=new ShootingButton("START");
		sb.setLayoutX(230);
		sb.setLayoutY(270);
		sb.setTextFill(Color.WHITE);
		sb.setOnAction(e->{
			if(ship!=null) {
				GameViewManager gameManager=new GameViewManager();
				gameManager.createNewGame(mainStage,ship);
			}
		});
		
		return sb;
	}
	/* -------------------------ship chooser scene select ship----------------- */
	private HBox createSelectShip() {
		HBox hb=new HBox();
		hb.setSpacing(5);
		list=new ArrayList<ShipPicker>();
		
		for(SHIP sh:SHIP.values()) {
			ShipPicker sp=new ShipPicker(sh);
			hb.getChildren().add(sp);
			list.add(sp);
			sp.setOnMouseClicked(e->{
				
				for(ShipPicker shPicker:list) {
					shPicker.ChoosenShip(false);
				}
				sp.ChoosenShip(true);
				ship=sp.getShip();
			});
		}
		hb.setLayoutX(120);
		hb.setLayoutY(120);
		return hb;
	}
	/* -------------------------ship chooser scene check and show scene by slide ----------------- */
	private void slideSubScene(ShootingSubScene ship) {

		if (isHiddenScene != null) {
			isHiddenScene.moveScene();
			
		}
		ship.moveScene();
		isHiddenScene = ship;
	}
	private ShootingSubScene getSceneForm(ShootingSubScene shootScene) {
		if (shootScene == null) {
			shootScene = new ShootingSubScene();
			mainPane.getChildren().add(shootScene);
			return shootScene;
		}
		return shootScene;
	}

	
	public Stage getMainStage() {
		return mainStage;
	}

}
