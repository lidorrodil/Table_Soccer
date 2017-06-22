package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tournament.TourModel;
import tournament.TourController;
import tournament.TourModel;
import tournament.TourView;


public class view implements ActionListener {
	final private model model;
	final Stage stage;

	// Define the fields in the GUI
	protected TextField txtCalc;
	protected TableView<Person> tableView;
	protected Button buttonSortName = new Button("Show Members");
	protected Button buttonSortAge = new Button("Sort By: Age");
	protected Button buttonSortRank = new Button("Sort By: Rank");
	protected Button btnTournamet = new Button("Tournament");

	Button aButton = new Button("Add Member");
	ImageView imgView;
	

	protected Button btnSearch = new Button("Search");
	protected Button btnClean = new Button("Clean Fields");
	TextField searchBoxByName = new TextField();
	TextField searchBoxByFamilyName = new TextField();
	TextField firstName = new TextField();
	TextField familyName = new TextField();
	TextField age = new TextField();
	TextField birthday = new TextField();
	TextField city = new TextField();
	TextField postzip = new TextField();
	TextField street = new TextField();
	TextField streetNum = new TextField();
	TextField rank = new TextField();
	TextField payment = new TextField();
	copyFiles copy = new copyFiles();

	String username = "admin";
	String password = "admin";
	protected TextField name;
	protected TextField pass;
	Button login = new Button("Login");
	boolean flag = false;
	DatePicker checkInDatePicker;

	RadioButton one, two, three, four, five;
	ToggleGroup group;
	Label englishLabel;
	HBox rankHbox, paymentGridPane;
	CheckBox yes, no;

	protected Button btnEdit = new Button("Edit");
	protected Button btnRemove = new Button("Remove");
	protected Button upload = new Button("Upload Image");

