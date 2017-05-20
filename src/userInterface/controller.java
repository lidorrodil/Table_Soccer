package userInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.sun.xml.internal.ws.spi.db.DatabindingProvider;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateStringConverter;
import sun.util.resources.LocaleData;

public class controller implements EventHandler<ActionEvent> {
	final private model model;
	final private view view;
	boolean flag = true;

	public controller(model model, view view) throws Exception {
		this.model = model;
		this.view = view;

		// Event handler for the button
		// view.button.disabledProperty();

		view.buttonSortName.setOnAction(event -> model.nameSort());
		view.buttonSortAge.setOnAction(event -> model.ageSort());
		view.buttonSortRank.setOnAction(event -> model.rankSort());
		view.btnRemove.setOnAction(e -> remove());
		view.btnEdit.setOnAction(e -> edit());
		view.aButton.setOnAction(e -> addMember());
		view.btnClean.setOnAction(e -> {
			view.firstName.setText("");
			view.familyName.setText("");
			view.checkInDatePicker.getEditor().clear();
			view.age.setText("");
			view.street.setText("");
			view.streetNum.setText("");
			view.postzip.setText("");
			view.city.setText("");
			cleanRankButton();
			view.yes.setSelected(false);
		});
		view.startTournament.setOnAction(e -> view.tournamentStart());

		// view.btnSearch.setOnAction(event -> showUser());
		view.btnSearch.setOnAction(e -> search());

		/*
		 * view.btnSearch.setOnAction(e -> { try (BufferedReader reader = new
		 * BufferedReader(new FileReader("file.txt"));){
		 * //view.lblResult.setText(""); //String line = reader.readLine();
		 * System.out.println("Step 0: "); searchMember search = new
		 * searchMember(); search.searchMember2(view.searchBoxByName.getText(),
		 * view.searchBoxByFamilyName.getText());
		 * 
		 * System.out.println("Step1 "); showUser();
		 * view.searchBoxByName.setText("");
		 * view.searchBoxByFamilyName.setText(""); } catch (Exception e1) {
		 * 
		 * e1.printStackTrace(); } });
		 */

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
	
	public int rankCheck(){
		if (view.one.isSelected())
			return 1;
		if (view.two.isSelected())
			return 2;
		if (view.three.isSelected())
			return 3;
		if (view.four.isSelected())
			return 4;
		if (view.five.isSelected())
			return 5;
		return 0;
	}
	
	public int paymentCheck(){
		if (view.yes.isSelected())
			return 1;
		return 0;	
	}
	
	public void cleanRankButton(){
		view.one.setSelected(false);
		view.two.setSelected(false);
		view.three.setSelected(false);
		view.four.setSelected(false);
		view.five.setSelected(false);
	}

	public void addMember() {
		System.out.println("STEP AddMember");

		try (FileWriter writer = new FileWriter(new File("./database/file.txt"), true);) {
			final DatePicker datePicker = new DatePicker(LocalDate.now());
			int age=0;
		    if (view.checkInDatePicker.getValue()!=null)
		    	age = datePicker.getValue().getYear()-view.checkInDatePicker.getValue().getYear();
			writer.write(view.firstName.getText());
			writer.write(System.getProperty("line.separator"));
			writer.write(view.familyName.getText());
			writer.write(System.getProperty("line.separator"));
			if (view.checkInDatePicker.getValue()!=null)
				//System.out.println("Birth :"+view.checkInDatePicker.getValue().toString());
				writer.write(view.checkInDatePicker.getValue().toString());
			else
				writer.write("0"); // if date doesn't insert so we put 0
			writer.write(System.getProperty("line.separator"));
			writer.write(String.valueOf(age));
			writer.write(System.getProperty("line.separator"));
			writer.write(view.street.getText());
			writer.write(System.getProperty("line.separator"));
			writer.write(view.streetNum.getText());
			writer.write(System.getProperty("line.separator"));
			writer.write(view.city.getText());
			writer.write(System.getProperty("line.separator"));
			writer.write(view.postzip.getText());
			writer.write(System.getProperty("line.separator"));
			writer.write(String.valueOf(rankCheck()));
			writer.write(System.getProperty("line.separator"));
			writer.write(String.valueOf(paymentCheck()));
			writer.write(System.getProperty("line.separator"));
			writer.close();

			view.copy.sortByAge();
			view.copy.sortByRank();
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		view.firstName.setText("");
		view.familyName.setText("");
		view.checkInDatePicker.getEditor().clear();
		view.age.setText("");
		view.street.setText("");
		view.streetNum.setText("");
		view.city.setText("");
		view.postzip.setText("");
		cleanRankButton();
		view.yes.setSelected(false);
	}

	@Override
	public void handle(ActionEvent event) {

	}
	
	public LocalDate returnDate(String date){
		final DatePicker datePicker = new DatePicker();
		String year = date.substring(0,4);
		String month = date.substring(6,7);
		String day = date.substring(9,10);
		System.out.println("Second: "+year);
		//System.out.println("STEp : "+year);
		LocalDate memeberDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month)
				, Integer.parseInt(day));
		//System.out.println("STEp :as "+Integer.parseInt(year));
		//datePicker.setValue(date3);
		
		return memeberDate;
	}
	
