package accessPoint;

import javafx.application.Application;
import javafx.stage.Stage;

public class Validator extends Application {
    private accessView view;
    private accessController controller;
    private accessModel model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = new accessModel();
        view = new accessView(primaryStage, model);
        controller = new accessController(model, view);

        // Display the GUI after all initialization is complete
        view.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
