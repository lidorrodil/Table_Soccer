package tournament;

import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.controller;
import userInterface.model;
import userInterface.view;

public class TestMVC extends Application{

	// private static final boolean EmailValidator = false;
	private TourModel model;
	private TourView view;
	private TourController controller;

	public static void main(String[] args) {
		launch();
	}

	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs the primaryStage created by JavaFX.
	 * 
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		// Validator vf = new Validator();
		// vf.start(primaryStage);

		model = new TourModel();
		view = new TourView(primaryStage, model);
		controller = new TourController(model, view);

		view.start();

	}

	/**
	 * The stop method is the opposite of the start method. It provides an
	 * opportunity to close down the program gracefully, when the program has
	 * been closed.
	 */
	public void stop() {
		if (view != null)
			view.stop();
	}

}
