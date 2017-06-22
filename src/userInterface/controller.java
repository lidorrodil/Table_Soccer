package userInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import FileCopy.FileCopy;
import FileCopy.FileCopyModel;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import sun.security.action.OpenFileInputStreamAction;
import sun.util.resources.LocaleData;
import tournament.tourController;
import tournament.tourModel;
import tournament.tourView;

public class controller implements EventHandler<ActionEvent> {
	final private model model;
	final private view view;
	boolean flag = true;
	String nameOfImg = " ";

	public controller(model model, view view) throws Exception {
		this.model = model;
		this.view = view;

		// Event handler for the button
		view.buttonSortName.setOnAction(event -> model.nameSort());
		view.buttonSortAge.setOnAction(event -> model.ageSort());
		view.buttonSortRank.setOnAction(event -> model.rankSort());
		view.btnRemove.setOnAction(e -> remove());
		view.btnEdit.setOnAction(e -> edit());
		view.aButton.setOnAction(e -> addMember());
		view.btnClean.setOnAction(e -> clean());
		view.btnTournamet.setOnAction(e -> createTournament());
		view.btnSearch.setOnAction(e -> search());
		model.getElements().addListener((ListChangeListener<Person>) c -> {
			while (c.next()) {
				view.tableView.scrollTo(c.getFrom());
			}
		});
		
		view.upload.setOnAction(e -> UploadImg());

	}
	
	public void clean(){
		view.firstName.setText("");
		view.familyName.setText("");
		view.checkInDatePicker.setValue(null);
		view.age.setText("");
		view.street.setText("");
		view.streetNum.setText("");
		view.postzip.setText("");
		view.city.setText("");
		cleanRankButton();
		view.yes.setSelected(false);
		view.imgView.setVisible(false);
	}

