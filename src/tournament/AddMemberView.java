package tournament;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Contains all properties and functions for a game in the preliminary phase.
 * 
 * @author joelf
 *
 */

public class AddMemberView {

	protected Stage stage;

	protected Button addButton;
	protected Button cancelButton;

	protected TextField textfieldTeamName;
	protected TextField textfieldPlayer1Name;
	protected TextField textfieldPlayer2Name;

	/**
	 * This method starts the window to add teams.
	 * 
	 * @param stage
	 *            Need Stage for window.
	 * @throws IOException
	 *             blabla.
	 */
	public void start() throws IOException {
		stage = new Stage();
		stage.setTitle("Add Member");

		VBox root = new VBox();
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();

		hBox1.setSpacing(10);
		hBox2.setSpacing(10);
		root.setPadding(new Insets(10));
		root.setSpacing(10);

		addButton = new Button("Add Team");
		cancelButton = new Button("Cancel");

		addButton.setOnAction(e -> addButtonClicked());
		addButton.setPrefWidth(100);
		cancelButton.setOnAction(e -> cancelButtonClicked());
		cancelButton.setPrefWidth(100);

		textfieldTeamName = new TextField();
		textfieldTeamName.setPromptText("Team Name");
		textfieldTeamName.setMaxWidth(210);
		textfieldPlayer1Name = new TextField();
		textfieldPlayer1Name.setPromptText("Player 1");
		textfieldPlayer1Name.setPrefWidth(100);
		textfieldPlayer2Name = new TextField();
		textfieldPlayer2Name.setPromptText("Player 2");
		textfieldPlayer2Name.setPrefWidth(100);

		hBox1.getChildren().addAll(textfieldPlayer1Name, textfieldPlayer2Name);
		hBox2.getChildren().addAll(addButton, cancelButton);
		root.getChildren().add(textfieldTeamName);
		root.getChildren().add(hBox1);
		root.getChildren().add(hBox2);
		Scene scene = new Scene(root);
		root.setStyle(
				"-fx-background-color: radial-gradient(focus-angle 45deg, focus-distance 50%, center 25% 25%, radius 60%, reflect, gray, darkgray 30%, black)");
		scene.getStylesheets().add(getClass().getResource("TourView.css").toExternalForm());

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Stopping the view - just make it invisible.
	 */
	public void stop() {
		stage.hide();
	}

	/**
	 * Closes the add members window.
	 */
	public void cancelButtonClicked() {
		this.stop();
	}

	/**
	 * Adds the Specified Team.
	 */
	public void addButtonClicked() {
		String t = textfieldTeamName.getText();
		String p1 = textfieldPlayer1Name.getText();
		String p2 = textfieldPlayer2Name.getText();
		TeamList.addTeam(t, p1, p2);
		textfieldPlayer1Name.clear();
		textfieldPlayer2Name.clear();
		textfieldTeamName.clear();
	}
}
