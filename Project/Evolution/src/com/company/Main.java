package com.company;
//
//import java.io.IOException;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        Evolution darwin = new Evolution(50, 30, 40, 10, 40);
//        darwin.run(100);
//    }
//}
/* w  w  w . j ava2  s.c om*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    int s = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox(5);
        Label textLbl = new Label("                ");
        TextArea text = new TextArea();
        text.setPrefRowCount(10);
        text.setPrefColumnCount(20);
        text.setWrapText(true);
        Text p = new Text();
        p.setText("This is a text sample");
        Text t = new Text();
        t.setX(20.0f);
        t.setY(65.0f);
        GridPane pane = new GridPane();
        Evolution darwin = new Evolution(50, 30, 160, 10, 50);
        int presetSteps = 1000; //And presetSteps
        String[][][] myArray = darwin.run(presetSteps);
        for (int x = 0; x < myArray[s].length; x++) {
            for (int y = 0; y < myArray[s][x].length; y++) {
                Label label = new Label(myArray[s][x][y], new Rectangle(1, 6));
                pane.add(label, x, y);
            }
        }

        Button getNextStep = new Button("Next Step");
        getNextStep.setOnAction(e -> nextView(myArray, stage));
        Button getPreviousStep = new Button("Previous Step");
        getPreviousStep.setOnAction(e -> prevView(myArray, stage));
        Button getSkippedStep = new Button("Skip 10");
        getSkippedStep.setOnAction(e -> skip10View(myArray, stage));

        HBox buttonBox = new HBox(10, getPreviousStep, getNextStep, getSkippedStep);

        root.getChildren().addAll(textLbl, t, buttonBox);
        Scene scene = new Scene(pane);
        Scene scene2 = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene2);
        stage1.setTitle("MAIN MENU");

        stage.setScene(scene);
        stage.setTitle("Step number 1");
        stage.show();
        stage1.show();
//        stage.setScene(scene2);
    }

    private void prevView(String[][][] myArray, Stage stage) {
        s--;
        if (s < 0) {
            s++;
            return;
        }
        GridPane pane = new GridPane();
//        while(myArray[s])
        for (int x = 0; x < myArray[s].length; x++) {
            for (int y = 0; y < myArray[s][x].length; y++) {
                Label label = new Label(myArray[s][x][y], new Rectangle(1, 6));
                pane.add(label, x, y);
            }
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Step number " + (s + 1));
    }

    private void nextView(String[][][] myArray, Stage stage) {
        s++;
        if (s >= myArray.length) {
            s--;
            return;
        }
        GridPane pane = new GridPane();

//        while(myArray[s])
        for (int x = 0; x < myArray[s].length; x++) {
            for (int y = 0; y < myArray[s][x].length; y++) {
                Label label = new Label(myArray[s][x][y], new Rectangle(1, 6));
                pane.add(label, x, y);
            }
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Step number " + (s + 1));
//        stage.show();

//        s+=1;
    }

    private void skip10View(String[][][] myArray, Stage stage) {
        s += 10;
        if (s >= myArray.length) {
            s -= 10;
            return;
        }
        GridPane pane = new GridPane();

//        while(myArray[s])
        for (int x = 0; x < myArray[s].length; x++) {
            for (int y = 0; y < myArray[s][x].length; y++) {
                Label label = new Label(myArray[s][x][y], new Rectangle(1, 6));
                pane.add(label, x, y);
            }
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Step number " + (s + 1));
    }


//    private void print(Node node) {
//        System.out.println("Creating a printer job...");
//
//        PrinterJob job = PrinterJob.createPrinterJob();
//        if (job != null) {
//            System.out.println(job.jobStatusProperty().asString());
//
//            boolean printed = job.printPage(node);
//            if (printed) {
//                job.endJob();
//            } else {
//                System.out.println("Printing failed.");
//            }
//        } else {
//            System.out.println("Could not create a printer job.");
//        }
//    }
}
