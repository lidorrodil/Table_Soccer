package accessPoint;

import javafx.scene.paint.Color;
import userInterface.MVC;

public class accessController {
	final private accessModel model;
	final private accessView view;

	protected accessController(accessModel model, accessView view) {
		this.model = model;
		this.view = view;

		view.btn.setOnAction(e -> {
			if (view.name.getText().equals("admin") && view.pwBox.getText().equals("admin")) {
				System.out.println("Succeed");
				view.stop();
				try {
					view.Appstart();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {

				view.actiontarget.setFill(Color.FIREBRICK);
				view.actiontarget.setText("Sign in failed");
			}
		});

	}

}
