package userInterface;

import java.beans.DefaultPersistenceDelegate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import login.Validator;
import sun.launcher.resources.launcher;

//An interface to be implemented by everyone interested in "Hello" events
interface HelloListener {
	void someoneSaidHello(String[] args);
}

// Someone who says "Hello"
class Initiater {
	private List<HelloListener> listeners = new ArrayList<HelloListener>();

	public void addListener(HelloListener toAdd) {
		listeners.add(toAdd);
	}

	public void sayHello(String[] args) {
		// MVC mvc = new MVC();

		// Notify everybody that may be interested.
		for (HelloListener hl : listeners)
			hl.someoneSaidHello(args);
	}
}

// Someone interested in "Hello" events
class Responder implements HelloListener {
	@Override
	public void someoneSaidHello(String[] args) {
		Validator validator = new Validator();
		validator.main(args);

		System.out.println("Hello there...");
	}

}

// Someone interested in "Hello" events
class Responder2 implements HelloListener {
	@Override
	public void someoneSaidHello(String[] args) {
		// Validator validator = new Validator();
		MVC mvc = new MVC();
		mvc.launch(args);
		System.out.println("Hello there...");
	}

}

public class EventChecks {

	public static Stage primaryStage;

	public static void main(String[] args) {
		Initiater initiater = new Initiater();
		Responder responder = new Responder();
		Responder2 responder2 = new Responder2();
		// Validator validator = new Validator();

		initiater.addListener(e -> {
			responder.someoneSaidHello(args);
			responder2.someoneSaidHello(args);
		});

		initiater.sayHello(args); // Prints "Hello!!!" and "Hello there..."
	}

}
