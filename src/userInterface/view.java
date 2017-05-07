package userInterface;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.html.HTML;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class view {
	final private model model;
	final private Stage stage;
	
	// Define the fields in the GUI
	protected TextField txtCalc; 
	protected TableView<Person> tableView;
	protected Button buttonSortName = new Button("Sort By: Name");
	protected Button buttonSortAge = new Button("Sort By: Age");
	protected Button buttonSortRank = new Button("Sort By: Rank");
	
	protected Button btnSearch = new Button("Search");
	protected Button btnClean = new Button("Clean");
	TextField searchBoxByName = new TextField();
	TextField searchBoxByFamilyName = new TextField();
	TextField firstName = new TextField();
	TextField familyName = new TextField();
	TextField age = new TextField();
	TextField address = new TextField();
	TextField rank = new TextField();
	TextField payment = new TextField();

	protected view(Stage stage, model model) throws IOException, URISyntaxException {
		this.stage = stage;
		this.model = model;
		
		
		// Initialize TableView
		TableView<Person> tableView = createTableView();
		BorderPane Border = new BorderPane();
		HBox root = new HBox();

		GridPane basicInfo = new GridPane();
		GridPane personalData = new GridPane();
		
		personalData.add( new Label("First Name:"), 0,0);
		personalData.add(firstName, 1, 0);
		
		personalData.add( new Label ("Family Name:"), 0, 1);

		personalData.add(familyName, 1, 1);
		
		personalData.add( new Label("Age:"), 0, 2);

		personalData.add(age, 1, 2);
		
		personalData.add( new Label("Address:"), 0, 3);
		
		personalData.add(address, 1, 3);
		
		personalData.add( new Label("Rank:"), 0, 4);
		
		personalData.add(rank, 1, 4);
		
		personalData.add(new Label(), 0, 5);
		
		personalData.add( new Label("Payment:"), 0, 5);
		
		personalData.add(payment, 1, 6);
		
		personalData.add(new Label(), 0, 7);
		
        Button aButton = new Button("Add Member");
        personalData.add(aButton, 1, 8);
        GridPane.setHalignment(aButton, HPos.LEFT);

        personalData.add(new Label(), 1, 9);
        
        personalData.add(searchBoxByName, 1, 10);
        
        personalData.add(searchBoxByFamilyName, 2, 10);
        personalData.add(btnClean, 0, 8);

        personalData.add(btnSearch, 0, 10);
        GridPane.setHalignment(btnSearch, HPos.LEFT);
		
        
        btnClean.setOnAction(e -> {
        	firstName.setText("");
        	familyName.setText("");
        	age.setText("");
        	address.setText("");
        	rank.setText("");
        	payment.setText("");
        });
        
        //Data of Members
        // write info into file
        aButton.setOnAction(e -> {        	
            try (FileWriter writer = new FileWriter(new File("file.txt"), true);){
				writer.write(firstName.getText()); writer.write(System.getProperty( "line.separator" ));
				 writer.write(familyName.getText()); writer.write(System.getProperty( "line.separator" ));
		            writer.write(age.getText()); writer.write(System.getProperty( "line.separator" ));
		            writer.write(address.getText()); writer.write(System.getProperty( "line.separator" ));
		            writer.write(rank.getText()); writer.write(System.getProperty( "line.separator" ));
		            writer.write(payment.getText()); writer.write(System.getProperty( "line.separator" ));
		            writer.close();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			} 
        });
        
		// Layout root pane
		VBox Vtable = new VBox();
		Vtable.setPadding(new Insets(10)); // around edge of VBox
		Vtable.setSpacing(10); // between elements
		VBox.setVgrow(tableView, Priority.ALWAYS); // Vertical resize goes to the table
		Vtable.getChildren().addAll(tableView, buttonSortName,buttonSortAge,
				buttonSortRank);
		
		copyFiles copy = new copyFiles();
		copy.sortByAge();
		copy.sortByRank();

		
		// Size constraints
		buttonSortName.setMaxWidth(Double.MAX_VALUE); // button can grow horizontally
		buttonSortAge.setMaxWidth(Double.MAX_VALUE); // button can grow horizontally
		buttonSortRank.setMaxWidth(Double.MAX_VALUE); // button can grow horizontally

		root.getChildren().addAll(Vtable, personalData);
		
		Border.setTop(root);

		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(15);
		basicInfo.getColumnConstraints().addAll(cc, cc, cc, cc);
		personalData.getColumnConstraints().addAll(cc, cc, cc, cc);
		RowConstraints rc = new RowConstraints();
		rc.setPercentHeight(15);
		basicInfo.getRowConstraints().addAll(rc, rc, rc, rc);
		personalData.getRowConstraints().addAll(rc, rc, rc, rc);

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

	public void start() {
		stage.show();
	}

	/**
	 * Stopping the view - just make it invisible
	 */
	public void stop() {
		stage.hide();
	}
}
