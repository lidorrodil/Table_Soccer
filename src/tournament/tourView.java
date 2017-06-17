package tournament;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;

public class tourView {
	private  tourModel model;
	private Stage stage;
	
	public tourView(Stage stage, tourModel model) {
		this.stage = stage;
		this.model = model;
		
		BorderPane Border = new BorderPane();
		

		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(15);


		Scene scene = new Scene(Border);

		//scene.getStylesheets().add(getClass().getResource("Design.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Tournament");
	
		
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
}
