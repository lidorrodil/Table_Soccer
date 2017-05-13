package login;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Validator_View {
    private Validator_Model model;
    private Stage stage;

    String username = "admin";
	String password = "admin";
    protected TextField name;
    protected TextField pass;
    Button login = new Button("Login");
    boolean flag = false;

    public Validator_View(Stage stage, Validator_Model model) {
        this.stage = stage;
        this.model = model;
        
        stage.setTitle("Access");
        
        GridPane root = new GridPane();
        name = new TextField();
        pass = new TextField();
        root.add(name, 0, 0);
        root.add(pass, 0, 1);
        root.add(login, 0, 2);
        
        login.setOnAction(e -> {
        	if (name.getText().equals(username) && pass.getText().equals(password)){
        		System.out.println("Succeed");
        		flag = true;
        		passOrFail();
        		stop();
        	}
        	else
        		System.out.println("Fail");
        	
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
    public boolean passOrFail(){
    	return flag;
    }
    
    public void start() {
        stage.show();
        //passOrFail();
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
}
