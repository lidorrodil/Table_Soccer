package tournament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Defines a team and its' properties
 *
 */
public class Team {
	private final SimpleIntegerProperty draws = new SimpleIntegerProperty();
	private final SimpleIntegerProperty gamesPlayed = new SimpleIntegerProperty();
	private final SimpleIntegerProperty goalsConceded = new SimpleIntegerProperty();
	private final SimpleIntegerProperty goalsDifference = new SimpleIntegerProperty();
	private final SimpleIntegerProperty goalsScored = new SimpleIntegerProperty();
	private final SimpleIntegerProperty losts = new SimpleIntegerProperty();
	private final SimpleStringProperty player1 = new SimpleStringProperty();
	private final SimpleStringProperty player2 = new SimpleStringProperty();
	private final SimpleIntegerProperty points = new SimpleIntegerProperty();
	private final SimpleStringProperty teamName = new SimpleStringProperty();
	private final SimpleIntegerProperty wins = new SimpleIntegerProperty();
	
	private final SimpleIntegerProperty set1 = new SimpleIntegerProperty();
	private final SimpleIntegerProperty set2 = new SimpleIntegerProperty();
	private final SimpleIntegerProperty set3 = new SimpleIntegerProperty();
	private final SimpleIntegerProperty set4 = new SimpleIntegerProperty();
	private final SimpleIntegerProperty set5 = new SimpleIntegerProperty();

	List<Object> values = new ArrayList<Object>();

	private final int PTS_FOR_WIN = 2;
	private final int PTS_FOR_DRAW = 1;
	private final int PTS_FOR_LOST = 0;

	public Team(String teamName, String player1, String player2) {
		values = Arrays.asList(0,0,0,0,0,0,player1,player2,0,teamName,0);
		updateRepresentations();
	}
	
	public Team clone(){
		Team clone = new Team(this.getTeamName(),this.getPlayer1(), this.getPlayer2());
		clone.getPropertySet1().set(0);
		clone.getPropertySet2().set(0);
		clone.getPropertySet3().set(0);
		clone.getPropertySet4().set(0);
		clone.getPropertySet5().set(0);
		return clone;
	}
	
	/**
	 * Updates all the values of the team to the values in array values.
	 */
	private void updateRepresentations(){
		draws.setValue(Integer.parseInt(values.get(0).toString()));
		gamesPlayed.setValue(Integer.parseInt(values.get(1).toString()));
		goalsConceded.setValue(Integer.parseInt(values.get(2).toString()));
		goalsDifference.setValue(Integer.parseInt(values.get(3).toString()));
		goalsScored.setValue(Integer.parseInt(values.get(4).toString()));
		losts.setValue(Integer.parseInt(values.get(5).toString()));
		player1.setValue(values.get(6).toString());
		player2.setValue(values.get(7).toString());
		points.setValue(Integer.parseInt(values.get(8).toString()));
		teamName.setValue(values.get(9).toString());
		wins.setValue(Integer.parseInt(values.get(10).toString()));
		set1.set(0);
		set2.set(0);
		set3.set(0);
		set4.set(0);
		set5.set(0);
	}
	
	/**
	 * Compares points, goal Difference, and goals scored (in this order) -> more is better.
	 * @param other
	 * @return The better team.
	 */
	public int compare(Team other){
		if(this.getPoints()!= other.getPoints()){
			return other.getPoints()-this.getPoints();
		} 
		else if(this.getGoalsDifference() != other.getGoalsDifference()){
			return other.getGoalsDifference() - this.getGoalsDifference();
		}
		else if(this.getGoalsScored() != other.getGoalsScored()){
			return other.getGoalsScored() -  this.getGoalsScored();
		}
		return 0;
	}
	
	/**
	 * @return the team name.
	 */
	@Override
	public String toString(){
		return teamName.get();
	}
	
	/**
	 * Directly updates Total Points as well.
	 */
	public void increaseWins() {
		wins.set(wins.get()+1);
		updatePoints();
		updateGamesPlayed();
	}

	/**
	 * Directly updates Total Points as well.
	 */
	public void increaseDraws() {
		draws.set(draws.get()+1);
		updatePoints();
		updateGamesPlayed();
	}

	/**
	 * Directly updates Total Points as well.
	 */
	public void increaseLosts() {
		losts.set(losts.get()+1);
		updatePoints();
		updateGamesPlayed();
	}

	/**
	 * Directly updates Goal Difference as well
	 * @param scored Represents the amount of goals, the team has scored.
	 */
	public void addGoalsScored(int scored) {
		goalsScored.set(goalsScored.get() + scored);
		updateGoalDifference();
	}

	/**
	 * Directly updates Goal Difference as well
	 * @param conceded How many goals the team let in.
	 */
	public void addGoalsConceded(int conceded) {
		goalsConceded.set(Math.abs(goalsConceded.get() + conceded));
		updateGoalDifference();
	}	

	private void updateGamesPlayed() {
		gamesPlayed.set(draws.get()+wins.get()+losts.get());
	}

	private void updatePoints() {
		points.set(wins.get() * PTS_FOR_WIN + draws.get() * PTS_FOR_DRAW + losts.get() * PTS_FOR_LOST);
	}

	private void updateGoalDifference() {
		goalsDifference.set(goalsScored.get() - goalsConceded.get());
		System.out.println("Scored"+ goalsScored.get());
		System.out.println("let in" + goalsConceded.get());
		System.out.println("diff "+ goalsDifference.get());
	}
	

	/*
	 * Just Getters and Setters below this point.
	 */	
	public List<Object> getValues() {
		return values;
	}

	public SimpleIntegerProperty getPropertyDraws() {
		return draws;
	}

	public SimpleIntegerProperty getPropertyGamesPlayed() {
		return gamesPlayed;
	}

	public SimpleIntegerProperty getPropertyGoalsConceded() {
		return goalsConceded;
	}

	public SimpleIntegerProperty getPropertyGoalsDifference() {
		return goalsDifference;
	}

	public SimpleIntegerProperty getPropertyGoalsScored() {
		return goalsScored;
	}

	public SimpleIntegerProperty getPropertyLosts() {
		return losts;
	}

	public SimpleStringProperty getPropertyPlayer1() {
		return player1;
	}

	public SimpleStringProperty getPropertyPlayer2() {
		return player2;
	}

	public SimpleIntegerProperty getPropertyPoints() {
		return points;
	}

	public SimpleStringProperty getPropertyTeamName() {
		return teamName;
	}

	public SimpleIntegerProperty getPropertyWins() {
		return wins;
	}
	
	public int getDraws() {
		return draws.get();
	}

	public int getGamesPlayed() {
		return gamesPlayed.get();
	}

	public int getGoalsConceded() {
		return goalsConceded.get();
	}

	public int getGoalsDifference() {
		return goalsDifference.get();
	}

	public int getGoalsScored() {
		return goalsScored.get();
	}

	public int getLosts() {
		return losts.get();
	}

	public String getPlayer1() {
		return player1.get();
	}

	public String getPlayer2() {
		return player2.get();
	}

	public int getPoints() {
		return points.get();
	}

	public String getTeamName() {
		return teamName.get();
	}

	public int getWins() {
		return wins.get();
	}

	public SimpleIntegerProperty getPropertySet1() {
		return set1;
	}

	public SimpleIntegerProperty getPropertySet2() {
		return set2;
	}

	public SimpleIntegerProperty getPropertySet3() {
		return set3;
	}

	public SimpleIntegerProperty getPropertySet4() {
		return set4;
	}

	public SimpleIntegerProperty getPropertySet5() {
		return set5;
	}
	
}
