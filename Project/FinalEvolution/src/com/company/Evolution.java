package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Evolution {
    int sizeX = 10;
    int sizeY = 10;
    int energy = 10;
    int animalNumber = 10;
    int grassEnergy = 10;
    Map map;
    DrawType[][] VisualizationArray;

    public Evolution(int sizeX, int sizeY, int energy, int animalNumber, int grassEnergy, Stage stage) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.energy = energy;
        this.animalNumber = animalNumber;
        this.grassEnergy = grassEnergy;

        stage.setTitle("Step number 1");


        this.VisualizationArray= new DrawType[sizeX+1][sizeY+1];
        Generator generator = new Generator();
        for (int i = 0; i <= sizeX; i++)
            for (int j = 0; i <= sizeY; i++)
                this.VisualizationArray[i][j]=DrawType.BLANK;
        this.map = new Map(this.sizeX, this.sizeY, this.grassEnergy, this.VisualizationArray);
        for (int i = 0; i < animalNumber; i++) {
            this.map.place(new Animal(generator.point(sizeX, sizeY), generator.genesArray(), energy));
        }

    }

    public boolean next(Stage stage) throws IOException, InterruptedException {
        print1(this.VisualizationArray, stage);
        this.map.draw();
        return map.process();
    }


//    public void prevView(DrawType[][][] myArray, Stage stage) {
//        s--;
//        if (s < 0) {
//            s++;
//            return;
//        }
//        print(myArray,stage);
//        stage.setTitle("Step number " + (s + 1));
//    }
//
//    public void nextView(DrawType[][][] myArray, Stage stage) {
//        s++;
//        if (s >= myArray.length) {
//            s--;
//            return;
//        }
//        print(myArray,stage);
//        stage.setTitle("Step number " + (s + 1));
//    }
//
//    public void skip10View(DrawType[][][] myArray, Stage stage) {
//        s += 10;
//        if (s >= myArray.length) {
//            s -= 10;
//            return;
//        }
//        print(myArray,stage);
//        stage.setTitle("Step number " + (s + 1));
//    }

    private void print1 (DrawType[][] VisualizationArray, Stage stage) throws InterruptedException {
        GridPane pane = new GridPane();
        for (int x = 0; x < VisualizationArray.length; x++) {
            for (int y = 0; y < VisualizationArray[x].length; y++) {
                if (false) throw new InterruptedException();
                Rectangle rect = new Rectangle(10, 10);
                rect.setStroke(Color.TRANSPARENT);
                rect.setFill(DrawType.ANIMAL.getColor());
//                rect.setFill(VisualizationArray[x][y].getColor());
//                rect.setFill(myArray[s][x][y].getColor());
                Label label = new Label(" ", rect);
                pane.add(label, x, y);
            }
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }
}
