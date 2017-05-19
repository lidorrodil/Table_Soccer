package userInterface;
 import javafx.scene.*;
 import javafx.stage.*;
 import javafx.geometry.*;
 import javafx.application.*;
 import javafx.scene.control.*;
 import javafx.scene.layout.*;
 import javafx.collections.*;
 import javafx.event.*;
 import javafx.scene.text.TextAlignment;

   public class ClassRegistrationForm extends Application{

    Scene scene1;

  Button createRequestButton,clearButton;

   Label peLabel,mathLabel,electivesLabel,englishLabel,spaceLabel;

   RadioButton english12,english11,english10,english9;
   ToggleGroup group;

 ComboBox<String>electivesComboBox;

 CheckBox healthBox,sportsBox,liftingBox,aerobicsBox,
 archeryBox,swimmingBox,yogaBox,bowlingBox;

    HBox buttonHbox;
    VBox englishVbox,mathVbox,electivesVbox,peVbox1,peVbox2;

    GridPane peClassesGridPane = new GridPane();

     ListView<String> mathClassesListView;
     ObservableList<String> mathClassesList;


public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Class Registration Application");
    primaryStage.setResizable(false);
    primaryStage.sizeToScene();




        GridPane gridPane = new GridPane();
        peClassesGridPane = new GridPane();
        scene1 = new Scene(gridPane);






       buttonHbox = new HBox();

       createRequestButton = new Button("Create Request");
       clearButton = new Button("Clear");
       buttonHbox.getChildren().addAll(createRequestButton,clearButton);
       gridPane.setConstraints(buttonHbox,0,2);


       peLabel = new Label("Pe"); 
       peClassesGridPane.setConstraints(peLabel,0,0);
       healthBox = new CheckBox("Health");
       peClassesGridPane.setConstraints(healthBox,0,1);
       yogaBox = new CheckBox("Yoga");
       peClassesGridPane.setConstraints(yogaBox,0,2);
       sportsBox = new CheckBox("Sports");
       peClassesGridPane.setConstraints(sportsBox,0,3);
       archeryBox = new CheckBox("Archery");
       peClassesGridPane.setConstraints(archeryBox,0,4);

       spaceLabel = new Label("");
       peClassesGridPane.setConstraints(spaceLabel,1,0);
       liftingBox = new CheckBox("Lift");
       peClassesGridPane.setConstraints(liftingBox,1,1);
       swimmingBox = new CheckBox("Swim");
       peClassesGridPane.setConstraints(swimmingBox,1,2);
       aerobicsBox = new CheckBox("Aero");
       peClassesGridPane.setConstraints(aerobicsBox,1,3);
       bowlingBox = new CheckBox("Bowl");
       peClassesGridPane.setConstraints(bowlingBox,1,4);
       peClassesGridPane.getChildren().addAll(
       peLabel,healthBox,yogaBox,sportsBox,archeryBox,spaceLabel,
        liftingBox,swimmingBox,aerobicsBox,bowlingBox
       );  

       gridPane.setConstraints(peClassesGridPane,0,1);



       mathVbox = new VBox();
       mathVbox.setAlignment(Pos.CENTER);
       mathVbox.setPrefSize(150,100);
       mathClassesListView = new ListView();
      mathClassesListView.setPrefSize(100,50);
       mathClassesList = FXCollections.observableArrayList(
                "Algebra 1-2",
                "Algebra 3-4",
                "Geometry",
                "Pre-Calculus",
                "Calculus"
         );
        mathClassesListView.setItems(mathClassesList);



       mathLabel = new Label("Math Classes");
       mathVbox.getChildren().addAll(mathLabel,mathClassesListView);
       gridPane.setConstraints(mathVbox,1,1);




       englishVbox = new VBox();
      englishVbox.setPrefSize(200, 200);
       englishVbox.setAlignment(Pos.CENTER);
       group  = new ToggleGroup();

    englishLabel = new Label("English Classes");
        englishVbox.getChildren().add(englishLabel);
    english12 = new RadioButton("English12");
    english12.setToggleGroup(group);
        englishVbox.getChildren().add(english12);
    english11 = new RadioButton("English11");
    english11.setToggleGroup(group);
        englishVbox.getChildren().add(english11);
    english10 = new RadioButton("English12");
    english10.setToggleGroup(group);
    englishVbox.getChildren().add(english10);
    english9 = new RadioButton("English9");
        english9.setToggleGroup(group);
    englishVbox.getChildren().add(english9);
    gridPane.setConstraints(englishVbox,0,0);

        electivesVbox = new VBox();
        electivesVbox.setAlignment(Pos.CENTER);
    electivesLabel = new Label("Electives");


    ObservableList<String> data = FXCollections.observableArrayList("Java"
            , "Web Design"
            , "Welding"
            , "Woods"
            , "Art"
            , "Band"
            , "GameDesign"
            , "Graphic Arts");
    electivesComboBox = new ComboBox<String>();
    electivesComboBox.setItems(data);

           electivesVbox.getChildren().addAll(
  electivesLabel,electivesComboBox
 );

       gridPane.setConstraints(electivesVbox,0,1);   



          gridPane.getChildren().addAll(
  englishVbox,electivesVbox,peVbox1,peVbox2,mathVbox,
  buttonHbox,peClassesGridPane
 );

    primaryStage.setScene(scene1);
    primaryStage.show();
}



public static void main(String[] args) {

    launch(args);

  }




   }