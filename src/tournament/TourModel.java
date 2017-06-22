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
	private static ObservableList<Team> finalGroup;
	
	protected KOGame quarterFinal1;
	protected KOGame quarterFinal2;
	protected KOGame quarterFinal3;
	protected KOGame quarterFinal4;
	protected KOGame semiFinal1;
	protected KOGame semiFinal2;
	protected KOGame finale;


	private int teamListIndex = 0;
	private int preliminaryGroupSize;
	private final int PRELIMINARY_GROUP_NUMBER = 4;

	public TourModel() {
	}
	
	/**
	 * Determines the pairings for the Finale.
	 */
	public void setFinalPairing(){
		finalGroup =FXCollections.observableArrayList();
		
		Team SFClone1 = semiFinal1.getWinnerTeam().clone();
		Team SFClone2 = semiFinal2.getWinnerTeam().clone();
		
		finalGroup.add(SFClone1);
		finalGroup.add(SFClone2);
		
		finale = new KOGame(finalGroup, "finale");
	}
	
	
	
	/**
	 * Determines the pairings for the Semi Finals.
	 */
	public void setSemiFinalPairings(){
		SFGroup1 =FXCollections.observableArrayList();
		SFGroup2 =FXCollections.observableArrayList();
		
		Team QFClone1 = quarterFinal1.getWinnerTeam().clone();
		Team QFClone2 = quarterFinal2.getWinnerTeam().clone();
		Team QFClone3 = quarterFinal3.getWinnerTeam().clone();
		Team QFClone4 = quarterFinal4.getWinnerTeam().clone();
		
		SFGroup1.add(QFClone1);
		SFGroup1.add(QFClone2);
		SFGroup2.add(QFClone3);
		SFGroup2.add(QFClone4);
		
		semiFinal1 = new KOGame(SFGroup1, "semiFinal1");
		semiFinal2 = new KOGame(SFGroup2, "semiFinal2");
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
	public void setQuarterFinalPairings(){
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
		
		quarterFinal1 = new KOGame(QFGroup1, "quarterFinal1");
		quarterFinal2 = new KOGame(QFGroup2, "quarterFinal2");
		quarterFinal3 = new KOGame(QFGroup3, "quarterFinal3");
		quarterFinal4 = new KOGame(QFGroup4, "quarterFinal4");
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
		while(true){
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			group1.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
			
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			group2.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
			
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
			group3.add(TeamList.getTeamlist().get(teamListIndex));
			teamListIndex++;
			
			if(teamListIndex > TeamList.getTeamListSize()-1){
				break;
			}
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
	
	public static ObservableList<Team> getQFGroup1() {
		return QFGroup1;
	}

	public static ObservableList<Team> getQFGroup2() {
		return QFGroup2;
	}

	public static ObservableList<Team> getQFGroup3() {
		return QFGroup3;
	}

	public static ObservableList<Team> getQFGroup4() {
		return QFGroup4;
	}

	public static ObservableList<Team> getSFGroup1() {
		return SFGroup1;
	}

	public static ObservableList<Team> getSFGroup2() {
		return SFGroup2;
	}

	public ObservableList<Team> getFinalGroup() {
		return finalGroup;
	}

	public KOGame getQuarterFinal1() {
		return quarterFinal1;
	}

	public KOGame getQuarterFinal2() {
		return quarterFinal2;
	}

	public KOGame getQuarterFinal3() {
		return quarterFinal3;
	}

	public KOGame getQuarterFinal4() {
		return quarterFinal4;
	}

	public KOGame getSemiFinal1() {
		return semiFinal1;
	}

	public KOGame getSemiFinal2() {
		return semiFinal2;
	}

	public KOGame getFinal() {
		return finale;
	}

}