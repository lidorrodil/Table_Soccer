package userInterface;
import javax.swing.*;
import javax.swing.GroupLayout.Group;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;

public class Login  {
	
	public class access extends Thread {
		GridPane root2 = new GridPane();
		name = new TextField();
		pass = new TextField();
		String name = "admin";
		String pass ="admin";
		boolean flag = false;
		root2.add(name, 0, 0);
		root2.add(pass, 0, 1);
		root2.add(login, 0, 2); 
		 
		login.setOnAction(e -> {
			if (name.equals("admin") && pass.equals("admin")) {
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

		public static void main(String[] args) {
			access log = new access();
		}
	}
	
	
		
