package tournament;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Enables you to add results to the preliminary games.
 * @author joelf
 *
 */

public class ShowPreliminaryGamesView {

	protected Stage stage;
	protected Scene scene;
	
	protected Button finishButton = new Button("Finish Preliminaries");
	protected Button cancelButton = new Button("Cancel Game");
	protected Button nextGameButton1 = new Button("Next Game");
	protected Button nextGameButton2 = new Button("Next Game");
	protected Button nextGameButton3 = new Button("Next Game");
	protected Button nextGameButton4 = new Button("Next Game");

	protected Label labelMatch = new Label("Match");
	protected Label labelResult = new Label("Result");
	protected Label labelTeamName1 = new Label();
	protected Label labelTeamName2 = new Label();
	protected Label labelTeamName3 = new Label();
	protected Label labelTeamName4 = new Label();

	protected TextField result11 = new TextField();
	protected TextField result12 = new TextField();
	protected TextField result21 = new TextField();
	protected TextField result22 = new TextField();
	protected TextField result31 = new TextField();
	protected TextField result32 = new TextField();
	protected TextField result41 = new TextField();
	protected TextField result42 = new TextField();

	protected GridPane root;

	private String pairing1;
	private String pairing2;
	private String pairing3;
	private String pairing4;

	private int gamesList1Pointer = 0;
	private int gamesList2Pointer = 0;
	private int gamesList3Pointer = 0;
	private int gamesList4Pointer = 0;

	private ObservableList<PreliminaryGame> gamesList1;
	private ObservableList<PreliminaryGame> gamesList2;
	private ObservableList<PreliminaryGame> gamesList3;
	private ObservableList<PreliminaryGame> gamesList4;

	private boolean games1Finished = false;
	private boolean games2Finished = false;
	private boolean games3Finished = false;
	private boolean games4Finished = false;
	private boolean allGamesFinished = false;

	public void start() {
		root = new GridPane();
		root.setPadding(new Insets(10));
		root.setHgap(10);
		root.setVgap(10);

		createGamesLists();
		setInitialPairings();
		createGUI(); // Do this after initial Pairing.

		cancelButton.setOnAction(c -> stop());

		nextGameButton1.setOnAction(c -> setPairing1());
		nextGameButton2.setOnAction(c -> setPairing2());
		nextGameButton3.setOnAction(c -> setPairing3());
		nextGameButton4.setOnAction(c -> setPairing4());

		Scene scene = new Scene(root);
		root.setStyle(
				"-fx-background-color: radial-gradient(focus-angle 45deg, focus-distance 50%, center 25% 25%, radius 60%, reflect, gray, darkgray 30%, black)");
		scene.getStylesheets().add(getClass().getResource("TourView.css").toExternalForm());

		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Add Game");
		stage.show();
	}
	
	public void start2(){
		stage.show();
	}

	/**
	 * If games1Finished, games2Finished, games3Finished and games4Finished are
	 * true, allGamesFinished is set to true. Also enables the finished Button.
	 */
	private void checkAllGamesFinished() {
		if (games1Finished && games2Finished && games3Finished && games4Finished) {
			allGamesFinished = true;
			finishButton.setDisable(false);
		}
	}

	/**
	 * Creates the view for the first pairing when this window opens
	 */
	private void setInitialPairings() {
		setPairing1();
		setPairing2();
		setPairing3();
		setPairing4();
	}

	/**
	 * Creates Pairing String for Group 1
	 */
	private void setPairing1() {
		if (gamesList1Pointer < gamesList1.size()) {
			pairing1 = gamesList1.get(gamesList1Pointer).toString();
			labelTeamName1.setText(pairing1);
		}
		if (gamesList1Pointer > 0) {
			updateResult1();
		}
		checkGames1Finished();
		gamesList1Pointer++;
	}

	/**
	 * Sets the result to what is written in result11 and result12
	 */
	private void updateResult1() {
		int result1 = Integer.parseInt(result11.getText());
		int result2 = Integer.parseInt(result12.getText());
		result11.clear();
		result12.clear();
		gamesList1.get(gamesList1Pointer-1).setResult(result1, result2);
	}

	/**
	 * Checks whether all games of group 1 are played.
	 */
	private void checkGames1Finished() {
		if (gamesList1Pointer >= gamesList1.size()) {
			games1Finished = true;
			nextGameButton1.setDisable(true);
		}
		checkAllGamesFinished();
	}

