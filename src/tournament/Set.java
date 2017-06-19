package tournament;

/**
 * Defines sets within a game
 *
 */

public class Set {

	public final int[] result = new int[2];
	public Team team1, team2;
	public Team winnerTeam;

	public Set(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}

	public void setResult(int ptsTeam1, int ptsTeam2) {
		result[0] = ptsTeam1;
		result[1] = ptsTeam2;
	}

	public void setWinner() {
		if (result[0] > result[1]) {
			winnerTeam = team1;
		} else {
			winnerTeam = team2;
		}
	}

}
