package tournament;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Opens a small window announcing the winner of the tournament.
 * @author joelf
 *
 */

public class WinnerView {
	protected Stage stage;
	protected Button cancelButton = new Button("Close");
	protected Label message1;
	protected Label message2;
	protected VBox root;

	public void start(Team winner) {
		stage = new Stage();
		stage.setTitle("Winner!!");

		root = new VBox();

		root.setPadding(new Insets(10));
		root.setSpacing(10);

		String teamname = winner.getTeamName();
		String player1 = winner.getPlayer1();
		String player2 = winner.getPlayer2();

		message1 = new Label("The Winner is " + teamname);
		message2 = new Label("Congratulations to " + player1 + " and " + player2 + ".");

		message1.setPrefWidth(300);
		message1.setMaxWidth(300);
		message2.setPrefWidth(300);
		message2.setMaxWidth(300);
		cancelButton.setPrefWidth(300);
		cancelButton.setMaxWidth(300);
		cancelButton.setOnAction(c->stop());

		root.getChildren().add(message1);
		root.getChildren().add(message2);
		root.getChildren().add(cancelButton);
		Scene scene = new Scene(root);
		root.setStyle(
				"-fx-background-color: radial-gradient(focus-angle 45deg, focus-distance 50%, center 25% 25%, radius 60%, reflect, gray, darkgray 30%, black)");
		scene.getStylesheets().add(getClass().getResource("TourView.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public void stop() {
		stage.hide();
	}
}
