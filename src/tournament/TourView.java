package tournament;

import java.awt.Font;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.nashorn.internal.ir.LiteralNode.PrimitiveLiteralNode;

/**
 * Displays the basic window of the tournament.
 * 
 * @author joelf
 *
 */

public class TourView {
	private TourModel model;
	private Stage stage;

	// Basic UI Elements
	protected VBox root;
	protected VBox vBox2;
	protected HBox hBox1;
	protected HBox hBox2;
	protected HBox hBox3;

	// UI Elements for KO Phase Part
	protected GridPane gridPane;
	protected BorderPane borderPane1 = new BorderPane();
	protected BorderPane borderPane2 = new BorderPane();
	protected BorderPane borderPane3 = new BorderPane();
	protected BorderPane borderPane4 = new BorderPane();
	protected BorderPane borderPane5 = new BorderPane();
	protected BorderPane borderPane6 = new BorderPane();
	protected BorderPane borderPane7 = new BorderPane();
	protected Label KOLabel1 = new Label("Quarter Final 1");
	protected Label KOLabel2 = new Label("Quarter Final 2");
	protected Label KOLabel3 = new Label("Quarter Final 3");
	protected Label KOLabel4 = new Label("Quarter Final 4");
	protected Label KOLabel5 = new Label("Semi Final 1");
	protected Label KOLabel6 = new Label("Semi Final 2");
	protected Label KOLabel7 = new Label("Final");
	protected Text labelQFPlaceholder1 = new Text("The Quarter Finals are not set yet");
	protected Text labelQFPlaceholder2 = new Text("The Quarter Finals are not set yet");
	protected Text labelQFPlaceholder3 = new Text("The Quarter Finals are not set yet");
	protected Text labelQFPlaceholder4 = new Text("The Quarter Finals are not set yet");
	protected Text labelSFPlaceholder1 = new Text("The Semi Finals are not set yet");
	protected Text labelSFPlaceholder2 = new Text("The Semi Finals are not set yet");
	protected Text labelFPlaceholder = new Text("The Final is not set yet");
	protected Button QFButton1 = new Button("Add Result");
	protected Button QFButton2 = new Button("Add Result");
	protected Button QFButton3 = new Button("Add Result");
	protected Button QFButton4 = new Button("Add Result");
	protected Button SFButton1 = new Button("Add Result");
	protected Button SFButton2 = new Button("Add Result");
	protected Button finalsButton = new Button("Add Result");
	List<TableView<Team>> tableKOList = new ArrayList();

	// UI Elements for Lower Part
	protected Button showAllTeamsButton = new Button("Show all Teams");
	protected Button addTeamButton = new Button("Add Team");
	protected Button removeTeamButton = new Button("Delete Team");
	protected Button startTournamentButton = new Button("Start Tournament");
	protected Button rulesButton = new Button("Rules");
	protected Button preliminaryGameButton = new Button("View Games");
	protected Button startQFButton = new Button("Start Quarter Finals");
	protected Button startSFButton = new Button("Start Semi Finals");
	protected Button startFButton = new Button("Start Final");
	protected Button endFinalButton = new Button("Show Winner Team");

	// UI Elements for Preliminary Phase
	protected TableView<Team> table1;
	protected TableView<Team> table2;
	protected TableView<Team> table3;
	protected TableView<Team> table4;
	protected Label prelHeader = new Label("Preliminary Rounds");
	protected Text labelPrelPlaceholder1 = new Text("The Preliminary groups are not set yet");
	protected Text labelPrelPlaceholder2 = new Text("The Preliminary groups are not set yet");
	protected Text labelPrelPlaceholder3 = new Text("The Preliminary groups are not set yet");
	protected Text labelPrelPlaceholder4 = new Text("The Preliminary groups are not set yet");
	List<Button> buttonList = new ArrayList();

	public TourView(Stage stage, TourModel model) {
		this.stage = stage;
		this.model = model;

	}

