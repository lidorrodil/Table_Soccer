package tournament;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Has all functions concerning the list of all teams.
 *
 */

public class TeamList {
	
	private List<Team> teamlist;
	
	public TeamList(){
		teamlist = new LinkedList<Team>();
	}

	public boolean addTeam(String teamName, String player1, String player2){
		try {
			checkIfNameValid(teamName);
			checkIfNameValid(player1);
			checkIfNameValid(player2);
			teamlist.add(new Team(teamName, player1, player2));
			return true;
		} 
		catch (IllegalArgumentException e){
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean removeTeam(String teamName){
		try{
			if(teamlist.stream().anyMatch(p->p.getTeamName().equals(teamName))){
				teamlist.remove(teamlist.stream().filter(p->p.getTeamName().equals(teamName)).findFirst().get());
			} 
			else{
				throw new IllegalArgumentException(teamName + " is no team!");
			}
		}
		catch(IllegalArgumentException e){
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean checkIfNameValid(String name){
		int minNameLength = 3;
		if(name == null){
			throw new IllegalArgumentException(name+ " is missing.");
		} else if(name.length()<minNameLength){
			throw new IllegalArgumentException(name + " too short.");
		} else if(teamlist.stream().anyMatch(p->p.getTeamName().equals(name))){
			throw new IllegalArgumentException(name + " already exists!");
		}
		return true;		
	}
	
	public void shuffleTeams(){
		Collections.shuffle(teamlist);
	}
	
	public static void main(String[] args){
		TeamList t = new TeamList();
		
		t.addTeam("team", "name1", "name2");
		t.addTeam("teaöm", "blö", "kle");
		
		System.out.println(t.teamlist.stream().filter(p -> p.getTeamName().equals("team")).findFirst().get().getPlayer1());
		
		t.removeTeam("tam");
	}
	
}
