package accessPoint;

import userInterface.MVC;

public class accessController {
	final private accessModel model;
	final private accessView view;

	protected accessController(accessModel model, accessView view) {
		this.model = model;
		this.view = view;

		view.login.setOnAction(e -> {
			if (view.name.getText().equals("admin") && view.pass.getText().equals("admin")) {
				System.out.println("Succeed");
				
				// flag = true;
				// stop();
				view.stop();
				try {
					view.Appstart();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else
				System.out.println("Fail");
		});

	}

}
