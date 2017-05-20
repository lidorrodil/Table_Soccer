package tournament;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TournamentView {
    private TournamentModel model;
    private Stage stage;
    
    public TournamentView(Stage stage, TournamentModel model){
        this.model = model;
        this.stage = stage;

        stage.setTitle("Tournament");
        VBox root = new VBox();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
    }
    
    public void startTournament(){
        stage.show();
    }

    public void stopTournament(){
        stage.hide();
    }
    
    
}
