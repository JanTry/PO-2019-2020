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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    int s = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        VBox root = new VBox(5);
        Label textLbl = new Label("                ");
        TextArea text = new TextArea();
        text.setPrefRowCount(20);
        text.setPrefColumnCount(20);
        text.setWrapText(true);
        Text pt = new Text();
        pt.setText("Genes of Animal with the most energy:");
        Text p = new Text();
        p.setText("Object programing project");
        Text t = new Text();
        Text p1 = new Text();
        Text t1 = new Text();
        p1.setText("His energy is:");
        Text p2 = new Text();
        p2.setText(" ");
        t.setX(20.0f);
        t.setY(65.0f);
        int sizeX=120;
        int sizeY=40;
        int energy=160;
        int animalNumber=10;
        int grassEnergy=50;
        Evolution darwin = new Evolution(sizeX, sizeY, energy, animalNumber, grassEnergy, stage);
        stage.show();
        Text generalInfo1= new Text();
        generalInfo1.setText("Size of map is: "+sizeX+" "+sizeY);
        Text generalInfo2= new Text();
        generalInfo2.setText(animalNumber+" animals start with "+energy+" energy each");
        Text generalInfo3= new Text();
        generalInfo3.setText("Every eaten grass gives "+grassEnergy+" energy");
        Boolean done = darwin.next(stage,p,p2);
        Button getNextStep = new Button("Next Step");
        getNextStep.setOnAction(e -> {
            try {
                darwin.next(stage,p,p2);
                s = s + 1;
                stage.setTitle("Step number " + (s+1));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        Button getSkippedStep = new Button("Skip 10 steps");
        getSkippedStep.setOnAction(e -> {
            try {
                darwin.next10(stage,p,p2);
                s = s + 10;
                stage.setTitle("Step number " + (s+1));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        Button getSkipped2Step = new Button("Skip 100 steps");
        getSkipped2Step.setOnAction(e -> {
            try {
                darwin.next100(stage,p,p2);
                s = s + 100;
                stage.setTitle("Step number " + (s+1));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        HBox buttonBox = new HBox(10, getNextStep, getSkippedStep, getSkipped2Step);
        Stage stage1 = new Stage();
        root.getChildren().addAll( generalInfo1,generalInfo2,generalInfo3, t, pt, p, p1, p2, t1, buttonBox);
        Scene scene2 = new Scene(root);
        stage1.setScene(scene2);
        stage1.setTitle("MAIN MENU");
        stage1.show();
    }

}


//package com.company;
////
////import java.io.IOException;
////
////public class Main {
////    public static void main(String[] args) throws IOException {
////        Evolution darwin = new Evolution(50, 30, 40, 10, 40);
////        darwin.run(100);
////    }
////}
///* w  w  w . j ava2  s.c om*/
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//import static java.lang.Thread.sleep;
//
//public class Main extends Application {
//
//    int s = 0;
//
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws IOException, InterruptedException {
//        VBox root = new VBox(5);
//        Label textLbl = new Label("                ");
//        TextArea text = new TextArea();
//        text.setPrefRowCount(20);
//        text.setPrefColumnCount(20);
//        text.setWrapText(true);
//        Text p = new Text();
//        p.setText("Object programing project");
//        Text t = new Text();
//        t.setX(20.0f);
//        t.setY(65.0f);
//        GridPane pane = new GridPane();
//        Evolution darwin = new Evolution(90, 50, 90, 30, 60);
//        int presetSteps = 5000; //And presetSteps
//        DrawType[][][] myArray = darwin.run(presetSteps);
//        print(myArray,stage);
//
//        Button getNextStep = new Button("Next Step");
//        getNextStep.setOnAction(e -> nextView(myArray, stage));
//        Button getPreviousStep = new Button("Previous Step");
//        getPreviousStep.setOnAction(e -> prevView(myArray, stage));
//        Button getSkippedStep = new Button("Skip 10");
//        getSkippedStep.setOnAction(e -> skip10View(myArray, stage));
//
//        HBox buttonBox = new HBox(10, getPreviousStep, getNextStep, getSkippedStep);
//
//        root.getChildren().addAll(textLbl,p, t, buttonBox);
//        Scene scene = new Scene(pane);
//        Scene scene2 = new Scene(root);
//        Stage stage1 = new Stage();
//        stage1.setScene(scene2);
//        stage1.setTitle("MAIN MENU");
//
////        stage.setScene(scene);
//        stage.setTitle("Step number 1");
//        stage.show();
//        stage1.show();
////        sleep(10000);
////        while(s<1000){
////            nextView(myArray,stage);
////            sleep(100);
////        }
////        stage.setScene(scene2);
//    }
//
//    private void prevView(DrawType[][][] myArray, Stage stage) {
//        s--;
//        if (s < 0) {
//            s++;
//            return;
//        }
//        print(myArray,stage);
//        stage.setTitle("Step number " + (s + 1));
//    }
//
//    private void nextView(DrawType[][][] myArray, Stage stage) {
//        s++;
//        if (s >= myArray.length) {
//            s--;
//            return;
//        }
//        print(myArray,stage);
//        stage.setTitle("Step number " + (s + 1));
//    }
//
//    private void skip10View(DrawType[][][] myArray, Stage stage) {
//        s += 10;
//        if (s >= myArray.length) {
//            s -= 10;
//            return;
//        }
//        print(myArray,stage);
//        stage.setTitle("Step number " + (s + 1));
//    }
//
//
//    private void print(DrawType[][][] myArray, Stage stage){
//        GridPane pane = new GridPane();
//        for (int x = 0; x < myArray[s].length; x++) {
//            for (int y = 0; y < myArray[s][x].length; y++) {
//                Rectangle rect= new Rectangle(10,10);
//                rect.setStroke(Color.TRANSPARENT);
//                rect.setFill(myArray[s][x][y].getColor());
//                Label label = new Label(" ", rect);
//                pane.add(label, x, y);
//            }
//        }
//        Scene scene = new Scene(pane);
//        stage.setScene(scene);
//    }
////    private void print(Node node) {
////        System.out.println("Creating a printer job...");
////
////        PrinterJob job = PrinterJob.createPrinterJob();
////        if (job != null) {
////            System.out.println(job.jobStatusProperty().asString());
////
////            boolean printed = job.printPage(node);
////            if (printed) {
////                job.endJob();
////            } else {
////                System.out.println("Printing failed.");
////            }
////        } else {
////            System.out.println("Could not create a printer job.");
////        }
////    }
//}