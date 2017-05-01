package userInterface;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
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
	protected TableView<Member> tableView;
	protected Button button = new Button("Refresh list");

	protected view(Stage stage, model model) throws IOException, URISyntaxException {
		this.stage = stage;
		this.model = model;

		// Initialize TableView
		TableView<Member> tableView = createTableView();
		BorderPane Border = new BorderPane();
		HBox root = new HBox();

		GridPane basicInfo = new GridPane();
		GridPane personalData = new GridPane();

		
		personalData.add( new Label("First Name:"), 0,0);
		TextField firstName = new TextField();
		personalData.add(firstName, 1, 0);
		
		personalData.add( new Label ("Family Name:"), 0, 1);
		TextField familyName = new TextField();
		personalData.add(familyName, 1, 1);
		
		personalData.add( new Label("Age:"), 0, 2);
		TextField age = new TextField();
		personalData.add(age, 1, 2);
		
		personalData.add( new Label("Address:"), 0, 3);
		TextField address = new TextField();
		personalData.add(address, 1, 3);
		
		personalData.add( new Label("Rank:"), 0, 4);
		TextField rank = new TextField();
		personalData.add(rank, 1, 4);
		
		personalData.add(new Label(), 0, 5);
		
		personalData.add( new Label("Payment:"), 0, 5);
		TextField payment = new TextField();
		personalData.add(payment, 1, 6);
		
		personalData.add(new Label(), 0, 7);
		
        Button aButton = new Button("Add Member");
        personalData.add(aButton, 1, 8);
        GridPane.setHalignment(aButton, HPos.LEFT);
       
        //Data of Members
        PrintWriter writer = new PrintWriter("file.txt", "UTF-8");
       
        // write info into file
        aButton.setOnAction(e -> {	
            writer.write(firstName.getText()); writer.println();
            writer.write(familyName.getText()); writer.println();
            writer.write(age.getText()); writer.println();
            writer.write(address.getText()); writer.println();
            writer.write(rank.getText()); writer.println();
            writer.write(payment.getText()); writer.println();
			writer.close();
            
        });
		
		// personalData.add(space, 2, 2);

		// Layout root pane
		VBox Vtable = new VBox();
		Vtable.setPadding(new Insets(10)); // around edge of VBox
		Vtable.setSpacing(10); // between elements
		VBox.setVgrow(tableView, Priority.ALWAYS); // Vertical resize goes to the table
		Vtable.getChildren().addAll(tableView, button);
		
		// Size constraints
		button.setMaxWidth(Double.MAX_VALUE); // button can grow horizontally

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
		stage.setTitle("Calculator");		
		
	}
	
	
	private TableView<Member> createTableView() {
		tableView = new TableView<>();

		// Each column needs a title, and a source of data.

		TableColumn<Member, String> colDecimal = new TableColumn<>("Name");
		colDecimal.setCellValueFactory(c -> c.getValue().asNameProperty());
		tableView.getColumns().add(colDecimal);

		TableColumn<Member, String> colBinary = new TableColumn<>("Family Name");
		colBinary.setCellValueFactory(c -> c.getValue().asFamilyNameProperty());
		tableView.getColumns().add(colBinary);

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
