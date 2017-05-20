package accessPoint;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.plaf.synth.SynthRootPaneUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jdk.nashorn.tools.Shell;
import userInterface.controller;
import userInterface.model;
import userInterface.view;

public class accessView {
    private accessModel model;
    private Stage stage;

    protected TextField name;
    protected TextField pass;
    Button login = new Button("Login");

    protected accessView(Stage stage, accessModel model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("Email Validator");
        
        GridPane root = new GridPane();
       // txtEmail = new TextField();
        name = new TextField();
		pass = new TextField();
	
        root.add(name, 0, 0);
        root.add(pass, 0, 1);
        root.add(login, 0, 2);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        
    }
    
    public void start() {
        stage.show();
    }
    
    /**
     * Stopping the view - just make it invisible
     */
    public void stop() {
        stage.hide();
    }
    
    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return stage;
    }

	public void Appstart() throws Exception {
		
		
		model model = new model();
		view view = new view(new Stage(), model);
		controller controller = new controller(model, view);
	
		view.start();		
	}
}
