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
	

	public class SOP {
		public static void print(String s) {
			System.out.println(s + "\n");
		}
	}

	public class Login extends Thread {
		String name;
		TheDemo theDemo;

		public Login(String name, TheDemo theDemo) {
			this.theDemo = theDemo;
			this.name = name;
			start();
		}

		@Override
		public void run() {
			theDemo.test(name);
		}
	}

	public class TheDemo {
		public synchronized void test(String name) {
			for (int i = 0; i < 10; i++) {
				SOP.print(name + " :: " + i);
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					SOP.print(e.getMessage());
				}
			}
		}

		public static void main(String[] args) {
			TheDemo theDemo = new TheDemo();
			new Login("THREAD 1", theDemo);
			new Login("THREAD 2", theDemo);
			new Login("THREAD 3", theDemo);
		}
	}
	
	
		
}