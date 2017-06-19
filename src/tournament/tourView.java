package tournament;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class tourView {
	private tourModel model;
	private Stage stage;

	PreliminaryTable table;

	public tourView(Stage stage, tourModel model) {
		this.stage = stage;
		this.model = model;

	}

	public void start() {
		Scene scene = new Scene(new Group());

		// scene.getStylesheets().add(getClass().getResource("Design.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Tournament");

		VBox vBox1 = new VBox();
		GridPane gridPane = new GridPane();
		

		// Create tables for preliminaries
		TableView<Team> table1 = new TableView<Team>();
		TableView<Team> table2 = new TableView<Team>();
		TableView<Team> table3 = new TableView<Team>();
		TableView<Team> table4 = new TableView<Team>();

		// Create table headers
		TableColumn<Team, Integer> rankCol = new TableColumn<Team, Integer>("Rank");
		TableColumn<Team, String> teamNameCol = new TableColumn<Team, String>("Team Name");
		TableColumn<Team, Integer> gamesCol = new TableColumn<Team, Integer>("Games");
		TableColumn<Team, Integer> winCol = new TableColumn<Team, Integer>("W");
		TableColumn<Team, Integer> tieCol = new TableColumn<Team, Integer>("T");
		TableColumn<Team, Integer> lossCol = new TableColumn<Team, Integer>("L");
		TableColumn<Team, Integer> goalsCol = new TableColumn<Team, Integer>("Goals");
		TableColumn<Team, Integer> goalScoredCol = new TableColumn<Team, Integer>("Scored");
		TableColumn<Team, Integer> goalConcededCol = new TableColumn<Team, Integer>("Conceded");
		TableColumn<Team, Integer> totPointsCol = new TableColumn<Team, Integer>("Point(s)");
	
		// format table headers
		gamesCol.getColumns().addAll(winCol, tieCol, lossCol);
		goalsCol.getColumns().addAll(goalScoredCol,goalConcededCol);
		
		// insert table headers into tables
		table1.getColumns().addAll(rankCol,teamNameCol,gamesCol,goalsCol,totPointsCol);	
		table2.getColumns().addAll(rankCol,teamNameCol,gamesCol,goalsCol,totPointsCol);	
		table3.getColumns().addAll(rankCol,teamNameCol,gamesCol,goalsCol,totPointsCol);	
		table4.getColumns().addAll(rankCol,teamNameCol,gamesCol,goalsCol,totPointsCol);	

		
		
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(table1,table2);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Stopping the view - just make it invisible
	 */
	public void stop() {
		stage.hide();
	}

	/**
	 * Getter for the stage, so that the controller can access window events
	 */
	public Stage getStage() {
		return stage;
	}
}
