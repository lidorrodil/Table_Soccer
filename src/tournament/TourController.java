package tournament;

import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import userInterface.Model;
import userInterface.view;

/**
 * This is the base class of the tournament. From here, all actions are coordinated.
 * @author joelf
 *
 */

public class TourController {
	
	final private TourModel model;
	final private TourView view;

	private TeamList teamlist = new TeamList();

	ShowPreliminaryGamesView view0 = new ShowPreliminaryGamesView();
//
	public TourController(TourModel model, TourView view) throws Exception {
		this.model = model;
		this.view = view;

		view.addTeamButton.setOnAction(c -> {
			try {
				addTeam();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		view.removeTeamButton.setOnAction(c -> {
			try {
				removeTeam();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		view.showAllTeamsButton.setOnAction(c -> {
			try {
				showAllTeamsView();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		view.rulesButton.setOnAction(c->showRules());
		view0.finishButton.setOnAction(c -> enableStartQFButton());
		view.startQFButton.setOnAction(c -> startQuarterFinals());
		view.QFButton1.setOnAction(c -> buttonQF1Pressed());
		view.QFButton2.setOnAction(c -> buttonQF2Pressed());
		view.QFButton3.setOnAction(c -> buttonQF3Pressed());
		view.QFButton4.setOnAction(c -> buttonQF4Pressed());
		
		view.startSFButton.setOnAction(c->startSemiFinals());
		view.SFButton1.setOnAction(c->buttonSF1Pressed());
		view.SFButton2.setOnAction(c->buttonSF2Pressed());
		
		view.startFButton.setOnAction(c->enableStartFinaleButton());

		view.preliminaryGameButton.setDisable(true);
		view.startTournamentButton.setOnAction(c -> startTournament());
		view.preliminaryGameButton.setOnAction(c -> showPreliminaryGamesView2());
		view.startFButton.setOnAction(c->startFinale());
		view.finalsButton.setOnAction(c->buttonFPressed());
		view.endFinalButton.setOnAction(c->showWinnerTeam());
		
	}
	
	/**
	 * Displays the games rules.
	 */
	public void showRules(){
		RulesView view7 = new RulesView();
		view7.start();
	}
	
	/**
	 * Displays the winners
	 */
	public void showWinnerTeam(){
		WinnerView view6 = new WinnerView();
		view6.start(model.finale.getWinnerTeam());
	}
	
	/**
	 * Gets Result from Final
	 */
	public void buttonFPressed() {
		AddKOResultView view8 = new AddKOResultView();
		view8.start(model.finale, view);
	}
	
	/**
	 * Puts Finalists into tables
	 */
	public void startFinale(){
		model.setFinalPairing();
		view.tableKOList.get(6).setItems(model.getFinalGroup());
		view.finalsButton.setDisable(false);
		
		int finaleButtonPos = view.hBox2.getChildren().indexOf(view.startFButton);
		view.hBox2.getChildren().remove(finaleButtonPos);
		view.hBox2.getChildren().add(finaleButtonPos, view.endFinalButton);
		view.endFinalButton.setDisable(true);
	}
	
	/**
	 * Gets Result from Semi Final
	 */
	public void buttonSF1Pressed() {
		AddKOResultView view5 = new AddKOResultView();
		view5.start(model.semiFinal1, view);
	}
	
	/**
	 * Gets Result from Semi Final
	 */
	public void buttonSF2Pressed() {
		AddKOResultView view5 = new AddKOResultView();
		view5.start(model.semiFinal2, view);
	}
	
	/**
	 * Puts Semi Finalists in Tables.
	 */
	public void startSemiFinals(){
		model.setSemiFinalPairings();
		view.tableKOList.get(4).setItems(model.getSFGroup1());
		view.tableKOList.get(5).setItems(model.getSFGroup2());
		view.SFButton1.setDisable(false);
		view.SFButton2.setDisable(false);
		
		int SFButtonPos = view.hBox2.getChildren().indexOf(view.startSFButton);
		view.hBox2.getChildren().remove(SFButtonPos);
		view.hBox2.getChildren().add(SFButtonPos, view.startFButton);
		view.startFButton.setDisable(true);
	}

	/**
	 * Gets Result from Quarter Final
	 */
	public void buttonQF1Pressed() {
		AddKOResultView view4 = new AddKOResultView();
		view4.start(model.quarterFinal1, view);
	}

	/**
	 * Gets Result from Quarter Final
	 */
	public void buttonQF2Pressed() {
		AddKOResultView view4 = new AddKOResultView();
		view4.start(model.quarterFinal2, view);
	}

	/**
	 * Gets Result from Quarter Final
	 */
	public void buttonQF3Pressed() {
		AddKOResultView view4 = new AddKOResultView();
		view4.start(model.quarterFinal3, view);
	}

	/**
	 * Gets Result from Quarter Final
	 */
	public void buttonQF4Pressed() {
		AddKOResultView view4 = new AddKOResultView();
		view4.start(model.quarterFinal4, view);
	}

	/**
	 * Puts Winner Teams into QF Tables
	 */
	public void startQuarterFinals() {
		model.setQuarterFinalPairings();
		view.tableKOList.get(0).setItems(model.getQFGroup1());
		view.tableKOList.get(1).setItems(model.getQFGroup2());
		view.tableKOList.get(2).setItems(model.getQFGroup3());
		view.tableKOList.get(3).setItems(model.getQFGroup4());

		int QFButton = view.hBox2.getChildren().indexOf(view.startQFButton);
		view.hBox2.getChildren().remove(QFButton);
		view.hBox2.getChildren().add(QFButton, view.startSFButton);
		view.startSFButton.setDisable(true);
		
		view.QFButton1.setDisable(false);
		view.QFButton2.setDisable(false);
		view.QFButton3.setDisable(false);
		view.QFButton4.setDisable(false);
	}
	
	/**
	 * When Semi Finals are finished, enable Start Finale button.
	 */
	public void enableStartFinaleButton() {
		view.startFButton.setDisable(false);
	}
	
	/**
	 * When Quarter Finals are finished, enable Start SF button.
	 */
	public void enableStartSFButton() {
		view.startSFButton.setDisable(false);
	}

	/**
	 * When preliminaries are finished, hide the Preliminaries Window and enable
	 * Start QF button.
	 */
	public void enableStartQFButton() {
		view.startQFButton.setDisable(false);
		view0.stop();
	}

	/**
	 * Sets up and initialises the Preliminaries Window.
	 */
	public void showPreliminaryGamesView() {
		view0.start();
	}

	/**
	 * just opens the stage with the Preliminaries Window.
	 */
	public void showPreliminaryGamesView2() {
		view0.start2();
	}

	/**
	 * Lets you add a team.
	 * 
	 * @throws Exception
	 */
	public void addTeam() throws Exception {
		AddMemberView view1 = new AddMemberView();
		view1.start(view);
		
	}

	/**
	 * Lets you remove a team.
	 * 
	 * @throws Exception
	 */
	public void removeTeam() throws Exception {
		RemoveMemberView view2 = new RemoveMemberView();
		view2.start(view);
	}

	/**
	 * Opens a windows with all registered teams.
	 */
	public void showAllTeamsView() {
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
	 * 
	 * @param teamlist
	 *            Must be an observable list.
	 */
	public void setTeamlist(TeamList teamlist) {
		this.teamlist = teamlist;
	}

	/**
	 * Initialises tournament, puts teams in groups and displays them.
	 */
	public void startTournament() {
		model.startTournament();
		view.getTable1().setItems(model.getGroup1());
		view.getTable2().setItems(model.getGroup2());
		view.getTable3().setItems(model.getGroup3());
		view.getTable4().setItems(model.getGroup4());

		int posOfAdd = view.hBox2.getChildren().indexOf(view.addTeamButton);
		view.hBox2.getChildren().remove(posOfAdd);
		view.hBox2.getChildren().add(posOfAdd, view.preliminaryGameButton);
		int posOfRemove = view.hBox2.getChildren().indexOf(view.removeTeamButton);
		view.hBox2.getChildren().remove(posOfRemove);

		int posOfstartTournament = view.hBox2.getChildren().indexOf(view.startTournamentButton);
		view.hBox2.getChildren().remove(posOfstartTournament);
		view.hBox2.getChildren().add(posOfstartTournament, view.startQFButton);
		view.startQFButton.setDisable(true);

		showPreliminaryGamesView();
	}
}
