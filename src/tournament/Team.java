package tournament;

/**
 * Defines a team and its' properties
 *
 */

public class Team {
	private String teamName, player1, player2;
	private int gamesPlayed, wins, draws, losts, goalsScored, goalsConceded, goalsDifference, points;

	private final int PTS_FOR_WIN = 2;
	private final int PTS_FOR_DRAW = 1;
	private final int PTS_FOR_LOST = 0;

	public Team(String teamName, String player1, String player2) {
		this.teamName = teamName;
		this.player1 = player1;
		this.player2 = player2;
		this.gamesPlayed = this.wins = this.draws = this.losts = this.goalsScored = this.goalsConceded = 0;
		setPoints();
		setGoalDifference();
	}
	
	
	
	private void setPoints() {
		points =  wins * PTS_FOR_WIN + draws * PTS_FOR_DRAW + losts * PTS_FOR_LOST;
	}

	private void setGoalDifference() {
		goalsDifference = goalsScored - goalsConceded;
	}
	
	

	public String getPlayer1() {
		return player1;
	}

	public String getPlayer2() {
		return player2;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int getWins() {
		return wins;
	}

	public int getDraws() {
		return draws;
	}

	public int getLosts() {
		return losts;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public int getGoalsConceded() {
		return goalsConceded;
	}

	public int getGoalsDifference() {
		return goalsDifference;
	}

	public int getPoints() {
		return points;
	}

	public void increaseGamesPlayed() {
		this.gamesPlayed++;
	}

	/*
	 * Directly updates Total Points as well
	 */
	public void increaseWins() {
		this.wins++;
		this.setPoints();
	}
	
	/*
	 * Directly updates Total Points as well
	 */
	public void increaseDraws() {
		this.draws++;
		this.setPoints();
	}

	/*
	 * Directly updates Total Points as well
	 */
	public void increaseLosts() {
		this.losts++;
		this.setPoints();
	}
	
	/*
	 * Directly updates Goal Difference as well
	 */
	public void addGoalsScored(int goalsScored) {
		this.goalsScored += goalsScored;
		this.setGoalDifference();
	}

	/*
	 * Directly updates Goal Difference as well
	 */
	public void addGoalsConceded(int goalsConceded) {
		this.goalsConceded += goalsConceded;
		this.setGoalDifference();
	}

	

}
