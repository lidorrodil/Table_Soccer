package tournament;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Has all functions concerning the list of all teams.
 *
 */

public class TeamList {

	private ObservableList<Team> teamlist;
	public ObservableList<Team> dummyTeamlist;

	public TeamList() {
		teamlist = FXCollections.observableArrayList();
		// dummyTeamlist = FXCollections.observableArrayList(new Team("team1",
		// "player1", "player2"),new Team("team2", "player1", "player2"),
		// new Team("team3", "player1", "player2"),new Team("team4", "player1",
		// "player2"),new Team("team5", "player1", "player2"));
	}

	public boolean addTeam(String teamName, String player1, String player2) {
		try {
			// checkIfNameValid(teamName);
			checkIfNameValid(player1);
			checkIfNameValid(player2);
			teamlist.add(new Team(teamName, player1, player2));
			return true;
		} catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeTeam(String teamName) {
		try {
			if (teamlist.stream().anyMatch(p -> p.getTeamName().equals(teamName))) {
				teamlist.remove(teamlist.stream().filter(p -> p.getTeamName().equals(teamName)).findFirst().get());
			} else {
				throw new IllegalArgumentException(teamName + " is no team!");
			}
		} catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean checkIfNameValid(String name) {
		int minNameLength = 3;
		if (name == null) {
			throw new IllegalArgumentException(name + " is missing.");
		} else if (name.length() < minNameLength) {
			throw new IllegalArgumentException(name + " too short.");
		} else if (teamlist.stream().anyMatch(p -> p.getTeamName().equals(name))) {
			throw new IllegalArgumentException(name + " already exists!");
		}
		return true;
	}

	public void shuffleTeams() {
		Collections.shuffle(teamlist);
	}

	public ObservableList<Team> getTeamlist() {
		return teamlist;
	}

}
