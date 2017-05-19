package ch.fhnw.richards.lecture07.email_validator;

import javafx.application.Application;
import javafx.stage.Stage;

public class EmailValidator extends Application {
    private EmailValidator_View view;
    private EmailValidator_Controller controller;
    private EmailValidator_Model model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new EmailValidator_Model();
        view = new EmailValidator_View(primaryStage, model);
        controller = new EmailValidator_Controller(model, view);

        // Display the GUI after all initialization is complete
        view.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
