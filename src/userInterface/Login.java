package userInterface;
import javax.swing.*;
import javax.swing.GroupLayout.Group;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;

public class Login  {
	
	public static void main(String[] args) throws FileNotFoundException {

		    Scanner scan = new Scanner (new File("login.txt"));
		    Scanner keyboard = new Scanner (System.in);
		    String user = scan.nextLine();
		    String pass = scan.nextLine(); // looks at selected file in scan

		    String inpUser = keyboard.nextLine();
		    String inpPass = keyboard.nextLine(); // gets input from user

		    if (inpUser.equals(user) && inpPass.equals(pass)) {
		        System.out.print("your login message");
		    } else {
		        System.out.print("your error message");
		    }
		}	
		
		
		
		
}