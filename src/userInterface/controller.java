package userInterface;


import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class controller implements EventHandler<ActionEvent> {
	final private model model;
	final private view view;

	protected controller(model model, view view) throws IOException {
		this.model = model;
		this.view = view;

		// Event handler for the button
		view.button.setOnAction(event -> model.addNewElement());

		// Event handler for the model's ObservableList requires a
		// ListChangeListener.
		// To make generics happy, we have to cast our lambda: what kind of data
		// do we have?
		// Note: May contain multiple changes - hence, the loop!
		model.getElements().addListener((ListChangeListener<Member>) c -> {
			while (c.next()) {
				view.tableView.scrollTo(c.getFrom());
			}
		});

	}

	@Override
	public void handle(ActionEvent event) {

	}
}
