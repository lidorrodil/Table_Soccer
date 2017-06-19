package tournament;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * This class is the skeleton for group phase table.
 *
 */

public class PreliminaryTable extends TableView<Team> {
	
	private TableView<Team> table = new TableView<Team>();
	
	private final TableColumn<Team,Integer> rankCol = new TableColumn<Team,Integer>("Rank");
	private final TableColumn<Team, String> teamNameCol = new TableColumn<Team,String>("Team Name");
	private final TableColumn<Team,Integer> gamesCol = new TableColumn<Team,Integer>("Games");
	private final TableColumn<Team,Integer> winCol = new TableColumn<Team,Integer>("W");
	private final TableColumn<Team,Integer> tieCol = new TableColumn<Team,Integer>("T");
	private final TableColumn<Team,Integer> lossCol = new TableColumn<Team,Integer>("L");
	private final TableColumn<Team,Integer> goalsCol = new TableColumn<Team,Integer>("Goals");
	private final TableColumn<Team,Integer> goalScoredCol = new TableColumn<Team,Integer>("Scored");
	private final TableColumn<Team,Integer> goalConcededCol = new TableColumn<Team,Integer>("Conceded");
	private final TableColumn<Team,Integer> totPointsCol = new TableColumn<Team,Integer>("Point(s)");

	public PreliminaryTable(){
		gamesCol.getColumns().addAll(winCol, tieCol, lossCol);
		goalsCol.getColumns().addAll(goalScoredCol,goalConcededCol);
		table.getColumns().addAll(rankCol,teamNameCol,gamesCol,goalsCol,totPointsCol);	
	}
	
}
