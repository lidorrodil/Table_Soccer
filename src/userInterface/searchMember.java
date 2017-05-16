package userInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class searchMember {

	public void searchMember2(String value1, String value2) throws Exception {

    	try {
    		File file = new File("./database/file.txt");
    		
    		FileWriter writer = new FileWriter(new File("./database/resultSearch.txt"));
    	    Scanner scanner = new Scanner(file);
    	    
    	    //now read the file line by line...
    	    int lineNum = 0;
    	    while (scanner.hasNextLine()) {
    	        String fName = scanner.nextLine();
    	        String familyName = scanner.nextLine();
    	        lineNum++;
    	        if(fName.equals(value1) &&
    	        		familyName.equals(value2) ) { 
    	            //System.out.println("ho hum, i found it on line " +lineNum);
    	            writer.write(fName); writer.write(System.getProperty( "line.separator" ));
    	            writer.write(familyName); writer.write(System.getProperty( "line.separator" ));
    	            writer.write(scanner.nextLine()); writer.write(System.getProperty( "line.separator" ));
    	            writer.write(scanner.nextLine()); writer.write(System.getProperty( "line.separator" ));
    	            writer.write(scanner.nextLine()); writer.write(System.getProperty( "line.separator" ));
    	            writer.write(scanner.nextLine()); writer.write(System.getProperty( "line.separator" ));
    	        }
    	        
    	       
    	    }
    	 //   System.out.println("Step Critic2");
    	    
    	    writer.close();
    	} catch(FileNotFoundException e) { 
    	    //handle this
    	}
    }



}