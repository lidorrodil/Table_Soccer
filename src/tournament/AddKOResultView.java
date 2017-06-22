package tournament;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddKOResultView {
	TourView mainView;

	protected Stage stage;

	protected GridPane gridpane;

	protected Button addButton = new Button("Set Result");

	protected Label labelTeam1 = new Label("Team 1");
	protected Label labelTeam2 = new Label("Team 2");
	protected Label gameIdentifier;

	protected TextField textTeam1 = new TextField();
	protected TextField textTeam2 = new TextField();

	protected int[] result = new int[2];

	protected int setCounter = 0;

	/**
	 * Shows the Stage
	 */
	public void start(KOGame game, TourView view) {
		mainView = view;

		stage = new Stage();
		stage.setTitle("Add Result");

		gridpane = new GridPane();
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		gridpane.setPadding(new Insets(5));
		
		gameIdentifier = new Label(getTitle(game));

		labelTeam1.setPrefWidth(100);
		labelTeam2.setPrefWidth(100);
		textTeam1.setMaxWidth(25);
		textTeam2.setMaxWidth(25);
		textTeam1.setPromptText("0");
		textTeam2.setPromptText("0");
		addButton.setPrefWidth(130);

		stage.sizeToScene();

		gridpane.add(gameIdentifier, 0, 0, 2, 1);
		gridpane.add(labelTeam1, 1, 1, 2, 1);
		gridpane.add(labelTeam2, 1, 2, 2, 1);
		gridpane.add(textTeam1, 3, 1, 1, 1);
		gridpane.add(textTeam2, 3, 2, 1, 1);
		gridpane.add(addButton, 1, 3, 3, 1);

		addButton.setOnAction(c -> addButtonPressed(game, mainView));

		Scene scene = new Scene(gridpane);
		stage.setHeight(300);
		stage.setWidth(180);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Set title 
	 */
	public String getTitle(KOGame game){
		String result = "";
		switch (game.getName()) {
		case "quarterFinal1":
			result = "Quarter Final 1";
			break;
		case "quarterFinal2":
			result = "Quarter Final 2";
			break;
		case "quarterFinal3":
			result = "Quarter Final 3";
			break;
		case "quarterFinal4":
			result = "Quarter Final 4";
			break;
		case "semiFinal1":
			result = "Semi Final 1";
			break;
		case "semiFinal2":
			result = "Semi Final 2";
			break;
		case "finale":
			result = "The Final";
			break;
		}
		return result;
	}

	/**
	 * Sets the entered result in an array Array[0] = Score Team 1. <br>
	 * Array[1] = Score Team 2.
	 */
	public void addButtonPressed(KOGame game, TourView view) {
		int team1Score = 0;
		int team2Score = 0;
		team1Score = Integer.parseInt(textTeam1.getText());
		team2Score = Integer.parseInt(textTeam2.getText());
		game.setResult(team1Score, team2Score);
		textTeam1.setPromptText("0");
		textTeam2.setPromptText("0");
		if (game.isKOGameFinished()) {
			switch (game.getName()) {
			case "quarterFinal1":
				view.QFButton1.setDisable(true);
				break;
			case "quarterFinal2":
				view.QFButton2.setDisable(true);
				break;
			case "quarterFinal3":
				view.QFButton3.setDisable(true);
				break;
			case "quarterFinal4":
				view.QFButton4.setDisable(true);
				break;
			case "semiFinal1":
				view.SFButton1.setDisable(true);
				break;
			case "semiFinal2":
				view.SFButton2.setDisable(true);
				break;
			case "finale":
				view.finalsButton.setDisable(true);
				break;
			}
		}
		if (view.QFButton1.isDisabled() && view.QFButton2.isDisabled() && view.QFButton3.isDisabled()
				&& view.QFButton4.isDisabled() && view.startSFButton.isDisabled()) {
			view.startSFButton.setDisable(false);
		}
		if(view.SFButton1.isDisabled() && view.SFButton2.isDisabled() && view.finalsButton.isDisabled()){
			view.startFButton.setDisable(false);
		}
		if(view.finalsButton.isDisabled() && view.endFinalButton.isDisabled()){
			view.endFinalButton.setDisable(false);
		}
		stage.hide();
		// result[0] = team1Score;
		// result[1] = team2Score;
	}

	/**
	 * Hides the Stage
	 */
	public void stop() {
		stage.hide();
	}

}
