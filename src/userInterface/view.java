package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
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

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.html.HTML;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import login.Validator;
import tournament.TournamentController;
import tournament.TournamentModel;
import tournament.TournamentView;

public class view implements ActionListener {
	final private model model;
	final private Stage stage;

	// Define the fields in the GUI
	protected TextField txtCalc;
	protected TableView<Person> tableView;
	protected Button buttonSortName = new Button("Show Members");
	protected Button buttonSortAge = new Button("Sort By: Age");
	protected Button buttonSortRank = new Button("Sort By: Rank");
	protected Button startTournament = new Button("Start new Tournament");
	Button aButton = new Button("Add Member");
	JCheckBox checkBox = new JCheckBox();

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

	public view(Stage stage, model model) throws IOException, URISyntaxException {
		this.stage = stage;
		this.model = model;

		// define checkbox for payment
		paymentGridPane = new HBox();
		paymentGridPane.setAlignment(Pos.CENTER);
		paymentGridPane.setPrefSize(40, 40);
		yes = new CheckBox();
		paymentGridPane.getChildren().add(yes);
		//no = new CheckBox(" No");
		//paymentGridPane.getChildren().add(no);

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
		
		
		
		checkInDatePicker = new DatePicker();
		/*
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        vbox.getChildren().add(gridPane);*/
		

		/*
		 * yes = new RadioButton("yes "); yes.setToggleGroup(group);
		 * paymentHbox.getChildren().add(yes); no = new RadioButton(" no");
		 * no.setToggleGroup(group); paymentHbox.getChildren().add(no);
		 */

		// Initialize TableView
		TableView<Person> tableView = createTableView();
		BorderPane Border = new BorderPane();
		HBox root = new HBox();

		GridPane basicInfo = new GridPane();
		GridPane personalData = new GridPane();

		final ImageView imv = new ImageView();
		final Image image2 = new Image(Main.class.getResourceAsStream("protips.png"));
		imv.setImage(image2);

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

		// personalData.add(new Label(), 0, 7);

		personalData.add(new Label("Payment:"), 0, 9);

		// personalData.add(payment, 1, 9);
		personalData.add(paymentGridPane, 1, 9);

		personalData.add(new Label(), 0, 10);

		personalData.add(aButton, 1, 10);
		GridPane.setHalignment(aButton, HPos.LEFT);
		personalData.add(btnEdit, 1, 11);
		GridPane.setHalignment(btnEdit, HPos.LEFT);
		personalData.add(btnClean, 0, 11);
		// personalData.setId("xxx");

		personalData.add(startTournament, 1, 13);
		
		personalData.add(new Label(), 1, 12);
		// personalData.add(peClassesGridPane, 1, 13);

		/*
		 * ImageView imageView = new ImageView(); imageView.setImage(new
		 * Image("protips.png")); Border.setRight(imageView);
		 */

		GridPane searchInfo = new GridPane();
		searchInfo.add(btnSearch, 0, 0);
		searchInfo.add(btnRemove, 0, 1);
		searchInfo.add(searchBoxByName, 1, 0);
		searchInfo.add(searchBoxByFamilyName, 2, 0);
		// searchInfo.add(checkBox, 0, 2);

		JRadioButton bird = new JRadioButton("Hi");
		// checkBox.setText("checkBox");
		bird.setMnemonic(KeyEvent.VK_C);
		bird.setActionCommand("10");
		bird.setSelected(true);
		// ButtonGroup group = new ButtonGroup();
		// group.add(bird);
		// bird.addActionListener(this);

		// searchInfo.setMinSize(200, 250);

		// personalData.setMinWidth(400);
		// personalData.setMinSize(200, 300);

		GridPane centerInfo = new GridPane();

		Label space = new Label("");
		space.setMinWidth(300);

		Label space2 = new Label("");
		space2.setMinWidth(100);
		personalData.add(space2, 1, 0);

		centerInfo.add(personalData, 0, 0);
		// centerInfo.add(new HBox(n), 1, 0);
		// centerInfo.add(new HBox(space2), 1, 1);

		centerInfo.add(searchInfo, 0, 9);
		centerInfo.setMinSize(200, 300);

		// centerInfo.add(searchInfo, 0,1);

		// centerInfo.add(Vbox, 0, 1);

		

		// Data of Members
		// write info into file
		/*
		 * aButton.setOnAction(e -> { try (FileWriter writer = new
		 * FileWriter(new File("file.txt"), true);) {
		 * writer.write(firstName.getText());
		 * writer.write(System.getProperty("line.separator"));
		 * writer.write(familyName.getText());
		 * writer.write(System.getProperty("line.separator"));
		 * writer.write(age.getText());
		 * writer.write(System.getProperty("line.separator"));
		 * writer.write(address.getText());
		 * writer.write(System.getProperty("line.separator"));
		 * writer.write(rank.getText());
		 * writer.write(System.getProperty("line.separator"));
		 * writer.write(payment.getText());
		 * writer.write(System.getProperty("line.separator")); writer.close(); }
		 * catch (Exception e1) {
		 * 
		 * e1.printStackTrace(); } });
		 */

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

		// Size constraints
		// buttonSortName.setMaxWidth(Double.MAX_VALUE); // button can grow
		// horizontally
		// buttonSortAge.setMaxWidth(Double.MAX_VALUE); // button can grow
		// horizontally
		// buttonSortRank.setMaxWidth(Double.MAX_VALUE); // button can grow

		// root.getChildren().addAll(Vtable, centerInfo);
		// Border.setTop(root);

		Border.setLeft(Vtable);

		// Border.setBottom(searchInfo);
		Border.setCenter(centerInfo);
		Border.setRight(space);
		Border.setId("border");

		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(15);
		basicInfo.getColumnConstraints().addAll(cc, cc, cc, cc);
		personalData.getColumnConstraints().addAll(cc, cc, cc, cc);
		// RowConstraints rc = new RowConstraints();
		// rc.setPercentHeight(15);
		// basicInfo.getRowConstraints().addAll(rc, rc, rc, rc);
		// personalData.getRowConstraints().addAll(rc, rc, rc, rc);

		
		
		// Border.setCenter(Moon_img);
		Scene scene = new Scene(Border);
		// Scene scene = new Scene(table.func());
		scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
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

		// login();

		stage.show();
		// System.out.println("Step 2");

	}

	public void login() throws IOException {
		stage.setTitle("Access");

		GridPane root2 = new GridPane();
		name = new TextField();
		pass = new TextField();
		root2.add(name, 0, 0);
		root2.add(pass, 0, 1);
		root2.add(login, 0, 2);

		login.setOnAction(e -> {
			if (name.getText().equals(username) && pass.getText().equals(password)) {
				System.out.println("Succeed");
				flag = true;
				stop();
			} else
				System.out.println("Fail");
		});
		Scene scene = new Scene(root2);
		stage.setScene(scene);
		System.out.println("Step here");

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
	
	private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);

        checkInDatePicker = new DatePicker();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        vbox.getChildren().add(gridPane);
    }
	
	public void tournamentStart(){
		TournamentModel tModel = new TournamentModel();
		TournamentView tView = new TournamentView(new Stage(), tModel);
		TournamentController tController = new TournamentController(tModel, tView);
		
		tView.startTournament();
		
		
	}

}
