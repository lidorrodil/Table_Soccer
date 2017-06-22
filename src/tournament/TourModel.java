package tournament;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tournament {

	private static ObservableList<Team> group1;
	private static ObservableList<Team> group2;
	private static ObservableList<Team> group3;
	private static ObservableList<Team> group4;

	private int teamListIndex = 0;
	private int preliminaryGroupSize;
	private final int PRELIMINARY_GROUP_NUMBER = 4;

	public Tournament() {

	}

	/**
	 * Calculates the size of preliminary groups.
	 */
	private void calcPreliminaryGroupSize() {
		preliminaryGroupSize = (TeamList.getTeamListSize() % PRELIMINARY_GROUP_NUMBER > 0)
				? TeamList.getTeamListSize() / PRELIMINARY_GROUP_NUMBER + 1
				: TeamList.getTeamListSize() / PRELIMINARY_GROUP_NUMBER;
	}

	/**
	 * Puts the needed amount of needed teams into the specified group.
	 */
	private void putTeamsInGroups() {
		// TODO: Delete Logs
		System.out.println("grousize = " + this.preliminaryGroupSize);
		
		while(true){
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			System.out.println(TeamList.getTeamlist().get(teamListIndex).toString());
			group1.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
			
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			System.out.println(TeamList.getTeamlist().get(teamListIndex).toString());
			group2.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
			
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			System.out.println(TeamList.getTeamlist().get(teamListIndex).toString());
			group3.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
			
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			System.out.println(TeamList.getTeamlist().get(teamListIndex).toString());
			group4.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
		}
		teamListIndex = 0;
	}

	/**
	 * puts teams randomised into preliminary groups.
	 */
	public void startTournament() {
		// Create the groups for preliminaries
		group1 = FXCollections.observableArrayList();
		group2 = FXCollections.observableArrayList();
		group3 = FXCollections.observableArrayList();
		group4 = FXCollections.observableArrayList();

		// Randomises which Team lands in which Group
		TeamList.shuffleTeams();
		TeamList.getTeamlist().stream().forEach(System.out::println);

		calcPreliminaryGroupSize();

		// Fill the groups
		putTeamsInGroups();
	}
	

	public static ObservableList<Team> getGroup1() {
		return group1;
	}

	public static ObservableList<Team> getGroup2() {
		return group2;
	}

	public static ObservableList<Team> getGroup3() {
		return group3;
	}

	public static ObservableList<Team> getGroup4() {
		return group4;
	}

}
