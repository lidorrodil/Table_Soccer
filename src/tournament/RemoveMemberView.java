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

public class RemoveMemberView {
	
	Stage stage;
	
	Button removeButton;
	Button cancelButton;

	ComboBox<Team> dropdown;

	/**
	 * Starts the remove members window.
	 * @throws IOException
	 */
	public void start() throws IOException {
		stage = new Stage();
		stage.setTitle("Remove Team");
		VBox vBox = new VBox();
		HBox hBox1 = new HBox();
		
		dropdown = new ComboBox<>(TeamList.getTeamlist());
		dropdown.setPromptText("Select a Team");
		dropdown.setMaxWidth(210);
		
		hBox1.setSpacing(10);
		vBox.setPadding(new Insets(10));
		vBox.setSpacing(10);
		

		removeButton = new Button("Remove Team");
		cancelButton = new Button("Cancel");

		removeButton.setOnAction(e -> removeButtonClicked());
		removeButton.setPrefWidth(100);
		cancelButton.setOnAction(e -> cancelButtonClicked());
		cancelButton.setPrefWidth(100);
		
		hBox1.getChildren().addAll(removeButton,cancelButton);
		vBox.getChildren().addAll(dropdown, hBox1);
		Scene scene = new Scene(vBox);
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
	public void removeButtonClicked() {
		Team t = (Team) dropdown.getSelectionModel().getSelectedItem();
		System.out.println("test before remove team");
		TeamList.removeTeam(t.toString());
		System.out.println("test after remove team");
		dropdown.setValue(null);
	}
	
	
}