	public void setRank(String rank){
		if (rank.equals("1"))
			view.one.setSelected(true);
		if (rank.equals("2"))
			view.two.setSelected(true);
		if (rank.equals("3"))
			view.three.setSelected(true);
		if (rank.equals("4"))
			view.four.setSelected(true);
		if (rank.equals("5"))
			view.five.setSelected(true);
	}

	private void showUser() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./database/resultSearch.txt"));
			try {
				String tmp = reader.readLine();
				view.firstName.setText(tmp);
				view.familyName.setText(reader.readLine());
				view.checkInDatePicker.setValue(returnDate(reader.readLine()));
				view.age.setText(reader.readLine());
				view.street.setText(reader.readLine());
				view.streetNum.setText(reader.readLine());
				view.city.setText(reader.readLine());
				view.postzip.setText(reader.readLine());
				setRank(reader.readLine());
				//if (reader.readLine())
				//view.rank.setText(reader.readLine());
				
				if (reader.readLine().equals("1"))
					view.yes.setSelected(true);
				//view.payment.setText(reader.readLine());
			}
			// }
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search() {
		try (BufferedReader reader = new BufferedReader(new FileReader("./database/file.txt"));) {
			searchMember search = new searchMember();
			search.searchMember2(view.searchBoxByName.getText(), view.searchBoxByFamilyName.getText());
			showUser();
			view.searchBoxByName.setText("");
			view.searchBoxByFamilyName.setText("");
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	public void delete(String fname, String familyName) {
		try {
			// BufferedReader resultSearch = new BufferedReader(new
			// FileReader("resultSearch.txt"));
			FileWriter write_src = new FileWriter("./database/new_file_after_remove.txt");
			BufferedReader read_src = new BufferedReader(new FileReader("./database/file.txt"));

			// String fName_to_remove = resultSearch.readLine();
			// String familyName_to_remove = resultSearch.readLine();

			// String fName_to_remove =
			// view.searchBoxByName.getText().toString();
			// String familyName_to_remove =
			// view.searchBoxByFamilyName.getText().toString();

			String fName_to_remove = fname;
			String familyName_to_remove = familyName;
			System.out.println("CHeckkkk: " + fName_to_remove + " " + familyName_to_remove);
			String fName_src_file = read_src.readLine();
			String familyName_src_file = read_src.readLine();

			System.out.println("STEP 0");
			while ((fName_src_file != null)
					&& (!fName_to_remove.equals(fName_src_file) || !familyName_to_remove.equals(familyName_src_file))) {
				System.out.println("STEP we do: " + fName_src_file);
				write_src.write(fName_src_file);
				write_src.write(System.getProperty("line.separator"));

				if (fName_src_file != null) {
					System.out.println("STEP we do: " + familyName_src_file);
					write_src.write(familyName_src_file);
					write_src.write(System.getProperty("line.separator"));
				}
				// tmp = src_file;

				fName_src_file = read_src.readLine();
				familyName_src_file = read_src.readLine();

				// System.out.println("END 1: "+tmp);
			}

			if (fName_src_file != null) {
				for (int i = 0; i < 8; i++) {
					System.out.println("Report: " + fName_src_file + " " + familyName_src_file);
					fName_src_file = read_src.readLine();
				}
			}
			System.out.println("There is left one more: " + fName_src_file + " " + familyName_src_file);
			System.out.println("---------------------");
			fName_src_file = read_src.readLine();
			while (fName_src_file != null) {
				write_src.write(fName_src_file);
				write_src.write(System.getProperty("line.separator"));
				fName_src_file = read_src.readLine();
			}
			write_src.close();
			read_src.close();
			// resultSearch.close();
			System.out.println("STEP 2");
			BufferedReader read = new BufferedReader(new FileReader("./database/new_file_after_remove.txt"));
			FileWriter write = new FileWriter("./database/file.txt");
			String line = "";
			while ((line = read.readLine()) != null) {
				write.write(line);
				write.write(System.getProperty("line.separator"));
			}
			System.out.println("STEP 3");

			System.out.println("STEP 4");
			read.close();
			write.close();
			File file = new File("./database/new_file_after_remove.txt");
			// file.createNewFile();
			file.deleteOnExit();
			System.out.println("STEP 5");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remove() {
		// search();
		delete(view.searchBoxByName.getText().toString(), view.searchBoxByFamilyName.getText().toString());
		view.searchBoxByName.setText("");
		view.searchBoxByFamilyName.setText("");
		try {
			view.copy.sortByAge();
			view.copy.sortByRank();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		view.firstName.setText("");
		view.familyName.setText("");
		view.birthday.setText("");
		view.age.setText("");
		view.street.setText("");
		view.streetNum.setText("");
		view.city.setText("");
		view.postzip.setText("");
		view.rank.setText("");
		view.payment.setText("");

	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public void edit() {
		System.out.println("STEP 1");
		try {
			BufferedReader resultSearch = new BufferedReader(new FileReader("./database/resultSearch.txt"));
			String name = resultSearch.readLine();
			String family = resultSearch.readLine();
			// delete(name, family);
			System.out.println("Result Edit: " + name + " " + family);
			addMember();
			delete(name, family);
			/*view.firstName.setText("");
			view.familyName.setText("");
			view.birthday.setText("");
			view.age.setText("");
			view.street.setText("");
			view.streetNum.setText("");
			view.city.setText("");
			view.postzip.setText("");
			view.rank.setText("");
			view.payment.setText("");*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
