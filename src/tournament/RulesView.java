package tournament;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Small Window that shows the basic rules of the tournament.
 * @author joelf
 *
 */

public class RulesView {
	protected Stage stage;
	protected Button cancelButton = new Button("Close");
	protected Text message1;
	protected Text message2;
	protected VBox root;

	public void start(){
		stage = new Stage();
		stage.setTitle("The Rules");
		
		root = new VBox();
		
		root.setPadding(new Insets(10));
		root.setSpacing(10);
		
		String line1 = "1. At maximum, 28 Teams can participate. \n";
		String line2 = "2. All Preliminary Games end with 7 Goals Scored. A 6:6 counts as a draw. \n";
		String line3 = "3. The first and the second team of each group advance to the quarter finals. \n";
		String line4 = "4. In the knockout phase, whichever teams scores 7 times first, wins the set. \n";
		String line5 = "5. Whichever team wins 3 sets first, wins the round. \n";
		String line6 = "6. Have Fun!";
		String line7 = "";
		String line8 = "";
		String line9 = "";
		String line10 = "";
		String line11= "";
		String line12 = "";
		String line13 = "";
		String line14 = "";
		String line15 = "";
		
		message1 = new Text("The Rules are as follows:");
		message1.setStrokeWidth(2);
		message2 = new Text(line1+line2+line3+line4+line5+line6);
		
		message1.prefWidth(500);
		message1.maxWidth(500);
		message2.prefWidth(500);
		message2.maxWidth(500);
		cancelButton.setPrefWidth(500);
		cancelButton.setMaxWidth(500);
		
		cancelButton.setOnAction(c->stop());
		
		root.getChildren().add(message1);
		root.getChildren().add(message2);
		root.getChildren().add(cancelButton);
		Scene scene = new Scene(root);
		root.setStyle(
				"-fx-background-color: radial-gradient(focus-angle 45deg, focus-distance 50%, center 25% 25%, radius 60%, reflect, gray, darkgray 30%, black)");
		scene.getStylesheets().add(getClass().getResource("TourView.css").toExternalForm());

		stage.setScene(scene);
		stage.show();
	}
	
	public void stop(){
		stage.hide();
	}
}
