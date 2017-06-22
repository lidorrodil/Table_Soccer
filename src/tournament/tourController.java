package tournament;

import userInterface.model;
import userInterface.view;

public class tourController {
	final private tourModel model;
	final private tourView view;
	
	public tourController(tourModel model, tourView view) throws Exception {
		this.model = model;
		this.view = view;
	}
}
