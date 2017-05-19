package userInterface;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Person {
	//private Integer value = 0;
	private String value1;
	private String value2;
	List<String> values = new ArrayList<String>();
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty familyName = new SimpleStringProperty();
	private final SimpleStringProperty birthday = new SimpleStringProperty();
	private final SimpleStringProperty age = new SimpleStringProperty();
	private final SimpleStringProperty street = new SimpleStringProperty();
	private final SimpleStringProperty streetNum = new SimpleStringProperty();
	private final SimpleStringProperty city = new SimpleStringProperty();
	private final SimpleStringProperty postzip = new SimpleStringProperty();
	private final SimpleStringProperty rank = new SimpleStringProperty();
	private final SimpleStringProperty payment = new SimpleStringProperty();
	//private final SimpleStringProperty asHexadecimal = new SimpleStringProperty();
	
	public Person(String name, String familyName,String birthday, String age,
			String street, String streetNum, String city, String postzip,
					String rank, String payment) {
		//this.value1 = name;
		//this.value2 = familyName;
		//values.addAll(name,familyName,age,address,rank,payment);
		values = Arrays.asList(name,familyName,birthday,age,street,streetNum,
				city,postzip,rank,payment);
		updateRepresentations();
	}
	
	private void updateRepresentations() {
		//name.setValue(value1.toString());
		//familyName.setValue(Integer.toBinaryString(value));
		//familyName.setValue((value2));
		
		name.setValue(values.get(0).toString());
		familyName.setValue(values.get(1).toString());
		birthday.setValue(values.get(2).toString());
		age.setValue(values.get(3).toString());
		street.setValue(values.get(4).toString());
		streetNum.setValue(values.get(5).toString());
		city.setValue(values.get(6).toString());
		postzip.setValue(values.get(7).toString());
		rank.setValue(values.get(8).toString());
		payment.setValue(values.get(9).toString());
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
		//value1 = (newValue);
		values.set(0, newValue);
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
		//value2 =(newValue);
		values.set(1, newValue);
		updateRepresentations();
	}

	
}
