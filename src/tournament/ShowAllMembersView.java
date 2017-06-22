package tournament;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowAllMembersView {
	
	Stage stage;
	Button cancelButton;
	TableView<Team> table;
	
	/**
	 * This method starts the windows to view all teams.
	 */
	public void start(){
		stage = new Stage();
		stage.setTitle("Teams");
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setSpacing(10);
		
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		double tableMaxHeight = resolution.getHeight();
				
		table = new TableView<>(TeamList.getTeamlist());
		createTable();
		table.setMaxHeight(tableMaxHeight);
		table.setPrefHeight(500);
		
		cancelButton = new Button("Cancel");
		cancelButton.setMaxWidth(Integer.MAX_VALUE);
		cancelButton.setOnAction(c->stop());
		
		vBox.getChildren().addAll(table, cancelButton);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Fills the table field with columns and values.
	 */
	private void createTable() {
		TableColumn<Team, String> teamNameCol = new TableColumn<Team, String>("Team Name");
		teamNameCol.setMinWidth(200);
		teamNameCol.setPrefWidth(200);
		teamNameCol.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
		table.getColumns().add(teamNameCol);
		TableColumn<Team, String> player1NameCol = new TableColumn<Team, String>("Player 1 Name");
		player1NameCol.setMinWidth(200);
		player1NameCol.setPrefWidth(200);
		player1NameCol.setCellValueFactory(c -> c.getValue().getPropertyPlayer1());
		table.getColumns().add(player1NameCol);
		TableColumn<Team, String> player2NameCol = new TableColumn<Team, String>("Player 2 Name");
		player2NameCol.setMinWidth(200);
		player2NameCol.setPrefWidth(200);
		player2NameCol.setCellValueFactory(c -> c.getValue().getPropertyPlayer2());
		table.getColumns().add(player2NameCol);
	}

	/**
	 * Stopping the view - just make it invisible.
	 */
	public void stop(){
		stage.hide();
	}
}
