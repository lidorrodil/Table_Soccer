package userInterface;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class controller implements EventHandler<ActionEvent> {
	final private model model;
	final private view view;
	boolean flag=true;
	protected controller(model model, view view) throws Exception {
		this.model = model;
		this.view = view;
		
		// Event handler for the button
		//view.button.disabledProperty();
		
		view.buttonSortName.setOnAction(event -> model.nameSort());
		view.buttonSortAge.setOnAction(event -> model.ageSort());
		view.buttonSortRank.setOnAction(event -> model.rankSort());
	
		view.btnSearch.setOnAction(event -> showUser());
		
		
		view.btnSearch.setOnAction(e -> {        	
            try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"));){
            	//view.lblResult.setText("");
            	//String line = reader.readLine();
            	System.out.println("Step 0: ");
            	searchMember search = new searchMember();
            	search.searchMember2(view.searchBoxByName.getText(), view.searchBoxByFamilyName.getText());
            	
            	System.out.println("Step1 ");
            	showUser();
            	view.searchBoxByName.setText("");
            	view.searchBoxByFamilyName.setText("");
            	//PrintWriter writer = new PrintWriter("resultSearch.txt");
            	//writer.print("");
            	//writer.close();
            	//view.lblResult.setText("");
			} catch (Exception e1) {
				
				e1.printStackTrace();
			} 
        });
		
		
		// Event handler for the model's ObservableList requires a
		// ListChangeListener.
		// To make generics happy, we have to cast our lambda: what kind of data
		// do we have?
		// Note: May contain multiple changes - hence, the loop!
		model.getElements().addListener((ListChangeListener<Person>) c -> {
			while (c.next()) {
				view.tableView.scrollTo(c.getFrom());
			}
		});

	}

	@Override
	public void handle(ActionEvent event) {

	}
	
	private void showUser() {
		try{
		BufferedReader reader = new BufferedReader(new FileReader("resultSearch.txt"));
		try{
			String tmp = reader.readLine();
			//view.lblResultName.setText(tmp);
			view.firstName.setText(tmp);
			view.familyName.setText(reader.readLine());
			view.age.setText(reader.readLine());
			view.address.setText(reader.readLine());
			view.rank.setText(reader.readLine());
			view.payment.setText(reader.readLine());
		}
		//}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
