package tournament;

import java.util.List;

public class KOGame {

	private List<Team> teams;
	private Team winnerTeam;

	private Set[] sets;

	private int currentSet, team1Wins, team2Wins, bestOfSets, numberOfWinsNeeded;

	public KOGame(Team team1, Team team2, int bestOfSets) {
		teams.add(team1);
		teams.add(team2);
		sets = new Set[bestOfSets];
		for (int i = 0; i < bestOfSets; i++) {
			sets[i] = new Set(team1, team2);
		}
		currentSet = 0;
		team1Wins = 0;
		team2Wins = 0;
		this.bestOfSets = bestOfSets;
		numberOfWinsNeeded = bestOfSets / 2 + 1;
	}

	/**
	 * Write result into current set
	 */
	public void setResult(int player1, int player2) {
		switch (currentSet) {
		case 1: {
			teams.get(0).getSet1().set(player1);
			teams.get(1).getSet1().set(player2);
			break;
		}
		case 2: {
			teams.get(0).getSet2().set(player1);
			teams.get(1).getSet2().set(player2);
			break;
		}
		case 3: {
			teams.get(0).getSet3().set(player1);
			teams.get(1).getSet3().set(player2);
			break;
		}
		case 4: {
			teams.get(0).getSet4().set(player1);
			teams.get(1).getSet4().set(player2);
			break;
		}
		case 5: {
			teams.get(0).getSet5().set(player1);
			teams.get(1).getSet5().set(player2);
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
		if (sets[currentSet - 1].winnerTeam.equals(teams.get(0))) {
			team1Wins++;
			if (team1Wins >= numberOfWinsNeeded) {
				winnerTeam = teams.get(0);
			}
		} else {
			team2Wins++;
			if (team2Wins >= numberOfWinsNeeded) {
				winnerTeam = teams.get(1);
			}
		}
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
		return winnerTeam;
	}

	public Set[] getSets() {
		return sets;
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

}
