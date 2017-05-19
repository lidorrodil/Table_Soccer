package ch.fhnw.richards.lecture07.email_validator;

import userInterface.MVC;

public class EmailValidator_Controller {
	final private EmailValidator_Model model;
	final private EmailValidator_View view;

	protected EmailValidator_Controller(EmailValidator_Model model, EmailValidator_View view) {
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