	public void UploadImg() {
		FileChooser upfile = new FileChooser();
		
		upfile.setTitle("Upload an image");
		
		File file = upfile.showOpenDialog(view.stage);

		FileCopyModel copy = new FileCopyModel();
		copy.FileCopy(file.getPath(), "./database/"+file.getName());
		
		String path = "./database/"+file.getName();
		URL toUrl;
		try {
			toUrl = new File(path).toURI().toURL();
			Image img = new Image(toUrl.toString());
			view.imgView.setImage(img);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nameOfImg = file.getName();
		
		
	}
	
	public void showImg(String name){
		String path = "./database/"+name;
		URL toUrl;
		try {
			toUrl = new File(path).toURI().toURL();
			Image img = new Image(toUrl.toString());
			view.imgView.setVisible(true);
			view.imgView.setImage(img);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addMember() {
		try (FileWriter writer = new FileWriter(new File("./database/file.txt"), true);) {
			final DatePicker datePicker = new DatePicker(LocalDate.now());
			int age = 0;
			if (view.checkInDatePicker.getValue() != null)
				age = datePicker.getValue().getYear() - view.checkInDatePicker.getValue().getYear();
			writer.write(view.firstName.getText());
			writer.write(System.getProperty("line.separator"));
			writer.write(view.familyName.getText());
			writer.write(System.getProperty("line.separator"));
			if (view.checkInDatePicker.getValue() != null)
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
			writer.write(nameOfImg);
			writer.write(System.getProperty("line.separator"));
			writer.close();

			view.copy.sortByAge();
			view.copy.sortByRank();
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		view.firstName.setText("");
		view.familyName.setText("");
		view.checkInDatePicker.setValue(null);
		view.age.setText("");
		view.street.setText("");
		view.streetNum.setText("");
		view.city.setText("");
		view.postzip.setText("");
		cleanRankButton();
		view.yes.setSelected(false);
		view.imgView.setVisible(false);
	}

	@Override
	public void handle(ActionEvent event) {

	}

	private void showUser() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("./database/resultSearch.txt"));
			try {
//				String date = "";
				String tmp = reader.readLine();
				final JPanel panel = new JPanel();
				if (tmp == null)
					JOptionPane.showMessageDialog(panel, "Could not find member", "Info",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					view.firstName.setText(tmp);
					view.familyName.setText(reader.readLine());
					tmp = reader.readLine();
					if (!tmp.equals("0"))
						view.checkInDatePicker.setValue(returnDate(tmp));
					view.age.setText(reader.readLine());
					view.street.setText(reader.readLine());
					view.streetNum.setText(reader.readLine());
					view.city.setText(reader.readLine());
					view.postzip.setText(reader.readLine());
					setRank(reader.readLine());
					
					if (reader.readLine().equals("1"))
						view.yes.setSelected(true);
					//System.out.println(reader.readLine());
					showImg(reader.readLine());
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

	public void search() {
		try  {
			clean();
			searchMember search = new searchMember();
			search.searchMemberByName(view.searchBoxByName.getText(), view.searchBoxByFamilyName.getText());
			showUser();
			view.searchBoxByName.setText("");
			view.searchBoxByFamilyName.setText("");
		} catch (Exception e1) {

			e1.printStackTrace();
		}

	}

	public String delete(String fname, String familyName) {
		String photo_to_rmv = "";
		boolean flag = true;
		try {
			FileWriter write_src = new FileWriter("./database/new_file_after_remove.txt");
			BufferedReader read_src = new BufferedReader(new FileReader("./database/file.txt"));
			String fName_to_remove = fname;
			String familyName_to_remove = familyName;
			String fName_src_file = read_src.readLine();
			String familyName_src_file = read_src.readLine();

			System.out.println("STEP 0");
			while ((fName_src_file != null)
					&& (!fName_to_remove.equals(fName_src_file) || !familyName_to_remove.equals(familyName_src_file))) {
				write_src.write(fName_src_file);
				write_src.write(System.getProperty("line.separator"));
				write_src.write(familyName_src_file);
				write_src.write(System.getProperty("line.separator"));
					for (int i = 0; i < 9; i++) {
						fName_src_file = read_src.readLine();
						write_src.write(fName_src_file);
						write_src.write(System.getProperty("line.separator"));
					}
				fName_src_file = read_src.readLine();
				familyName_src_file = read_src.readLine();
			}


			// we found the member	
			if (fName_src_file==null){
				flag = false;
				return null;
			}
			for (int i = 0; i < 8; i++) {
				String s =read_src.readLine();
				System.out.println(s);
			}
			photo_to_rmv = read_src.readLine();
			// we left more members to fill
			fName_src_file = read_src.readLine();
			while (fName_src_file != null) {
				write_src.write(fName_src_file);
				write_src.write(System.getProperty("line.separator"));
				fName_src_file = read_src.readLine();
			}
			write_src.close();
			read_src.close();
			BufferedReader read = new BufferedReader(new FileReader("./database/new_file_after_remove.txt"));
			FileWriter write = new FileWriter("./database/file.txt");
			String line = "";
			while ((line = read.readLine()) != null) {
				write.write(line);
				write.write(System.getProperty("line.separator"));
			}
			read.close();
			write.close();
			File file = new File("./database/new_file_after_remove.txt");
			file.deleteOnExit();
			return photo_to_rmv;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return photo_to_rmv;
	}

	public void remove() {
		String photo = "";
		if (!(view.searchBoxByName.getText().toString().equals("")) && (!view.searchBoxByFamilyName.getText().toString().equals("")))
			photo = delete(view.searchBoxByName.getText().toString(), view.searchBoxByFamilyName.getText().toString());
		if (photo==null || photo==""){
			final JPanel panel = new JPanel();
		    JOptionPane.showMessageDialog(panel, "Could not find member", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else{	
		String path = "./database/"+photo;
		File file = new File(path);
		file.delete();
		view.searchBoxByName.setText("");
		view.searchBoxByFamilyName.setText("");
		try {
			view.copy.sortByAge();
			view.copy.sortByRank();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

		view.firstName.setText("");
		view.familyName.setText("");
		view.checkInDatePicker.setValue(null);
		view.age.setText("");
		view.street.setText("");
		view.streetNum.setText("");
		view.city.setText("");
		view.postzip.setText("");
		cleanRankButton();
		view.yes.setSelected(false);
		view.imgView.setVisible(false);
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
			addMember();
			delete(name, family);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int rankCheck() {
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

	public int paymentCheck() {
		if (view.yes.isSelected())
			return 1;
		return 0;
	}

	public void cleanRankButton() {
		view.one.setSelected(false);
		view.two.setSelected(false);
		view.three.setSelected(false);
		view.four.setSelected(false);
		view.five.setSelected(false);
	}

	public LocalDate returnDate(String date) {
		final DatePicker datePicker = new DatePicker();
		System.out.println("Date: "+date);
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String day = date.substring(8, 10);
		LocalDate memeberDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		System.out.println("mem Date: "+memeberDate);
		return memeberDate;
	}

	public void setRank(String rank) {
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

	public void createTournament() {

		tourModel model = new tourModel();
		tourView view = new tourView(new Stage(), model);
		try {
			tourController controller = new tourController(model, view);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		view.start();
	}

}
