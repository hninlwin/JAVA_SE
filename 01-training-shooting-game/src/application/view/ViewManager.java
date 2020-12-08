package application.view;

import java.util.ArrayList;
import java.util.List;

import application.model.SHIP;
import application.model.ShipPicker;
import application.model.ShootingButton;
import application.model.ShootingSubScene;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewManager {

	private Stage mainStage;
	private AnchorPane mainPane;
	private Scene mainScene;
	private ShootingSubScene shipChooserScene;
	private ShootingSubScene shipScoreScene;
	private ShootingSubScene shipHelpScene;
	private ShootingSubScene isHiddenScene;

	private static final int button_x = 50, button_y = 150;

	private static final String bg_img = "application/view/images/background.jpg";
	private static final String lb_img = "application/view/images/label_2.png";
	private static List<ShipPicker>list;
	private SHIP ship;

	public enum BtnName {
		START, SCORE, HELP, EXIT
	}

	public ViewManager() {
		mainStage = new Stage();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, 800, 600);
		mainStage.setScene(mainScene);
		mainPane.setBackground(new Background(createBrackground(bg_img)));
		createButton();
		createLogo();
	}
	/* -----------create logo-------------- */

	private void createLogo() {
		Label logo = new Label("SHIP SHOOTING GAME !");
		logo.setStyle("-fx-text-fill:white;-fx-font-size:40px;");
		logo.setLayoutX(300);
		logo.setLayoutY(20);
		logo.setBackground(new Background(createBrackground(lb_img)));
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
		button.setLayoutY(button_y + i * 70);
		mainPane.getChildren().add(button);

		button.setOnAction(e -> getActionButton(button.getText()));
	}

	/*-------------- action button -------*/
	private void getActionButton(String text) {
		switch (text) {
		case "START":
			shipChooserScene = getSceneForm(shipChooserScene);
			checkSubScene(shipChooserScene);
			createShipChooser(shipChooserScene);
			break;
		case "SCORE":
			shipScoreScene = getSceneForm(shipScoreScene);
			checkSubScene(shipScoreScene);
			break;
		case "HELP":
			shipHelpScene = getSceneForm(shipHelpScene);
			checkSubScene(shipHelpScene);
			break;
		default:
			Platform.exit();
			break;
		}
	}

	private void createShipChooser(ShootingSubScene ss) {
		Label lb = new Label("CHOOSE YOUR SHIP !");
		lb.setLayoutX(180);
		lb.setLayoutY(50);

		ss.getPane().getChildren().addAll(lb, createSelectShip(), createStartButton());
	}

	private ShootingButton createStartButton() {
		ShootingButton sb=new ShootingButton("START");
		sb.setLayoutX(280);
		sb.setLayoutY(270);
		
		sb.setOnAction(e->{
			if(ship!=null) {
				GameViewManager gameManager=new GameViewManager();
				gameManager.createNewGame(mainStage,ship);
			}
		});
		
		return sb;
	}

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
		hb.setLayoutX(130);
		hb.setLayoutY(100);
		return hb;
	}

	private void checkSubScene(ShootingSubScene ship) {

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

	/* ----------create background to anchor pane ---- */
	private BackgroundImage createBrackground(String img_path) {
		Image img = new Image(img_path);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, null);

		return bg;

	}

	public Stage getMainStage() {
		return mainStage;
	}

}
