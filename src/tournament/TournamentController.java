package tournament;

public class TournamentController {

        final private TournamentModel model;
        final private TournamentView view;
        
        protected TournamentController(TournamentModel model, TournamentView view){
            this.model = model;
            this.view = view;
        }
}