	/**
	 * Creates Pairing String for Group 2
	 */
	private void setPairing2() {
		if (gamesList2Pointer < gamesList2.size()) {
			pairing2 = gamesList2.get(gamesList2Pointer).toString();
			labelTeamName2.setText(pairing2);
		}
		if (gamesList2Pointer > 0) {
			updateResult2();
		}
		checkGames2Finished();
		gamesList2Pointer++;
	}

	/**
	 * Sets the result to what is written in result21 and result22
	 */
	private void updateResult2() {
		int result1 = Integer.parseInt(result21.getText());
		int result2 = Integer.parseInt(result22.getText());
		result21.clear();
		result22.clear();
		gamesList2.get(gamesList2Pointer-1).setResult(result1, result2);
	}

	/**
	 * Checks whether all games of group 2 are played.
	 */
	private void checkGames2Finished() {
		if (gamesList2Pointer >= gamesList2.size()) {
			games2Finished = true;
			nextGameButton2.setDisable(true);
		}
		checkAllGamesFinished();
	}

	/**
	 * Creates Pairing String for Group 3
	 */
	private void setPairing3() {
		if (gamesList3Pointer < gamesList3.size()) {
			pairing3 = gamesList3.get(gamesList3Pointer).toString();
			labelTeamName3.setText(pairing3);
		}
		if (gamesList3Pointer > 0) {
			updateResult3();
		}
		checkGames3Finished();
		gamesList3Pointer++;
	}

	/**
	 * Sets the result to what is written in result31 and result32
	 */
	private void updateResult3() {
		int result1 = Integer.parseInt(result31.getText());
		int result2 = Integer.parseInt(result32.getText());
		result31.clear();
		result32.clear();
		gamesList3.get(gamesList3Pointer-1).setResult(result1, result2);
	}

	/**
	 * Checks whether all games of group 3 are played.
	 */
	private void checkGames3Finished() {
		if (gamesList3Pointer >= gamesList3.size()) {
			games3Finished = true;
			nextGameButton3.setDisable(true);
		}
		checkAllGamesFinished();
	}

	/**
	 * Creates Pairing String for Group 4
	 */
	private void setPairing4() {
		if (gamesList4Pointer < gamesList4.size()) {
			pairing4 = gamesList4.get(gamesList4Pointer).toString();
			labelTeamName4.setText(pairing4);
		}
		if (gamesList4Pointer > 0) {
			updateResult4();
		}
		checkGames4Finished();
		gamesList4Pointer++;
	}

	/**
	 * Sets the result to what is written in result41 and result42
	 */
	private void updateResult4() {
		int result1 = Integer.parseInt(result41.getText());
		int result2 = Integer.parseInt(result42.getText());
		result41.clear();
		result42.clear();
		gamesList4.get(gamesList4Pointer-1).setResult(result1, result2);
	}

	/**
	 * Checks whether all games of group 1 are played.
	 */
	private void checkGames4Finished() {
		if (gamesList4Pointer >= gamesList4.size()) {
			games4Finished = true;
			nextGameButton4.setDisable(true);
		}
		checkAllGamesFinished();
	}

