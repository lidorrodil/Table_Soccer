package accessPoint;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.plaf.synth.SynthRootPaneUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.tools.Shell;
import userInterface.controller;
import userInterface.model;
import userInterface.view;

public class accessView {
    private accessModel model;
    private Stage stage;

    protected TextField name;
    Button btn = new Button("Sign in");
    PasswordField pwBox;
    final Text actiontarget;

    protected accessView(Stage stage, accessModel model) {
        this.stage = stage;
        this.model = model;
        
        GridPane root = new GridPane();
        name = new TextField();
	    pwBox = new PasswordField();
	    
	   actiontarget = new Text();
        root.add(actiontarget, 1, 6);
	    
        HBox hbBtn = new HBox(10);
		
        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        actiontarget.setId("actiontarget");
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
       // stage.setTitle("Email Validator");
        root.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        root.add(userName, 0, 1);
        root.add(name, 1, 1);
        
        Label pw = new Label("Password:");
        root.add(pw, 0, 2);
        root.add(pwBox, 1, 2);
        

        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        root.add(hbBtn, 1, 4);

        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("Welcome");
    
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        //scene.getStylesheets().add(getClass().getResourceAsStream(name))
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("layouts.css").toExternalForm());
        
        
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
