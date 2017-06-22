package tournament;

import java.util.List;

import javafx.collections.ObservableList;

public class KOGame {

	private ObservableList<Team> teams;
	private Team matchWinnerTeam;
	private Team setWinnerTeam;
	private boolean KOGameFinished = false;
	private String name;

	private int currentSet, team1Wins, team2Wins, bestOfSets, numberOfWinsNeeded;

	/**
	 * 
	 * @param teams Must be a list containins teams
	 * @param name Must be the same as the identifier
	 */
	public KOGame(ObservableList<Team> teams, String name) {
		this.name = name;
		this.teams = teams;
		bestOfSets = 5;
		currentSet = 0;
		team1Wins = 0;
		team2Wins = 0;
		numberOfWinsNeeded = bestOfSets / 2 + 1;
	}

	/**
	 * Write result into current set
	 */
	public void setResult(int player1, int player2) {
		switch (currentSet) {
		case 0: {
			teams.get(0).getPropertySet1().set(player1);
			teams.get(1).getPropertySet1().set(player2);
			if(player1 > player2){
				setWinnerTeam = teams.get(0);
			}
			else{
				setWinnerTeam = teams.get(1);
			}
			break;
		}
		case 1: {
			teams.get(0).getPropertySet2().set(player1);
			teams.get(1).getPropertySet2().set(player2);
			if(player1 > player2){
				setWinnerTeam = teams.get(0);
			}
			else{
				setWinnerTeam = teams.get(1);
			}
			break;
		}
		case 2: {
			teams.get(0).getPropertySet3().set(player1);
			teams.get(1).getPropertySet3().set(player2);
			if(player1 > player2){
				setWinnerTeam = teams.get(0);
			}
			else{
				setWinnerTeam = teams.get(1);
			}
			break;
		}
		case 3: {
			teams.get(0).getPropertySet4().set(player1);
			teams.get(1).getPropertySet4().set(player2);
			if(player1 > player2){
				setWinnerTeam = teams.get(0);
			}
			else{
				setWinnerTeam = teams.get(1);
			}
			break;
		}
		case 4: {
			teams.get(0).getPropertySet5().set(player1);
			teams.get(1).getPropertySet5().set(player2);
			if(player1 > player2){
				setWinnerTeam = teams.get(0);
			}
			else{
				setWinnerTeam = teams.get(1);
			}
			break;
		}
		}

		setWins();
		currentSet++;
	}

	/**
	 * Increase number of wins of the team which has won the current set
	 */
	private void setWins() {
//		System.out.println(sets[currentSet].winnerTeam.toString());
		System.out.println(teams.get(0).toString());
		if (setWinnerTeam.equals(teams.get(0))) {
			team1Wins++;
			if (team1Wins >= numberOfWinsNeeded) {
				matchWinnerTeam = teams.get(0);
				setKOGameFinished(true);
			}
		} else {
			team2Wins++;
			if (team2Wins >= numberOfWinsNeeded) {
				matchWinnerTeam = teams.get(1);
				setKOGameFinished(true);
			}
		}
		setWinnerTeam = null;
	}

	/**
	 * 
	 * @return The amount of Wins needed.
	 */
	public int getNumberOfWinsNeeded() {
		return numberOfWinsNeeded;
	}

	/**
	 * 
	 * @return The amount of sets to be played (at the most).
	 */
	public int getBestOfSets() {
		return bestOfSets;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public Team getWinnerTeam() {
		return matchWinnerTeam;
	}

	public int getCurrentSet() {
		return currentSet;
	}

	public int getTeam1Wins() {
		return team1Wins;
	}

	public int getTeam2Wins() {
		return team2Wins;
	}

	public boolean isKOGameFinished() {
		return KOGameFinished;
	}
	
	public String getName() {
		return name;
	}


	private void setKOGameFinished(boolean kOGameFinished) {
		KOGameFinished = kOGameFinished;
	}

}