	/**
	 * Creates the basic GUI
	 */
	private void createGUI() {
		// Format Labels
		int matchPrefWidth = 240;
		labelTeamName1.setText(pairing1);
		labelTeamName1.setPrefWidth(matchPrefWidth);
		labelTeamName2.setText(pairing2);
		labelTeamName2.setPrefWidth(matchPrefWidth);
		labelTeamName3.setText(pairing3);
		labelTeamName3.setPrefWidth(matchPrefWidth);
		labelTeamName4.setText(pairing4);
		labelTeamName4.setPrefWidth(matchPrefWidth);
		labelMatch.setPrefWidth(matchPrefWidth);
		labelResult.setPrefWidth(35);

		// Format Result Fields
		int resultPrefWidth = 25;
		result11.setMaxWidth(resultPrefWidth);
		result12.setMaxWidth(resultPrefWidth);
		result21.setMaxWidth(resultPrefWidth);
		result22.setMaxWidth(resultPrefWidth);
		result31.setMaxWidth(resultPrefWidth);
		result32.setMaxWidth(resultPrefWidth);
		result41.setMaxWidth(resultPrefWidth);
		result42.setMaxWidth(resultPrefWidth);
		result11.setMinWidth(resultPrefWidth);
		result12.setMinWidth(resultPrefWidth);
		result21.setMinWidth(resultPrefWidth);
		result22.setMinWidth(resultPrefWidth);
		result31.setMinWidth(resultPrefWidth);
		result32.setMinWidth(resultPrefWidth);
		result41.setMinWidth(resultPrefWidth);
		result42.setMinWidth(resultPrefWidth);
		result11.setPromptText(0 + "");
		result12.setPromptText(0 + "");
		result21.setPromptText(0 + "");
		result22.setPromptText(0 + "");
		result31.setPromptText(0 + "");
		result32.setPromptText(0 + "");
		result41.setPromptText(0 + "");
		result42.setPromptText(0 + "");

		// Format Buttons
		finishButton.setPrefWidth(320);
		finishButton.setDisable(true);
		cancelButton.setPrefWidth(100);
		nextGameButton1.setPrefWidth(100);
		nextGameButton2.setPrefWidth(100);
		nextGameButton3.setPrefWidth(100);
		nextGameButton4.setPrefWidth(100);

		root.add(labelMatch, 0, 0, 2, 1);
		root.add(labelTeamName1, 0, 1, 1, 1);
		root.add(labelTeamName2, 0, 2, 1, 1);
		root.add(labelTeamName3, 0, 3, 1, 1);
		root.add(labelTeamName4, 0, 4, 1, 1);
		root.add(finishButton, 0, 5, 4, 1);

		root.add(result11, 2, 1, 1, 1);
		root.add(result12, 3, 1, 1, 1);
		root.add(result21, 2, 2, 1, 1);
		root.add(result22, 3, 2, 1, 1);
		root.add(result31, 2, 3, 1, 1);
		root.add(result32, 3, 3, 1, 1);
		root.add(result41, 2, 4, 1, 1);
		root.add(result42, 3, 4, 1, 1);

		root.add(nextGameButton1, 4, 1, 1, 1);
		root.add(nextGameButton2, 4, 2, 1, 1);
		root.add(nextGameButton3, 4, 3, 1, 1);
		root.add(nextGameButton4, 4, 4, 1, 1);
		root.add(cancelButton, 4, 5, 1, 1);
	}

	/**
	 * Creates the list of games for each group.
	 */
	public void createGamesLists() {
		// Fill gamesList1
		gamesList1 = FXCollections.observableArrayList();
		int numberOfTeams1 = TourModel.getGroup1().size();
		for (int i = 0; i < numberOfTeams1 - 1; i++) {
			for (int j = i + 1; j < numberOfTeams1; j++) {
				Team team1 = TourModel.getGroup1().get(i);
				Team team2 = TourModel.getGroup1().get(j);
				PreliminaryGame game = new PreliminaryGame(team1, team2);
				gamesList1.add(game);
			}
		}

		// Fill gamesList2
		gamesList2 = FXCollections.observableArrayList();
		int numberOfTeams2 = TourModel.getGroup2().size();
		for (int i = 0; i < numberOfTeams2 - 1; i++) {
			for (int j = i + 1; j < numberOfTeams2; j++) {
				Team team1 = TourModel.getGroup2().get(i);
				Team team2 = TourModel.getGroup2().get(j);
				PreliminaryGame game = new PreliminaryGame(team1, team2);
				gamesList2.add(game);
			}
		}

		// Fill gamesList3
		gamesList3 = FXCollections.observableArrayList();
		int numberOfTeams3 = TourModel.getGroup3().size();
		for (int i = 0; i < numberOfTeams3 - 1; i++) {
			for (int j = i + 1; j < numberOfTeams3; j++) {
				Team team1 = TourModel.getGroup3().get(i);
				Team team2 = TourModel.getGroup3().get(j);
				PreliminaryGame game = new PreliminaryGame(team1, team2);
				gamesList3.add(game);
			}
		}

		// Fill gamesList4
		gamesList4 = FXCollections.observableArrayList();
		int numberOfTeams4 = TourModel.getGroup4().size();
		for (int i = 0; i < numberOfTeams4 - 1; i++) {
			for (int j = i + 1; j < numberOfTeams4; j++) {
				Team team1 = TourModel.getGroup4().get(i);
				Team team2 = TourModel.getGroup4().get(j);
				PreliminaryGame game = new PreliminaryGame(team1, team2);
				gamesList4.add(game);
			}
		}
	}

	public void stop() {
		stage.hide();
	}

	public boolean isAllGamesFinished() {
		return allGamesFinished;
	}
}
