package login;


import java.io.Console;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Validator extends Application {
    private Validator_View view;
    private Validator_Controller controller;
    private Validator_Model model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new Validator_Model();
        view = new Validator_View(primaryStage, model);
        controller = new Validator_Controller(model, view);

        // Display the GUI after all initialization is complete
        
        
        view.start();
        view.passOrFail();
    }

    public static void main(String[] args) {
        launch(args);
        
    }

}
