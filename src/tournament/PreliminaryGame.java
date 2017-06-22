package tournament;

/**
 * Use this class for all the games that are not in the KO phase.
 * @author joelf
 * 
 */

public class PreliminaryGame {

	private Team team1, team2;
	
	public PreliminaryGame(Team team1, Team team2){
		this.team1 = team1;
		this.team2 = team2;
	}
	
	/**
	 * Updates the Teams' stats
	 */
	public void setResult(int goalsTeam1, int goalsTeam2){
		if(goalsTeam1 > goalsTeam2){
			team1.increaseWins();
			team2.increaseLosts();
		}
		else if(goalsTeam1 == goalsTeam2){
			team1.increaseDraws();
			team2.increaseDraws();
		}
		else{
			team1.increaseLosts();
			team2.increaseWins();
		}
		
		team1.addGoalsScored(goalsTeam1);
		team1.addGoalsConceded(goalsTeam2);
		
		team2.addGoalsScored(goalsTeam2);
		team2.addGoalsConceded(goalsTeam1);
	}
	
	public String toString(){
		return team1.toString() + " vs. " + team2.toString();
	}
	
	
}
