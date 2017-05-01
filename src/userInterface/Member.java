package userInterface;

import java.io.BufferedReader;

import javafx.beans.property.SimpleStringProperty;

/**
 * A SuperNumber is an integer, along with String representations of that
 * integer as a binary, decimal, and hexadecimal number. The setter methods
 * allow any of the representations to be changed (Strings). Changing a
 * representation will change the value of the number, and this will change all
 * of the other representations as well.
 * 
 * This class is part of the model. Since the representations will be needed by
 * the View, these are stored as properties, to allow ChangeListeners to be
 * defined.
 */
public class Member {
	//private Integer value = 0;
	private String value1;
	private String value2;
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty familyName = new SimpleStringProperty();
	//private final SimpleStringProperty asHexadecimal = new SimpleStringProperty();
	
	public Member(String name, String familyName) {
		this.value1 = name;
		this.value2 = familyName;
		updateRepresentations();
	}
	
	private void updateRepresentations() {
		name.setValue(value1.toString());
		//familyName.setValue(Integer.toBinaryString(value));
		familyName.setValue((value2));
	}

	//--- Getters and Setters ---
	
	// Note: The naming is CRITICAL, because the TableView will derive method
	// names from the property names that it is given.
	
	public SimpleStringProperty asNameProperty() {
		return name;
	}

	public String getAsName() {
		return name.get();
	}
	
	public void setAsName(String newValue) {
		//value = Integer.parseInt(newValue);
		value1 = (newValue);
		updateRepresentations();
	}

	public SimpleStringProperty asFamilyNameProperty() {
		return familyName;
	}
	
	public String getAsFamilyName() {
		return familyName.get();
	}

	public void setAsFamilyName(String newValue) {
		//value = Integer.parseInt(newValue, 2);
		value2 =(newValue);
		updateRepresentations();
	}

	
}
