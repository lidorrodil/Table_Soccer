package tournament;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class tourView {
	private tourModel model;
	private Stage stage;
	
	protected Button showAllTeamsButton = new Button("Show all Teams");
	protected Button addTeamButton = new Button("Add Team");
	protected Button removeTeamButton = new Button("Delete Team");
	protected Button startTournamentButton = new Button("Start Tournament");
	protected Button finishTournamentButton = new Button("Stop Tournament");
	protected Button rulesButton = new Button("Rules");
	
	List<Button> buttonList = new ArrayList();


	public tourView(Stage stage, tourModel model) {
		this.stage = stage;
		this.model = model;

	}

	public void start() {
		Scene scene = new Scene(new Group());
		
		addButtonsToButtonList();

		// scene.getStylesheets().add(getClass().getResource("Design.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Tournament");

		// Make basic skeleton of view
		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		GridPane gridPane = new GridPane();
		vBox1.getChildren().addAll(hBox1, hBox2);
		hBox1.getChildren().addAll(vBox2, gridPane);
		
		hBox2.setPadding(new Insets(10));
		hBox2.setSpacing(10);
		hBox2.getChildren().add(showAllTeamsButton);
		hBox2.getChildren().add(addTeamButton);
		hBox2.getChildren().add(removeTeamButton);
		hBox2.getChildren().add(startTournamentButton);
		hBox2.getChildren().add(finishTournamentButton);
		hBox2.getChildren().add(rulesButton);

		gridPane.setHgap(50);
		gridPane.setVgap(50);

		gridPane.add(new Button("Button5"), 0, 0);
		gridPane.add(new Button("Button5"), 0, 3);
		gridPane.add(new Button("Button5"), 0, 7);
		gridPane.add(new Button("Button5"), 0, 9);
		gridPane.add(new Button("Button6"), 1, 1);
		gridPane.add(new Button("Button6"), 1, 8);
		gridPane.add(new Button("Button7"), 2, 5);		
		

		// Create tables for preliminaries
		TableView<Team> table1 = new TableView<Team>();
		TableView<Team> table2 = new TableView<Team>();
		TableView<Team> table3 = new TableView<Team>();
		TableView<Team> table4 = new TableView<Team>();

		table1.setFixedCellSize(25);
		table1.prefHeightProperty()
				.bind(table1.fixedCellSizeProperty().multiply(Bindings.size(table1.getItems()).add(5)));
		table1.minHeightProperty().bind(table1.prefHeightProperty());
		table1.maxHeightProperty().bind(table1.prefHeightProperty());
		
		table2.setFixedCellSize(25);
		table2.prefHeightProperty()
				.bind(table2.fixedCellSizeProperty().multiply(Bindings.size(table2.getItems()).add(7)));
		table2.minHeightProperty().bind(table2.prefHeightProperty());
		table2.maxHeightProperty().bind(table2.prefHeightProperty());
		
		table3.setFixedCellSize(25);
		table3.prefHeightProperty()
				.bind(table3.fixedCellSizeProperty().multiply(Bindings.size(table3.getItems()).add(7)));
		table3.minHeightProperty().bind(table3.prefHeightProperty());
		table3.maxHeightProperty().bind(table3.prefHeightProperty());
		
		table4.setFixedCellSize(25);
		table4.prefHeightProperty()
				.bind(table4.fixedCellSizeProperty().multiply(Bindings.size(table4.getItems()).add(7)));
		table4.minHeightProperty().bind(table4.prefHeightProperty());
		table4.maxHeightProperty().bind(table4.prefHeightProperty());

		// Some Testing
		// TODO: Delete this stuff before deploying
		TeamList liste1 = new TeamList();
		liste1.addTeam("gsdfgs", "player1", "player2");
		liste1.addTeam("sdfjjk", "player1", "player2");
		liste1.addTeam("tttttttt", "player1", "player2");
		PreliminaryGame g2 = new PreliminaryGame(liste1.getTeamlist().get(0), liste1.getTeamlist().get(1));
		g2.setResult(3, 3);
		table1.setItems(liste1.getTeamlist());

		TeamList liste2 = new TeamList();
		liste2.addTeam("team1", "player1", "player2");
		liste2.addTeam("team2", "player1", "player2");
		liste2.addTeam("team31", "player1", "player2");
		liste2.addTeam("team41", "player1", "player2");
		liste2.addTeam("team51", "player1", "player2");
		PreliminaryGame g = new PreliminaryGame(liste2.getTeamlist().get(0), liste2.getTeamlist().get(1));
		g.setResult(6, 2);
		table2.setItems(liste2.getTeamlist());

		
		
		// Create table headers
		createPreliminaryTables(table1, table2, table3, table4);

		// insert tables into (leftmost) vbox2
		vBox2.setSpacing(10);
		vBox2.setPadding(new Insets(10, 10, 10, 10)); // (new Insets(top, right,
														// bottom, left))
		vBox2.getChildren().addAll(table1, table2, table3, table4);
		
		

		// final VBox vbox = new VBox();
		// vbox.setSpacing(5);
		// vbox.setPadding(new Insets(10, 0, 0, 10));
		// vbox.getChildren().addAll(table1,table2);

		((Group) scene.getRoot()).getChildren().addAll(vBox1);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Addss buttons to buttonlist and formats them.
	 */
	private void addButtonsToButtonList() {
		buttonList.add(showAllTeamsButton);
		buttonList.add(addTeamButton);
		buttonList.add(removeTeamButton);
		buttonList.add(startTournamentButton);
		buttonList.add(finishTournamentButton);
		buttonList.add(rulesButton);
		buttonList.stream().forEach(c->c.setPrefWidth(150));
	}
	
	/**
	 * Creates the Table for Quartelfinals, Semifinals, Final.
	 * @param game 
	 * @return A table containing 2 teams only.
	 */
	public TableView<Team> createKOTable(KOGame game){
		TableView<Team> table = new TableView<>();
		TableColumn<Team, String> teamNameCol = new TableColumn<Team, String>("Team Name");
		teamNameCol.setMinWidth(200);
		teamNameCol.setPrefWidth(200);
		teamNameCol.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
		table.getColumns().add(teamNameCol);
		
		ObservableList<TableColumn<Team, Integer>> list = FXCollections.observableArrayList();
		for(int i = 0; i< game.getBestOfSets(); i++){
			int number = i+1;
			TableColumn<Team, Integer> setCol = new TableColumn<Team, Integer>(Integer.toString(number));
			setCol.setMaxWidth(50);
			setCol.setPrefWidth(50);
			setCol.setCellValueFactory(c -> c.getValue().getPropertyWins().asObject());
		}
		
		TableColumn<Team, Integer> setsPlayedCol = new TableColumn<Team, Integer>("Sets");
		setsPlayedCol.setMaxWidth(50);
		setsPlayedCol.setPrefWidth(50);
		setsPlayedCol.setCellValueFactory(c -> c.getValue().getPropertyGamesPlayed().asObject());
		table.getColumns().addAll(list);
		
		return table;
		
	}

	/**
	 * Creates all tables for the preliminary groups.
	 * @param table1 Must contain a list of Teams.
	 * @param table2 Must contain a list of Teams.
	 * @param table3 Must contain a list of Teams.
	 * @param table4 Must contain a list of Teams.
	 */
	public void createPreliminaryTables(TableView<Team> table1, TableView<Team> table2, TableView<Team> table3,
			TableView<Team> table4) {
		// TODO: Add rank to Teams
		// TODO: Refactor, that only 1 table is created to have less redundancy
		// TableColumn<Team, Integer> rankCol = new TableColumn<Team,
		// Integer>("Rank");
		// rankCol.setMaxWidth(50);
		// rankCol.setPrefWidth(50);
		// rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		TableColumn<Team, String> teamNameCol1 = new TableColumn<Team, String>("Team Name");
		teamNameCol1.setMinWidth(200);
		teamNameCol1.setPrefWidth(200);
		teamNameCol1.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
		table1.getColumns().add(teamNameCol1);
		TableColumn<Team, String> teamNameCol2 = new TableColumn<Team, String>("Team Name");
		teamNameCol2.setMinWidth(200);
		teamNameCol2.setPrefWidth(200);
		teamNameCol2.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
		table2.getColumns().add(teamNameCol2);
		TableColumn<Team, String> teamNameCol3 = new TableColumn<Team, String>("Team Name");
		teamNameCol3.setMinWidth(200);
		teamNameCol3.setPrefWidth(200);
		teamNameCol3.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
		table3.getColumns().add(teamNameCol3);
		TableColumn<Team, String> teamNameCol4 = new TableColumn<Team, String>("Team Name");
		teamNameCol4.setMinWidth(200);
		teamNameCol4.setPrefWidth(200);
		teamNameCol4.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
		table4.getColumns().add(teamNameCol4);

		TableColumn<Team, Integer> gamesPlayedCol1 = new TableColumn<Team, Integer>("Games");
		gamesPlayedCol1.setMaxWidth(50);
		gamesPlayedCol1.setPrefWidth(50);
		gamesPlayedCol1.setCellValueFactory(c -> c.getValue().getPropertyGamesPlayed().asObject());
		table1.getColumns().add(gamesPlayedCol1);
		TableColumn<Team, Integer> gamesPlayedCol2 = new TableColumn<Team, Integer>("Games");
		gamesPlayedCol2.setMaxWidth(50);
		gamesPlayedCol2.setPrefWidth(50);
		gamesPlayedCol2.setCellValueFactory(c -> c.getValue().getPropertyGamesPlayed().asObject());
		table2.getColumns().add(gamesPlayedCol2);
		TableColumn<Team, Integer> gamesPlayedCol3 = new TableColumn<Team, Integer>("Games");
		gamesPlayedCol3.setMaxWidth(50);
		gamesPlayedCol3.setPrefWidth(50);
		gamesPlayedCol3.setCellValueFactory(c -> c.getValue().getPropertyGamesPlayed().asObject());
		table3.getColumns().add(gamesPlayedCol3);
		TableColumn<Team, Integer> gamesPlayedCol4 = new TableColumn<Team, Integer>("Games");
		gamesPlayedCol4.setMaxWidth(50);
		gamesPlayedCol4.setPrefWidth(50);
		gamesPlayedCol4.setCellValueFactory(c -> c.getValue().getPropertyGamesPlayed().asObject());
		table4.getColumns().add(gamesPlayedCol4);

		TableColumn<Team, Integer> winCol1 = new TableColumn<Team, Integer>("W");
		winCol1.setMaxWidth(50);
		winCol1.setPrefWidth(50);
		winCol1.setCellValueFactory(c -> c.getValue().getPropertyWins().asObject());
		TableColumn<Team, Integer> winCol2 = new TableColumn<Team, Integer>("W");
		winCol2.setMaxWidth(50);
		winCol2.setPrefWidth(50);
		winCol2.setCellValueFactory(c -> c.getValue().getPropertyWins().asObject());
		TableColumn<Team, Integer> winCol3 = new TableColumn<Team, Integer>("W");
		winCol3.setMaxWidth(50);
		winCol3.setPrefWidth(50);
		winCol3.setCellValueFactory(c -> c.getValue().getPropertyWins().asObject());
		TableColumn<Team, Integer> winCol4 = new TableColumn<Team, Integer>("W");
		winCol4.setMaxWidth(50);
		winCol4.setPrefWidth(50);
		winCol4.setCellValueFactory(c -> c.getValue().getPropertyWins().asObject());

		TableColumn<Team, Integer> drawCol1 = new TableColumn<Team, Integer>("D");
		drawCol1.setMaxWidth(50);
		drawCol1.setPrefWidth(50);
		drawCol1.setCellValueFactory(c -> c.getValue().getPropertyDraws().asObject());
		TableColumn<Team, Integer> drawCol2 = new TableColumn<Team, Integer>("D");
		drawCol2.setMaxWidth(50);
		drawCol2.setPrefWidth(50);
		drawCol2.setCellValueFactory(c -> c.getValue().getPropertyDraws().asObject());
		TableColumn<Team, Integer> drawCol3 = new TableColumn<Team, Integer>("D");
		drawCol3.setMaxWidth(50);
		drawCol3.setPrefWidth(50);
		drawCol3.setCellValueFactory(c -> c.getValue().getPropertyDraws().asObject());
		TableColumn<Team, Integer> drawCol4 = new TableColumn<Team, Integer>("D");
		drawCol4.setMaxWidth(50);
		drawCol4.setPrefWidth(50);
		drawCol4.setCellValueFactory(c -> c.getValue().getPropertyDraws().asObject());

		TableColumn<Team, Integer> lostCol1 = new TableColumn<Team, Integer>("L");
		lostCol1.setMaxWidth(50);
		lostCol1.setPrefWidth(50);
		lostCol1.setCellValueFactory(c -> c.getValue().getPropertyLosts().asObject());
		TableColumn<Team, Integer> lostCol2 = new TableColumn<Team, Integer>("L");
		lostCol2.setMaxWidth(50);
		lostCol2.setPrefWidth(50);
		lostCol2.setCellValueFactory(c -> c.getValue().getPropertyLosts().asObject());
		TableColumn<Team, Integer> lostCol3 = new TableColumn<Team, Integer>("L");
		lostCol3.setMaxWidth(50);
		lostCol3.setPrefWidth(50);
		lostCol3.setCellValueFactory(c -> c.getValue().getPropertyLosts().asObject());
		TableColumn<Team, Integer> lostCol4 = new TableColumn<Team, Integer>("L");
		lostCol4.setMaxWidth(50);
		lostCol4.setPrefWidth(50);
		lostCol4.setCellValueFactory(c -> c.getValue().getPropertyLosts().asObject());

		TableColumn<Team, Integer> gamesCol1 = new TableColumn<Team, Integer>("Games");
		gamesCol1.getColumns().add(winCol1);
		gamesCol1.getColumns().add(drawCol1);
		gamesCol1.getColumns().add(lostCol1);
		table1.getColumns().add(gamesCol1);
		TableColumn<Team, Integer> gamesCol2 = new TableColumn<Team, Integer>("Games");
		gamesCol2.getColumns().add(winCol2);
		gamesCol2.getColumns().add(drawCol2);
		gamesCol2.getColumns().add(lostCol2);
		table2.getColumns().add(gamesCol2);
		TableColumn<Team, Integer> gamesCol3 = new TableColumn<Team, Integer>("Games");
		gamesCol3.getColumns().add(winCol3);
		gamesCol3.getColumns().add(drawCol3);
		gamesCol3.getColumns().add(lostCol3);
		table3.getColumns().add(gamesCol3);
		TableColumn<Team, Integer> gamesCol4 = new TableColumn<Team, Integer>("Games");
		gamesCol4.getColumns().add(winCol4);
		gamesCol4.getColumns().add(drawCol4);
		gamesCol4.getColumns().add(lostCol4);
		table4.getColumns().add(gamesCol4);

		TableColumn<Team, Integer> goalScoredCol1 = new TableColumn<Team, Integer>("Scored");
		goalScoredCol1.setMaxWidth(50);
		goalScoredCol1.setPrefWidth(50);
		goalScoredCol1.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());
		TableColumn<Team, Integer> goalScoredCol2 = new TableColumn<Team, Integer>("Scored");
		goalScoredCol2.setMaxWidth(50);
		goalScoredCol2.setPrefWidth(50);
		goalScoredCol2.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());
		TableColumn<Team, Integer> goalScoredCol3 = new TableColumn<Team, Integer>("Scored");
		goalScoredCol3.setMaxWidth(50);
		goalScoredCol3.setPrefWidth(50);
		goalScoredCol3.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());
		TableColumn<Team, Integer> goalScoredCol4 = new TableColumn<Team, Integer>("Scored");
		goalScoredCol4.setMaxWidth(50);
		goalScoredCol4.setPrefWidth(50);
		goalScoredCol4.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());

		TableColumn<Team, Integer> goalConcededCol1 = new TableColumn<Team, Integer>("Conc");
		goalConcededCol1.setMaxWidth(50);
		goalConcededCol1.setPrefWidth(50);
		goalConcededCol1.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());
		TableColumn<Team, Integer> goalConcededCol2 = new TableColumn<Team, Integer>("Conc");
		goalConcededCol2.setMaxWidth(50);
		goalConcededCol2.setPrefWidth(50);
		goalConcededCol2.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());
		TableColumn<Team, Integer> goalConcededCol3 = new TableColumn<Team, Integer>("Conc");
		goalConcededCol3.setMaxWidth(50);
		goalConcededCol3.setPrefWidth(50);
		goalConcededCol3.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());
		TableColumn<Team, Integer> goalConcededCol4 = new TableColumn<Team, Integer>("Conc");
		goalConcededCol4.setMaxWidth(50);
		goalConcededCol4.setPrefWidth(50);
		goalConcededCol4.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());

		TableColumn<Team, Integer> goalDifferenceCol1 = new TableColumn<Team, Integer>("Diff");
		goalDifferenceCol1.setMaxWidth(50);
		goalDifferenceCol1.setPrefWidth(50);
		goalDifferenceCol1.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());
		TableColumn<Team, Integer> goalDifferenceCol2 = new TableColumn<Team, Integer>("Diff");
		goalDifferenceCol2.setMaxWidth(50);
		goalDifferenceCol2.setPrefWidth(50);
		goalDifferenceCol2.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());
		TableColumn<Team, Integer> goalDifferenceCol3 = new TableColumn<Team, Integer>("Diff");
		goalDifferenceCol3.setMaxWidth(50);
		goalDifferenceCol3.setPrefWidth(50);
		goalDifferenceCol3.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());
		TableColumn<Team, Integer> goalDifferenceCol4 = new TableColumn<Team, Integer>("Diff");
		goalDifferenceCol4.setMaxWidth(50);
		goalDifferenceCol4.setPrefWidth(50);
		goalDifferenceCol4.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());

		TableColumn<Team, Integer> goalsCol1 = new TableColumn<Team, Integer>("Goals");
		goalsCol1.getColumns().add(goalScoredCol4);
		goalsCol1.getColumns().add(goalConcededCol4);
		goalsCol1.getColumns().add(goalDifferenceCol4);
		table1.getColumns().add(goalsCol1);
		TableColumn<Team, Integer> goalsCol2 = new TableColumn<Team, Integer>("Goals");
		goalsCol2.getColumns().add(goalScoredCol4);
		goalsCol2.getColumns().add(goalConcededCol4);
		goalsCol2.getColumns().add(goalDifferenceCol4);
		table2.getColumns().add(goalsCol2);
		TableColumn<Team, Integer> goalsCol3 = new TableColumn<Team, Integer>("Goals");
		goalsCol3.getColumns().add(goalScoredCol4);
		goalsCol3.getColumns().add(goalConcededCol4);
		goalsCol3.getColumns().add(goalDifferenceCol4);
		table3.getColumns().add(goalsCol3);
		TableColumn<Team, Integer> goalsCol4 = new TableColumn<Team, Integer>("Goals");
		goalsCol4.getColumns().add(goalScoredCol4);
		goalsCol4.getColumns().add(goalConcededCol4);
		goalsCol4.getColumns().add(goalDifferenceCol4);
		table4.getColumns().add(goalsCol4);

		TableColumn<Team, Integer> totPointsCol1 = new TableColumn<Team, Integer>("Point(s)");
		totPointsCol1.setMaxWidth(50);
		totPointsCol1.setPrefWidth(50);
		totPointsCol1.setCellValueFactory(c -> c.getValue().getPropertyPoints().asObject());
		table1.getColumns().add(totPointsCol1);
		TableColumn<Team, Integer> totPointsCol2 = new TableColumn<Team, Integer>("Point(s)");
		totPointsCol2.setMaxWidth(50);
		totPointsCol2.setPrefWidth(50);
		totPointsCol2.setCellValueFactory(c -> c.getValue().getPropertyPoints().asObject());
		table2.getColumns().add(totPointsCol2);
		TableColumn<Team, Integer> totPointsCol3 = new TableColumn<Team, Integer>("Point(s)");
		totPointsCol3.setMaxWidth(50);
		totPointsCol3.setPrefWidth(50);
		totPointsCol3.setCellValueFactory(c -> c.getValue().getPropertyPoints().asObject());
		table3.getColumns().add(totPointsCol3);
		TableColumn<Team, Integer> totPointsCol4 = new TableColumn<Team, Integer>("Point(s)");
		totPointsCol4.setMaxWidth(50);
		totPointsCol4.setPrefWidth(50);
		totPointsCol4.setCellValueFactory(c -> c.getValue().getPropertyPoints().asObject());
		table4.getColumns().add(totPointsCol4);
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
