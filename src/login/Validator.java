package login;


import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import javafx.application.Application;
import javafx.stage.Stage;
import tournament.TournamentModel;
import tournament.TournamentView;
import userInterface.MVC;

public class Validator extends Application   {
    private Validator_View view;
    private Validator_Controller controller;
    private Validator_Model model;
    private TournamentView tView;
    private TournamentModel tModel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Validator_Model();
        view = new Validator_View(primaryStage, model);
        
        tModel = new TournamentModel();
        tView = new TournamentView(primaryStage, tModel);
        controller = new Validator_Controller(model, view);
        boolean flag;
        // Display the GUI after all initialization is complete
        
        view.start(); 
        tView.startTournament();
       
    }
    
   

    public static void main(String[] args) {
        launch(args);
        
    }
    
  

}
