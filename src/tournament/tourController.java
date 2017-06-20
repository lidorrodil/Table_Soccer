package tournament;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import userInterface.model;
import userInterface.view;

public class tourController {
	final private tourModel model;
	final private tourView view;
	
	private TeamList teamlist = new TeamList();
	
	public tourController(tourModel model, tourView view) throws Exception {
		this.model = model;
		this.view = view;
		
		view.addTeamButton.setOnAction(c-> {
			try {
				addTeam();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		view.removeTeamButton.setOnAction(c->{
			try {
				removeTeam();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		view.showAllTeamsButton.setOnAction(c->{
			try {
				showAllTeamsView();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
				
			
	}
	
	/**
	 * Lets you add a team.
	 * @throws Exception
	 */
	public void addTeam() throws Exception{
		AddMemberView view1 = new AddMemberView();
		view1.start();
	}
	
	/**
	 * Lets you remove a team.
	 * @throws Exception
	 */
	public void removeTeam() throws Exception{
		RemoveMemberView view2 = new RemoveMemberView();
		view2.start();
	}
	
	/**
	 * Opens a windows with all registered teams.
	 */
	public void showAllTeamsView(){
		ShowAllMembersView view3 = new ShowAllMembersView();
		view3.start();
	}

	/**
	 * 
	 * @return An observable list containing all teams.
	 */
	public TeamList getTeamlist() {
		return teamlist;
	}

	/**
	 * set the team list for the tournament.
	 * @param teamlist Must be an observable list.
	 */
	public void setTeamlist(TeamList teamlist) {
		this.teamlist = teamlist;
	}
}
