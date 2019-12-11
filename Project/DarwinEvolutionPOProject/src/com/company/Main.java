package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        int sizeX = 120;
        int sizeY = 40;
        int energy = 160;
        int animalNumber = 10;
        int grassEnergy = 50;


        VBox root = new VBox(5);
        TextArea text = new TextArea();
        text.setPrefRowCount(20);
        text.setPrefColumnCount(20);
        text.setWrapText(true);
        Text animalLable = new Text();
        animalLable.setText("Genes of Animal with the most energy:");
        Text topAnimalGenes = new Text();
        topAnimalGenes.setText("");
        Text blank1 = new Text();
        Text energyLabel = new Text();
        Text blank2 = new Text();
        energyLabel.setText("His energy is:");
        Text topAnimalEnergy = new Text();
        topAnimalEnergy.setText(" ");
        blank1.setX(20.0f);
        blank1.setY(65.0f);

        Evolution darwin = new Evolution(sizeX, sizeY, energy, animalNumber, grassEnergy, stage);
        stage.show();
        Text generalInfo1 = new Text();
        generalInfo1.setText("Size of map is: " + sizeX + " " + sizeY);
        Text generalInfo2 = new Text();
        generalInfo2.setText(animalNumber + " animals start with " + energy + " energy each");
        Text generalInfo3 = new Text();
        generalInfo3.setText("Every eaten grass gives " + grassEnergy + " energy");
        Boolean done = darwin.next(stage, topAnimalGenes, topAnimalEnergy);
        Button getNextStep = new Button("Next Step");
        getNextStep.setOnAction(e -> {
            try {
                darwin.next(stage, topAnimalGenes, topAnimalEnergy);
                s = s + 1;
                stage.setTitle("Step number " + (s + 1));
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        Button getSkippedStep = new Button("Skip 10 steps");
        getSkippedStep.setOnAction(e -> {
            try {
                darwin.next10(stage, topAnimalGenes, topAnimalEnergy);
                s = s + 10;
                stage.setTitle("Step number " + (s + 1));
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        Button getSkipped2Step = new Button("Skip 100 steps");
        getSkipped2Step.setOnAction(e -> {
            try {
                darwin.next100(stage, topAnimalGenes, topAnimalEnergy);
                s = s + 100;
                stage.setTitle("Step number " + (s + 1));
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        HBox buttonBox = new HBox(10, getNextStep, getSkippedStep, getSkipped2Step);
        Stage stage1 = new Stage();
        root.getChildren().addAll(generalInfo1, generalInfo2, generalInfo3, blank1, animalLable, topAnimalGenes, energyLabel, topAnimalEnergy, blank2, buttonBox);
        Scene scene2 = new Scene(root);
        stage1.setScene(scene2);
        stage1.setTitle("MAIN MENU");
        stage1.show();
    }

}
