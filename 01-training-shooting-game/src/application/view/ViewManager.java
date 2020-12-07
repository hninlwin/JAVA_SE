package application.view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class ViewManager {

	private Stage mainStage;
	private AnchorPane mainPane;
	private Scene mainScene;
	private ShootingSubScene shipChooserScene;
	private ShootingSubScene shipScoreScene;
	private ShootingSubScene shipHelpScene;
	private ShootingSubScene isHiddenScene;
	
	
	private static final int button_x = 50, button_y = 50;
	private List<ShootingButton> listButtons;

	private static final String bg_img = "application/view/images/background.jpg";

	public enum BtnName {
		START, SCORE, HELP, EXIT
	}

	public ViewManager() {
		mainStage = new Stage();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, 800, 600);
		mainStage.setScene(mainScene);
		listButtons = new ArrayList<ShootingButton>();
		createBrackground();
		createButton();
		createLogo();
	}
	/* -----------create logo-------------- */

	private void createLogo() {
		Label logo = new Label("SHIP SHOOTING GAME !");
		logo.setStyle("-fx-text-fill:white;-fx-font-size:40px;");
		logo.setLayoutX(300);
		logo.setLayoutY(20);
		mainPane.getChildren().add(logo);
	}

	/* ------------create button and set layout---------- */

	private void createButton() {

		for (BtnName btn : BtnName.values()) {
			ShootingButton button = new ShootingButton(btn.name());		
			addMenuButton(button);
		}
	}

	private void addMenuButton(ShootingButton button) {
		button.setLayoutX(button_x);
		button.setLayoutY(button_y + listButtons.size() * 80);
		listButtons.add(button);
		mainPane.getChildren().add(button);

		button.setOnAction(e -> getActionButton(button.getText()));
	}

	/*-------------- action button -------*/
	private void getActionButton(String text) {
		switch (text) {
		case "START":
			shipChooserScene=getSceneForm(shipChooserScene);
			break;
		case "SCORE":

			break;
		case "HELP":

			break;
		default:
			Platform.exit();
			break;
		}
	}

	private ShootingSubScene getSceneForm(ShootingSubScene shipChooserScene) {
		
		if(shipChooserScene==null) {
			shipChooserScene=new ShootingSubScene();
			mainPane.getChildren().add(shipChooserScene);
		}
		
		return shipChooserScene;
	}
	/* ----------create background to anchor pane ---- */
	private void createBrackground() {
		Image img = new Image(bg_img);
		BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(bg));
	}

	public Stage getMainStage() {
		return mainStage;
	}

}
