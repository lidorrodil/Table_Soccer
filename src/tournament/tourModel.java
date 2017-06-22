package tournament;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourModel {

	private static ObservableList<Team> group1;
	private static ObservableList<Team> group2;
	private static ObservableList<Team> group3;
	private static ObservableList<Team> group4;
	
	private static ObservableList<Team> QFGroup1;
	private static ObservableList<Team> QFGroup2;
	private static ObservableList<Team> QFGroup3;
	private static ObservableList<Team> QFGroup4;
	private static ObservableList<Team> SFGroup1;
	private static ObservableList<Team> SFGroup2;
	private static ObservableList<Team> FinalGroup;


	private int teamListIndex = 0;
	private int preliminaryGroupSize;
	private final int PRELIMINARY_GROUP_NUMBER = 4;

	public TourModel() {

	}
	
	/**
	 * Determines the pairings for the Quarterfinals .<br>
	 * QF1: Group 1 First vs. Group 2 Second <br>
	 * QF2: Group 1 Second vs. Group 2 First <br>
	 * QF3: Group 3 First vs. Group 4 Second <br>
	 * QF4: Group 3 Second vs. Group 4 First <br>
	 * Make sure group 1-4 are initialised!
	 * 
	 */
	public void getQuarterFinalPairings(){
		// Get Rankings of the groups
		group1.sort((c1,c2) -> c1.compare(c2));
		group2.sort((c1,c2)-> c1.compare(c2));
		group3.sort((c1,c2)-> c1.compare(c2));
		group4.sort((c1,c2)-> c1.compare(c2));
		
		//Put Winner and Runnerup into QF Groups
		QFGroup1 = FXCollections.observableArrayList();
		QFGroup2 = FXCollections.observableArrayList();
		QFGroup3 = FXCollections.observableArrayList();
		QFGroup4 = FXCollections.observableArrayList();

		QFGroup1.add(group1.get(0));
		QFGroup1.add(group2.get(1));
		QFGroup2.add(group2.get(0));
		QFGroup2.add(group1.get(1));
		QFGroup3.add(group3.get(0));
		QFGroup3.add(group4.get(1));
		QFGroup4.add(group4.get(0));
		QFGroup4.add(group3.get(1));
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
