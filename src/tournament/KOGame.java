package tournament;

public class KOGame {

	private Team team1, team2;
	private Team winnerTeam;

	private Set[] sets;

	private int currentSet, team1Wins, team2Wins, numberOfWinsNeeded;

	public KOGame(Team team1, Team team2, int bestOfSets){
		this.team1=team1;
		this.team2=team2;
		sets = new Set[bestOfSets];
		for(int i = 0; i<bestOfSets; i++){
			sets[i] = new Set(team1, team2);
		}
		currentSet = 1;
		team1Wins = 0;
		team2Wins = 0;
		numberOfWinsNeeded = bestOfSets/2+1;
	}

	/*
	 * Write result into current set
	 */
	public void setResult(int player1, int player2){
		sets[currentSet-1].setResult(player1, player2);
		sets[currentSet-1].setWinner();
		setWins();
		currentSet++;
	}
	
	/*
	 * Increase number of wins of the team which has won the current set
	 */
	private void setWins(){
		if(sets[currentSet-1].winnerTeam.equals(team1)){
			team1Wins++;
			if(team1Wins >= numberOfWinsNeeded){
				winnerTeam = team1;
			}
		} else{
			team2Wins++;
			if(team2Wins >= numberOfWinsNeeded){
				winnerTeam = team2;
			}
		}
		
		
	}
	
	
}