	public view(Stage stage, model model) throws IOException, URISyntaxException {
		this.stage = stage;
		this.model = model;
		
		// define checkbox for payment
		paymentGridPane = new HBox();
		paymentGridPane.setAlignment(Pos.CENTER);
		paymentGridPane.setPrefSize(40, 40);
		yes = new CheckBox();
		paymentGridPane.getChildren().add(yes);

		// define radiobox for rank
		rankHbox = new HBox();
		rankHbox.setPrefSize(40, 40);
		rankHbox.setAlignment(Pos.CENTER);
		group = new ToggleGroup();
		one = new RadioButton(" ");
		one.setToggleGroup(group);
		rankHbox.getChildren().add(one);
		two = new RadioButton(" ");
		two.setToggleGroup(group);
		rankHbox.getChildren().add(two);
		three = new RadioButton(" ");
		three.setToggleGroup(group);
		rankHbox.getChildren().add(three);
		four = new RadioButton(" ");
		four.setToggleGroup(group);
		rankHbox.getChildren().add(four);
		five = new RadioButton(" ");
		five.setToggleGroup(group);
		rankHbox.getChildren().add(five);
		rankHbox.setId("hbox-custom");

		checkInDatePicker = new DatePicker();

		// Initialize TableView
		TableView<Person> tableView = createTableView();
		BorderPane Border = new BorderPane();
		HBox root = new HBox();

		GridPane basicInfo = new GridPane();
		GridPane personalData = new GridPane();

		personalData.add(new Label("First Name:"), 0, 2);
		personalData.add(firstName, 1, 2);

		personalData.add(new Label("Family Name:"), 0, 3);

		personalData.add(familyName, 1, 3);

		personalData.add(new Label("Birthday:"), 0, 4);

		//personalData.add(birthday, 1, 4);
		personalData.add(checkInDatePicker, 1, 4);
		

		personalData.add(new Label("Age:"), 0, 5);

		personalData.add(age, 1, 5);
		age.setEditable(false);

		personalData.add(new Label("Street:"), 0, 6);

		personalData.add(street, 1, 6);
		personalData.add(streetNum, 2, 6);
		streetNum.setId("smallBox");

		personalData.add(new Label("City:"), 0, 7);

		personalData.add(city, 1, 7);
		personalData.add(postzip, 2, 7);
		postzip.setId("smallBox");

		personalData.add(new Label("Rank:"), 0, 8);

		personalData.add(rankHbox, 1, 8);

		personalData.add(new Label("Payment:"), 0, 9);

		personalData.add(paymentGridPane, 1, 9);

		personalData.add(new Label("Image: "), 0, 10);
		
		personalData.add(upload, 1, 10);

		aButton.setMinWidth(90);
		btnEdit.setMinWidth(40);
		btnClean.setMinWidth(80);
		btnTournamet.setMinWidth(100);
		
		HBox lowerLine = new HBox();
		lowerLine.getChildren().addAll(aButton,btnEdit,btnClean,btnTournamet);
		lowerLine.setPadding(new Insets(7));
		lowerLine.setSpacing(5);
		//personalData.add(line, 0, 11);
		
		btnTournamet.setId("round-green");
		btnSearch.setId("rich-blue");
		btnClean.setId("round-green");
		aButton.setId("round-green");
		btnEdit.setId("round-green");
		btnRemove.setId("round-red");
		
		personalData.add(new Label(), 1, 12);

		GridPane searchInfo = new GridPane();
		HBox upperSearchLine = new HBox();
		upperSearchLine.getChildren().addAll(btnSearch,btnRemove);
		upperSearchLine.setPadding(new Insets(5));
		upperSearchLine.setSpacing(5);
		searchInfo.add(upperSearchLine, 0, 0);
		searchInfo.add(new HBox(new Label()), 0, 1);
		searchInfo.add(new Label("First Name:"), 0, 2);
		searchInfo.add(new Label("Family Name:"), 0, 3);
		searchInfo.add(searchBoxByName, 1, 2);
		searchInfo.add(searchBoxByFamilyName, 1, 3);
		

		GridPane centerInfo = new GridPane();

		Label space = new Label("");
		space.setMinWidth(300);

		Label space2 = new Label("");
		space2.setMinWidth(100);
		personalData.add(space2, 1, 0);	
		centerInfo.add(new HBox(new Label()), 0, 0);
		centerInfo.add(searchInfo, 0, 1);
		
		//Image img2 = new Image("C:\\Users\\lidorett\\git\\Table_Soccer\\20.jpg");
		//FileChooser upfile = new FileChooser();
		//upfile.showOpenDialog(null);
		
		imgView = new ImageView();
		//imgView.setImage(img2);
		
		centerInfo.add(imgView, 0, 3);
		
		imgView.setFitHeight(80);
		imgView.setFitWidth(80);
		
		//centerInfo.add(new HBox(new Label()), 0, 2);
		//centerInfo.add(new HBox(new Label()), 0, 3);
		//centerInfo.add(new HBox(new Label()), 0, 4);
		//centerInfo.add(new HBox(new Label()), 0, 3);
		//centerInfo.add(new HBox(new Label()), 0, 4);
		//centerInfo.add(new HBox(new Label()), 0, 3);
		centerInfo.add(new HBox(new Label()), 0, 4);
		centerInfo.add(personalData, 0, 5);
		centerInfo.add(lowerLine, 0, 6);
		
		centerInfo.setMinSize(200, 300);

		// Layout root pane
		VBox Vtable = new VBox();
		Vtable.setPadding(new Insets(10)); // around edge of VBox
		Vtable.setSpacing(5); // between elements
		VBox.setVgrow(tableView, Priority.ALWAYS); // Vertical resize goes to
													// the table
		Vtable.getChildren().addAll(tableView, buttonSortName, buttonSortAge, buttonSortRank);
		Vtable.setMaxWidth(182);
		buttonSortName.setId("vtable");
		buttonSortAge.setId("vtable");
		buttonSortRank.setId("vtable");
		
		
		Border.setLeft(Vtable);

		Border.setCenter(centerInfo);
		Border.setRight(space);
		Border.setId("border");

		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(15);
		basicInfo.getColumnConstraints().addAll(cc, cc, cc, cc);
		personalData.getColumnConstraints().addAll(cc, cc, cc, cc);

		Scene scene = new Scene(Border);
		

		tableView.getStylesheets().add(getClass().getResource("table.css").toExternalForm());

		scene.getStylesheets().add(getClass().getResource("Design.css").toExternalForm());
		stage.setHeight(600);
		stage.setScene(scene);
		stage.setTitle("Table Soccer");


	}

	private TableView<Person> createTableView() {
		tableView = new TableView<>();

		// Each column needs a title, and a source of data.

		TableColumn<Person, String> colName = new TableColumn<>("Name");
		colName.setCellValueFactory(c -> c.getValue().asNameProperty());
		tableView.getColumns().add(colName);

		TableColumn<Person, String> colFamilyName = new TableColumn<>("Family Name");
		colFamilyName.setCellValueFactory(c -> c.getValue().asFamilyNameProperty());
		tableView.getColumns().add(colFamilyName);

		// Finally, attach the tab
		tableView.setItems(model.getElements());

		return tableView;
	}

	public void start() throws IOException {
		stage.show();
	}

	/**
	 * Stopping the view - just make it invisible
	 */
	public void stop() {
		stage.hide();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		e.getActionCommand();

	}
	

	
	public void tournamentStart() throws Exception{

		TourModel tModel = new TourModel();
		TourView tView = new TourView(new Stage(), tModel);
		TourController tController = new TourController(tModel, tView);	
		tView.start();
		
		
	}


}
