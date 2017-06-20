package tournament;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Has all functions concerning the list of all teams.
 * TODO: Create 4 lists for the Preliminaries
 */

public class TeamList {

	private static ObservableList<Team> teamlist;

	public TeamList() {
		teamlist = FXCollections.observableArrayList();
	}

	/**
	 * Adds a team to the list with all the teams.
	 * @param teamName Must be greater than 3 chars.
	 * @param player1 Must be greater than 3 chars.
	 * @param player2 Must be greater than 3 chars.
	 * @return Whether adding was successful or failed (=false).
	 */
	public static boolean addTeam(String teamName, String player1, String player2) {
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

	/**
	 * Removes selected team from the List of all teams.
	 * @param teamName Must exist in teamList.
	 * @return Whether removing was successful or failed (=false).
	 */
	public static boolean removeTeam(String teamName) {
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

	/**
	 * Checks if name is longer than 3 chars and not null.
	 * @param name Should be longer than 3 chars and not null.
	 * @return Whether or not name is valid.
	 */
	public static boolean checkIfNameValid(String name) {
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

	/**
	 * Shuffles all teams to make 4 Preliminary Tables.
	 */
	public void shuffleTeams() {
		Collections.shuffle(teamlist);
	}

	/**
	 * Makes teamList accessible outside the class.
	 * @return the teamList of all teams.
	 */
	public static ObservableList<Team> getTeamlist() {
		return teamlist;
	}

}