	public void start() {
		Scene scene = new Scene(new Group());
		stage.setMaximized(true);

		addButtonsToButtonList();
		fillTableKOList();

		stage.setScene(scene);
		stage.setTitle("Tournament");

		// Make basic skeleton of view
		root = new VBox();
		vBox2 = new VBox();
		hBox1 = new HBox();
		hBox2 = new HBox();
		hBox3 = new HBox();
		gridPane = new GridPane();
		root.getChildren().addAll(hBox1, hBox2);
		hBox1.getChildren().addAll(vBox2, gridPane);

		hBox2.setPadding(new Insets(0, 3, 3, 10));
		hBox2.setSpacing(10);
		hBox2.getChildren().add(showAllTeamsButton);
		hBox2.getChildren().add(addTeamButton);
		hBox2.getChildren().add(removeTeamButton);
		hBox2.getChildren().add(startTournamentButton);
		hBox2.getChildren().add(rulesButton);

		gridPane.setPadding(new Insets(3));

		createKOView();

		createPreliminaryView();

		// Create table headers
		createPreliminaryTables(table1, table2, table3, table4);

		// insert tables into (leftmost) vbox2
		vBox2.setSpacing(10);
		vBox2.setPadding(new Insets(10, 10, 10, 10)); // (new Insets(top, right,
														// bottom, left))
		vBox2.getChildren().addAll(table1, table2, table3, table4);

		((Group) scene.getRoot()).getChildren().addAll(root);
		scene.getStylesheets().add(getClass().getResource("TourView.css").toExternalForm());
		root.setStyle(
				"-fx-background-color: radial-gradient(focus-angle 45deg, focus-distance 50%, center 25% 25%, radius 60%, reflect, gray, darkgray 30%, black)");
		root.setPrefSize(1920, 1080);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Creates the left part of the TourView
	 */
	private void createPreliminaryView() {
		// Create tables for preliminaries
		// prelHeader.setPadding(new Insets(5));
		vBox2.getChildren().add(prelHeader);
		table1 = new TableView<Team>();
		table2 = new TableView<Team>();
		table3 = new TableView<Team>();
		table4 = new TableView<Team>();

		table1.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
		table2.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
		table3.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
		table4.getStylesheets().add(getClass().getResource("table.css").toExternalForm());

		table1.setFixedCellSize(25);
		table1.prefHeightProperty()
				.bind(table1.fixedCellSizeProperty().multiply(Bindings.size(table1.getItems()).add(9)));
		table1.minHeightProperty().bind(table1.prefHeightProperty());
		table1.maxHeightProperty().bind(table1.prefHeightProperty());
		table1.setPlaceholder(labelPrelPlaceholder1);

		table2.setFixedCellSize(25);
		table2.prefHeightProperty()
				.bind(table2.fixedCellSizeProperty().multiply(Bindings.size(table2.getItems()).add(9)));
		table2.minHeightProperty().bind(table2.prefHeightProperty());
		table2.maxHeightProperty().bind(table2.prefHeightProperty());
		table2.setPlaceholder(labelPrelPlaceholder2);

		table3.setFixedCellSize(25);
		table3.prefHeightProperty()
				.bind(table3.fixedCellSizeProperty().multiply(Bindings.size(table3.getItems()).add(9)));
		table3.minHeightProperty().bind(table3.prefHeightProperty());
		table3.maxHeightProperty().bind(table3.prefHeightProperty());
		table3.setPlaceholder(labelPrelPlaceholder3);

		table4.setFixedCellSize(25);
		table4.prefHeightProperty()
				.bind(table4.fixedCellSizeProperty().multiply(Bindings.size(table4.getItems()).add(9)));
		table4.minHeightProperty().bind(table4.prefHeightProperty());
		table4.maxHeightProperty().bind(table4.prefHeightProperty());
		table4.setPlaceholder(labelPrelPlaceholder4);
	}

	/**
	 * Creates the right part of the TourView
	 */
	private void createKOView() {
		// KO Phase
		tableKOList.forEach(c -> c.setMaxSize(310, 115));
		tableKOList.forEach(c -> c.setFixedCellSize(20));
		tableKOList.forEach(c -> c.getStylesheets().add(getClass().getResource("table.css").toExternalForm()));

		QFButton1.setPrefWidth(310);
		QFButton2.setPrefWidth(310);
		QFButton3.setPrefWidth(310);
		QFButton4.setPrefWidth(310);
		SFButton1.setPrefWidth(310);
		SFButton2.setPrefWidth(310);
		finalsButton.setPrefWidth(310);

		borderPane1.setMargin(tableKOList.get(1), new Insets(5));
		borderPane2.setMargin(tableKOList.get(2), new Insets(5));
		borderPane3.setMargin(tableKOList.get(3), new Insets(5));
		borderPane4.setMargin(tableKOList.get(4), new Insets(5));
		borderPane5.setMargin(tableKOList.get(5), new Insets(5));
		borderPane6.setMargin(tableKOList.get(6), new Insets(5));
		borderPane7.setMargin(tableKOList.get(7), new Insets(5));
		borderPane1.setMargin(QFButton1, new Insets(5));
		borderPane2.setMargin(QFButton2, new Insets(5));
		borderPane3.setMargin(QFButton3, new Insets(5));
		borderPane4.setMargin(QFButton4, new Insets(5));
		borderPane5.setMargin(SFButton1, new Insets(5));
		borderPane6.setMargin(SFButton2, new Insets(5));
		borderPane7.setMargin(finalsButton, new Insets(5));
		borderPane1.setMargin(KOLabel1, new Insets(5));
		borderPane2.setMargin(KOLabel2, new Insets(5));
		borderPane3.setMargin(KOLabel3, new Insets(5));
		borderPane4.setMargin(KOLabel4, new Insets(5));
		borderPane5.setMargin(KOLabel5, new Insets(5));
		borderPane6.setMargin(KOLabel6, new Insets(5));
		borderPane7.setMargin(KOLabel7, new Insets(5));
		borderPane1.setPadding(new Insets(5));
		borderPane2.setPadding(new Insets(5));
		borderPane3.setPadding(new Insets(5));
		borderPane4.setPadding(new Insets(5));
		borderPane5.setPadding(new Insets(5));
		borderPane6.setPadding(new Insets(5));
		borderPane7.setPadding(new Insets(5));
		borderPane1.setTop(KOLabel1);
		borderPane2.setTop(KOLabel2);
		borderPane3.setTop(KOLabel3);
		borderPane4.setTop(KOLabel4);
		borderPane5.setTop(KOLabel5);
		borderPane6.setTop(KOLabel6);
		borderPane7.setTop(KOLabel7);
		borderPane1.setCenter(tableKOList.get(0));
		borderPane2.setCenter(tableKOList.get(1));
		borderPane3.setCenter(tableKOList.get(2));
		borderPane4.setCenter(tableKOList.get(3));
		borderPane5.setCenter(tableKOList.get(4));
		borderPane6.setCenter(tableKOList.get(5));
		borderPane7.setCenter(tableKOList.get(6));
		borderPane1.setBottom(QFButton1);
		borderPane2.setBottom(QFButton2);
		borderPane3.setBottom(QFButton3);
		borderPane4.setBottom(QFButton4);
		borderPane5.setBottom(SFButton1);
		borderPane6.setBottom(SFButton2);
		borderPane7.setBottom(finalsButton);
		QFButton1.setDisable(true);
		QFButton2.setDisable(true);
		QFButton3.setDisable(true);
		QFButton4.setDisable(true);
		SFButton1.setDisable(true);
		SFButton2.setDisable(true);
		finalsButton.setDisable(true);

		gridPane.add(borderPane1, 0, 0);
		gridPane.add(borderPane2, 0, 1);
		gridPane.add(borderPane3, 0, 3);
		gridPane.add(borderPane4, 0, 4);
		gridPane.add(borderPane5, 1, 1);
		gridPane.add(borderPane6, 1, 3);
		gridPane.add(borderPane7, 2, 2);
	}

	/**
	 * Inserts 7 tables into the tableList for KO Games
	 */
	private void fillTableKOList() {
		int setWidth = 20;
		for (int i = 0; i < 8; i++) {
			tableKOList.add(new TableView<Team>());
			TableColumn<Team, String> teamNameCol1 = new TableColumn<Team, String>("Team Name");
			teamNameCol1.setMinWidth(200);
			teamNameCol1.setPrefWidth(200);
			teamNameCol1.setCellValueFactory(c -> c.getValue().getPropertyTeamName());
			tableKOList.get(i).getColumns().add(teamNameCol1);
			TableColumn<Team, Integer> setCol1 = new TableColumn<Team, Integer>("1");
			setCol1.setMaxWidth(setWidth);
			setCol1.setPrefWidth(setWidth);
			setCol1.setCellValueFactory(c -> c.getValue().getPropertySet1().asObject());
			TableColumn<Team, Integer> setCol2 = new TableColumn<Team, Integer>("2");
			setCol2.setMaxWidth(setWidth);
			setCol2.setPrefWidth(setWidth);
			setCol2.setCellValueFactory(c -> c.getValue().getPropertySet2().asObject());
			TableColumn<Team, Integer> setCol3 = new TableColumn<Team, Integer>("3");
			setCol3.setMaxWidth(setWidth);
			setCol3.setPrefWidth(setWidth);
			setCol3.setCellValueFactory(c -> c.getValue().getPropertySet3().asObject());
			TableColumn<Team, Integer> setCol4 = new TableColumn<Team, Integer>("4");
			setCol4.setMaxWidth(setWidth);
			setCol4.setPrefWidth(setWidth);
			setCol4.setCellValueFactory(c -> c.getValue().getPropertySet4().asObject());
			TableColumn<Team, Integer> setCol5 = new TableColumn<Team, Integer>("5");
			setCol5.setMaxWidth(setWidth);
			setCol5.setPrefWidth(setWidth);
			setCol5.setCellValueFactory(c -> c.getValue().getPropertySet5().asObject());
			TableColumn<Team, Integer> sets = new TableColumn<Team, Integer>("Sets");
			sets.getColumns().add(setCol1);
			sets.getColumns().add(setCol2);
			sets.getColumns().add(setCol3);
			sets.getColumns().add(setCol4);
			sets.getColumns().add(setCol5);
			tableKOList.get(i).getColumns().add(sets);
		}
		tableKOList.get(0).setPlaceholder(labelQFPlaceholder1);
		tableKOList.get(1).setPlaceholder(labelQFPlaceholder2);
		tableKOList.get(2).setPlaceholder(labelQFPlaceholder3);
		tableKOList.get(3).setPlaceholder(labelQFPlaceholder4);
		tableKOList.get(4).setPlaceholder(labelSFPlaceholder1);
		tableKOList.get(5).setPlaceholder(labelSFPlaceholder2);
		tableKOList.get(6).setPlaceholder(labelFPlaceholder);

	}

	/**
	 * Adds buttons to buttonlist and formats them.
	 */
	private void addButtonsToButtonList() {
		buttonList.add(showAllTeamsButton);
		buttonList.add(addTeamButton);
		buttonList.add(removeTeamButton);
		buttonList.add(startTournamentButton);
		buttonList.add(rulesButton);
		buttonList.add(preliminaryGameButton);
		buttonList.add(startQFButton);
		buttonList.add(startSFButton);
		buttonList.add(startFButton);
		buttonList.add(endFinalButton);
		buttonList.stream().forEach(c -> c.setPrefWidth(150));
	}

	/**
	 * Creates all tables for the preliminary groups.
	 * 
	 * @param table1
	 *            Must contain a list of Teams.
	 * @param table2
	 *            Must contain a list of Teams.
	 * @param table3
	 *            Must contain a list of Teams.
	 * @param table4
	 *            Must contain a list of Teams.
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

		TableColumn<Team, Integer> goalScoredCol1 = new TableColumn<Team, Integer>("F");
		goalScoredCol1.setMaxWidth(50);
		goalScoredCol1.setPrefWidth(50);
		goalScoredCol1.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());
		TableColumn<Team, Integer> goalScoredCol2 = new TableColumn<Team, Integer>("F");
		goalScoredCol2.setMaxWidth(50);
		goalScoredCol2.setPrefWidth(50);
		goalScoredCol2.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());
		TableColumn<Team, Integer> goalScoredCol3 = new TableColumn<Team, Integer>("F");
		goalScoredCol3.setMaxWidth(50);
		goalScoredCol3.setPrefWidth(50);
		goalScoredCol3.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());
		TableColumn<Team, Integer> goalScoredCol4 = new TableColumn<Team, Integer>("F");
		goalScoredCol4.setMaxWidth(50);
		goalScoredCol4.setPrefWidth(50);
		goalScoredCol4.setCellValueFactory(c -> c.getValue().getPropertyGoalsScored().asObject());

		TableColumn<Team, Integer> goalConcededCol1 = new TableColumn<Team, Integer>("A");
		goalConcededCol1.setMaxWidth(50);
		goalConcededCol1.setPrefWidth(50);
		goalConcededCol1.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());
		TableColumn<Team, Integer> goalConcededCol2 = new TableColumn<Team, Integer>("A");
		goalConcededCol2.setMaxWidth(50);
		goalConcededCol2.setPrefWidth(50);
		goalConcededCol2.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());
		TableColumn<Team, Integer> goalConcededCol3 = new TableColumn<Team, Integer>("A");
		goalConcededCol3.setMaxWidth(50);
		goalConcededCol3.setPrefWidth(50);
		goalConcededCol3.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());
		TableColumn<Team, Integer> goalConcededCol4 = new TableColumn<Team, Integer>("A");
		goalConcededCol4.setMaxWidth(50);
		goalConcededCol4.setPrefWidth(50);
		goalConcededCol4.setCellValueFactory(c -> c.getValue().getPropertyGoalsConceded().asObject());

		TableColumn<Team, Integer> goalDifferenceCol1 = new TableColumn<Team, Integer>("GD");
		goalDifferenceCol1.setMaxWidth(50);
		goalDifferenceCol1.setPrefWidth(50);
		goalDifferenceCol1.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());
		TableColumn<Team, Integer> goalDifferenceCol2 = new TableColumn<Team, Integer>("GD");
		goalDifferenceCol2.setMaxWidth(50);
		goalDifferenceCol2.setPrefWidth(50);
		goalDifferenceCol2.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());
		TableColumn<Team, Integer> goalDifferenceCol3 = new TableColumn<Team, Integer>("GD");
		goalDifferenceCol3.setMaxWidth(50);
		goalDifferenceCol3.setPrefWidth(50);
		goalDifferenceCol3.setCellValueFactory(c -> c.getValue().getPropertyGoalsDifference().asObject());
		TableColumn<Team, Integer> goalDifferenceCol4 = new TableColumn<Team, Integer>("GD");
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

	public TableView<Team> getTable1() {
		return table1;
	}

	public TableView<Team> getTable2() {
		return table2;
	}

	public TableView<Team> getTable3() {
		return table3;
	}

	public TableView<Team> getTable4() {
		return table4;
	}

	public HBox gethBox1() {
		return hBox1;
	}

	public HBox gethBox2() {
		return hBox2;
	}

	public HBox gethBox3() {
		return hBox3;
	}

	public GridPane getGridPane() {
		return gridPane;
	}
}
