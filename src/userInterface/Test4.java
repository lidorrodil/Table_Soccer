package userInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.scene.control.DatePicker;

public class Test4 {

	public static void main(String[] args) throws IOException {
		
		String fileNameDefined = "employees.csv";
        // -File class needed to turn stringName to actual file
        FileWriter write = new FileWriter(fileNameDefined);
        write.append("lidor");
        write.append(",");
        write.append("28");
        write.append("\n");
        write.flush();
        write.close();
        
	}

}
