package userInterface;
/*
 * the file with the data includes a tag which look like:
 * First Name, Family Name, Age, Address, Rank, Payment
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class model {
	private final ObservableList<Person> elements = FXCollections.observableArrayList();
	// private final ObservableList<SuperNumber> elements = FXCollections.obser
	boolean flag = true;

	public void nameSort() {
		elements.clear();

		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/file.txt"));
			try {
				String line;
				while ((line = br.readLine()) != null) {
					elements.add(new Person(line, br.readLine(), br.readLine(), br.readLine(), br.readLine(),
							br.readLine()));
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ageSort() {
		elements.clear();
		try (BufferedReader br = new BufferedReader(new FileReader("./database/sortAge.txt"));) {

			String line = br.readLine();
			try {
				while ((line) != null) {
					elements.add(new Person(line, br.readLine(), br.readLine(), br.readLine(), br.readLine(),
							br.readLine()));
					line = br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void rankSort() {
		elements.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader("./database/sortRank.txt"));
			try {
				String line;
				while ((line = br.readLine()) != null) {
					elements.add(new Person(line, br.readLine(), br.readLine(), br.readLine(), br.readLine(),
							br.readLine()));
				}
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remove() {
		

	}

	public void edit() {

	}
	
	public void search(){
		
	}

	public String name() {
		Label label1 = new Label("Name:");
		TextField textField = new TextField();
		HBox hb = new HBox();
		hb.getChildren().addAll(label1, textField);
		hb.setSpacing(10);

		Scene scene = new Scene(hb);
		// Scene scene = new Scene(table.func());
		scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());

		return null;
	}

	// getters and setters
	public ObservableList<Person> getElements() {
		return elements;
	}
}
