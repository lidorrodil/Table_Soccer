package tournament;

import java.io.IOError;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles everything concerning removing a member from the tournament.
 * @author joelf
 *
 */

public class RemoveMemberView {
	
	Stage stage;
	
	Button removeButton;
	Button cancelButton;

	ComboBox<Team> dropdown;

	/**
	 * Starts the remove members window.
	 * @throws IOException
	 */
	public void start(TourView view) throws IOException {
		stage = new Stage();
		stage.setTitle("Remove Team");
		VBox root = new VBox();
		HBox hBox1 = new HBox();
		
		dropdown = new ComboBox<>(TeamList.getTeamlist());
		dropdown.setPromptText("Select a Team");
		dropdown.setMaxWidth(210);
		
		hBox1.setSpacing(10);
		root.setPadding(new Insets(10));
		root.setSpacing(10);
		

		removeButton = new Button("Remove Team");
		cancelButton = new Button("Cancel");

		removeButton.setOnAction(e -> removeButtonClicked(view));
		removeButton.setPrefWidth(100);
		cancelButton.setOnAction(e -> cancelButtonClicked());
		cancelButton.setPrefWidth(100);
		
		hBox1.getChildren().addAll(removeButton,cancelButton);
		root.getChildren().addAll(dropdown, hBox1);
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
	public void cancelButtonClicked(){
		this.stop();
	}
	
	/**
	 * Removes chosen team.
	 */
	public void removeButtonClicked(TourView view) {
		Team t = (Team) dropdown.getSelectionModel().getSelectedItem();
		TeamList.removeTeam(t.toString());
		dropdown.setValue(null);
		if(TeamList.getTeamlist().size() < 28){
			view.addTeamButton.setDisable(false);
		}
		if(TeamList.getTeamlist().size() <8){
			view.startTournamentButton.setDisable(true);
		}
	}
	
	
}
