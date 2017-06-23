package accessPoint;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import userInterface.controller;
import userInterface.model;
import userInterface.view;

public class ChgDataView {
	private accessModel model;
	private Stage stage;

	protected TextField oldNameText;	
	protected TextField newNameText;
	Button btnOK = new Button("OK");
	Button btnCancel = new Button("Cancel");
	PasswordField oldPasswordBox;
	PasswordField newPasswordBox;
	final Text actiontarget;

	protected ChgDataView() {
		stage = new Stage();

		GridPane root = new GridPane();
		oldNameText = new TextField();
		oldPasswordBox = new PasswordField();
		newNameText = new TextField();
		newPasswordBox = new PasswordField();

		actiontarget = new Text();
		root.add(actiontarget, 1, 6);

		HBox hbBtn = new HBox(10);

		Text scenetitle = new Text("Change data");
		scenetitle.setId("userdata-text");
		actiontarget.setId("actiontarget");

		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		// stage.setTitle("Email Validator");
		root.add(scenetitle, 0, 0, 2, 1);

		Label oldUserName = new Label("Old User Name:");
		root.add(oldUserName, 0, 1);
		root.add(oldNameText, 1, 1);

		Label oldPassword = new Label("Old Password:");
		root.add(oldPassword, 0, 2);
		root.add(oldPasswordBox, 1, 2);
		
		Label newUserName = new Label("New User Name:");
		root.add(newUserName, 0, 3);
		root.add(newNameText, 1, 3);

		Label newPassword = new Label("New Password:");
		root.add(newPassword, 0, 4);
		root.add(newPasswordBox, 1, 4);

		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().addAll(btnCancel, btnOK);
		root.add(hbBtn, 1, 5);

		Scene scene = new Scene(root, 340, 275);
		stage.setTitle("Welcome");

		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));

		// scene.getStylesheets().add(getClass().getResourceAsStream(name))
		stage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("layouts.css").toExternalForm());

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

	/**
	 * Getter for the stage, so that the controller can access window events
	 */
	public Stage getStage() {
		return stage;
	}

}
