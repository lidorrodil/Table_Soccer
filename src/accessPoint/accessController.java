package accessPoint;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import userInterface.MVC;
import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class accessController {
	final private accessModel model;
	final private accessView view;
	private ChgDataView dataView;

	final static File PASSWORD_FILE = new File("password.bin");
	final static File PASSWORD_SALT_FILE = new File("passwordSalt.bin");
	final static File USERNAME_FILE = new File("username.bin");
	final static File USERNAME_SALT_FILE = new File("userSalt.bin");
	final static Charset ENCODING = StandardCharsets.UTF_8;
	private static final Charset Encoder = null;

	private static String standardusername = "admin";
	private static String standardPassword = "admin";
	private static byte[] hashedPassword;
	private static byte[] hashedUsername;
	private static byte[] saltPassword;
	private static byte[] saltUsername;
	private static PasswordEncryptionService encryptor = new PasswordEncryptionService();
	
	private static boolean isAccessGranted = false; 
	
	// Should be true
	private boolean makeWritable = true;
	// Should be false -> Set true if you want to reset password.
	private boolean makeUnWritable = false;

	protected accessController(accessModel model, accessView view) {
		this.model = model;
		this.view = view;
		dataView = new ChgDataView();
		
		PASSWORD_FILE.setWritable(makeUnWritable);
		PASSWORD_SALT_FILE.setWritable(makeUnWritable);
		USERNAME_FILE.setWritable(makeUnWritable);
		USERNAME_SALT_FILE.setWritable(makeUnWritable);

		firstLaunchPasswordSetter();
		
		readInSalts();
		

		view.btn.setOnAction(e -> openMainView());
		view.btnChangeUserData.setOnAction(e->startUserdataChangeView());
		dataView.btnOK.setOnAction(e->changeUserData());
		dataView.btnCancel.setOnAction(e->dataView.stop());
		
	}

	/**
	 * Writes a new password into the password file.
	 * @param aPasswordField must not be null or 0 chars.
	 */
	public void writeNewPasswordFile(PasswordField aPasswordField) throws FileNotFoundException{
		byte[] result = null;
		try {
			result = encryptor.getEncryptedPassword(aPasswordField.getText(), saltPassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileOutputStream newPasswordWriter;
		try {
			newPasswordWriter = new FileOutputStream(PASSWORD_FILE, false);
			newPasswordWriter.write(result);
			newPasswordWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Writes a new username into the password file.
	 * @param aTextField must not be null or 0 chars.
	 */
	public void writeNewUsernameFile(TextField aTextField){
		byte[] result = null;
		try {
			result = encryptor.getEncryptedPassword(aTextField.getText(), saltUsername);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileOutputStream newUsernameWriter;
		try {
			newUsernameWriter = new FileOutputStream(USERNAME_FILE, false);
			newUsernameWriter.write(result);
			newUsernameWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Changes the user Data
	 */
	private void changeUserData(){
		// Checks if you are allowed to change data.
		String output = "";
		if(checkInput(dataView.oldNameText,dataView.oldPasswordBox)){
			dataView.stop();
			try {
				if(dataView.newNameText.getText().trim().isEmpty()){
					output = output + "Username NOT changed; \n";
				}
				else{
					writeNewUsernameFile(dataView.newNameText);
					output = output + "Username changed; \n";
				}
				if(dataView.newPasswordBox.getText().trim().isEmpty()){
					output = output + "Password NOT changed.";
				}
				else{
					writeNewPasswordFile(dataView.newPasswordBox);
					output = output + "Password changed";
				}
				view.actiontarget.setTextFill(Color.FIREBRICK);
				view.actiontarget.setText(output);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			dataView.actiontarget.setFill(Color.FIREBRICK);
			dataView.actiontarget.setText("Change data failed!");
		}
		dataView.newNameText.clear();
		dataView.oldNameText.clear();
		dataView.newPasswordBox.clear();
		dataView.oldPasswordBox.clear();
	}
	
	/**
	 * Opens the view to change user data
	 */
	private void startUserdataChangeView() {
		dataView.start();
	}

	/**
	 * Reads the salts into the system.
	 */
	private void readInSalts() {
		PASSWORD_SALT_FILE.setWritable(makeWritable);
		USERNAME_SALT_FILE.setWritable(makeWritable);
		Path location = Paths.get(USERNAME_SALT_FILE.getPath());
		try {
			saltUsername = Files.readAllBytes(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		location = Paths.get(PASSWORD_SALT_FILE.getPath());
		try {
			saltPassword = Files.readAllBytes(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PASSWORD_SALT_FILE.setWritable(makeUnWritable);
		USERNAME_SALT_FILE.setWritable(makeUnWritable);
	}

	/**
	 * Should only be called at first start
	 * @return A byte[] array containing the salt. 
	 */
	private byte[] generateSalt() {
		byte[] salt = null;
		try {
			salt = encryptor.generateSalt();
		} catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return salt;
	}

	/**
	 * On first launch of the application, generate password and username files
	 * with standardusername and standardpassword stored within
	 */
	private void firstLaunchPasswordSetter() {
		PASSWORD_FILE.setWritable(makeWritable);
		USERNAME_FILE.setWritable(makeWritable);
		USERNAME_SALT_FILE.setWritable(makeWritable);
		PASSWORD_SALT_FILE.setWritable(makeWritable);
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(PASSWORD_FILE.getPath()));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			if (br.readLine() == null) {
				try {
					saltUsername = generateSalt();
					FileOutputStream userSaltWriter = new FileOutputStream(USERNAME_SALT_FILE);
					userSaltWriter.write(saltUsername);
					userSaltWriter.close();
					
					saltPassword = generateSalt();
					FileOutputStream passwordSaltWriter = new FileOutputStream(PASSWORD_SALT_FILE);
					passwordSaltWriter.write(saltPassword);
					passwordSaltWriter.close();

					hashedPassword = encryptor.getEncryptedPassword(standardPassword, saltPassword);
					FileOutputStream passwordWriter = new FileOutputStream(PASSWORD_FILE);
					passwordWriter.write(hashedPassword);
					passwordWriter.close();
					System.out.println("Standard password generated successfully.");

					hashedUsername = encryptor.getEncryptedPassword(standardusername, saltUsername);
					FileOutputStream usernameWriter = new FileOutputStream(USERNAME_FILE);
					usernameWriter.write(hashedUsername);
					usernameWriter.close();
					System.out.println("Standard username generated successfully.");
					
					System.out.println();
					
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PASSWORD_FILE.setWritable(makeUnWritable);
		USERNAME_FILE.setWritable(makeUnWritable);
		USERNAME_SALT_FILE.setWritable(makeUnWritable);
		PASSWORD_SALT_FILE.setWritable(makeUnWritable);
	}
	
	/**
	 * If username and password are correct, close this view and get to the main view.
	 */
	public void openMainView(){
		if(checkInput(view.name,view.pwBox)){
			view.stop();
			try {
				view.Appstart();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			view.actiontarget.setTextFill(Color.FIREBRICK);
			view.actiontarget.setText("Sign in failed");
		}
	}

	/**
	 * Checks the user input.
	 * @return result is true if username and password match the saved data, else false.
	 */
	public boolean checkInput(TextField aTextField, PasswordField aPasswordField) {
		PASSWORD_FILE.setWritable(makeWritable);
		USERNAME_FILE.setWritable(makeWritable);
		boolean result = false;
		
		if(checkUsername(aTextField) && checkPassword(aPasswordField)){
			result = true;
			System.out.println("Login/Change data Successful.");
			System.out.println();
			isAccessGranted = true;
		}
		
		PASSWORD_FILE.setWritable(makeUnWritable);
		USERNAME_FILE.setWritable(makeUnWritable);
		return result;
	}
	
	/**
	 * Checks the username.
	 * @return result is true if username matches the saved data, else false.
	 */
	public boolean checkUsername(TextField aTextField){
		boolean result = false;
		Path location = Paths.get(USERNAME_FILE.getPath());
		byte[] readUsernameFromFile = null;
		try {
			readUsernameFromFile = Files.readAllBytes(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result = encryptor.authenticate(aTextField.getText(), readUsernameFromFile, saltUsername);
			if(result){
				System.out.println("Username correct.");
			}
			else{
				System.out.println("Username wrong.");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Checks the password.
	 * @return result is true if password matches the saved data, else false.
	 */
	public boolean checkPassword(PasswordField aPasswordField){
		boolean result = false;
		Path location = Paths.get(PASSWORD_FILE.getPath());
		byte[] readPasswordFileArray = null;
		try {
			readPasswordFileArray = Files.readAllBytes(location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			result = encryptor.authenticate(aPasswordField.getText(), readPasswordFileArray, saltPassword);
			if(result){
				System.out.println("Password correct.");
			}
			else{
				System.out.println("Password wrong.");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
