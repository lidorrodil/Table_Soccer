package userInterface;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import login.Validator;
import login.Validator_Controller;
import login.Validator_Model;
import login.Validator_View;

public class MVC extends Application {
	//private static final boolean EmailValidator = false;
	private model model;
	private view view;
	private controller controller;

	public static void main(String[] args) {
		launch();		
	}
	
	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs the primaryStage created by JavaFX.
	 * @throws Exception 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new model();
		view = new view(primaryStage, model);
		controller = new controller(model, view);
	
		view.start();
		
	}

	/**
	 * The stop method is the opposite of the start method. It provides an
	 * opportunity to close down the program gracefully, when the program has
	 * been closed.
	 */
	@Override
	public void stop() {
		if (view != null) view.stop();
	}
}

