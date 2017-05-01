package userInterface;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class model {
	private final ObservableList<Member> elements = FXCollections.observableArrayList();
	//private final ObservableList<SuperNumber> elements = FXCollections.obser

	public void addNewElement() {
		//elements.add(new SuperNumber(elements.size()));
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("file.txt"));
			try {
				String name = br.readLine();
				String familyName = br.readLine();
				elements.add(new Member(name,familyName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//elements.add(new SuperNumber("hh"));
	}
	
	public String name(){
		Label label1 = new Label("Name:");
		TextField textField = new TextField ();
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);
		
		Scene scene = new Scene(hb);
		// Scene scene = new Scene(table.func());
		scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
	
		
		return null;
	}
	

	// getters and setters
	public ObservableList<Member> getElements() {
		return elements;
	}
}
