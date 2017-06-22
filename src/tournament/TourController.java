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

public class TourController {
	final private TourModel model;
	final private TourView view;
	
	private TeamList teamlist = new TeamList();
	
	ShowPreliminaryGamesView view0 = new ShowPreliminaryGamesView();
	
	public TourController(TourModel model, TourView view) throws Exception {
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
		
		view.preliminaryGameButton.setDisable(true);
		view.startTournamentButton.setOnAction(c->startTournament());
		view.preliminaryGameButton.setOnAction(c->showPreliminaryGamesView2());
			
	}
	
	public void showPreliminaryGamesView() {
		view0.start();
	}
	
	public void showPreliminaryGamesView2(){
		view0.start2();
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
	
	/**
	 * Initialises tournament, puts teams in groups and displays them.
	 */
	public void startTournament(){
		model.startTournament();
		view.getTable1().setItems(model.getGroup1());
		view.getTable2().setItems(model.getGroup2());
		view.getTable3().setItems(model.getGroup3());
		view.getTable4().setItems(model.getGroup4());
		view.startTournamentButton.setDisable(true);
		view.preliminaryGameButton.setDisable(false);
		
		int posOfAdd = view.hBox2.getChildren().indexOf(view.addTeamButton);
		view.hBox2.getChildren().remove(posOfAdd);
		view.hBox2.getChildren().add(posOfAdd, view.preliminaryGameButton);
		int posOfRemove = view.hBox2.getChildren().indexOf(view.removeTeamButton);
		view.hBox2.getChildren().remove(posOfRemove);
		view.hBox2.getChildren().add(posOfRemove, view.startKOButton);
		
		showPreliminaryGamesView();
	}
}
