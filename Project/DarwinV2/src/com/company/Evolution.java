package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Evolution {
    private int sizeX = 10;
    private int sizeY = 10;
    private int energy = 10;
    private int animalNumber = 10;
    private int grassEnergy = 10;
    private Map map;
    private DrawType[][] VisualizationArray;
    private int recSize= 3;
    GridPane pane = new GridPane();

    Evolution(int sizeX, int sizeY, int energy, int animalNumber, int grassEnergy, Stage stage, int recSize) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.energy = energy;
        this.animalNumber = animalNumber;
        this.grassEnergy = grassEnergy;
        this.recSize=recSize;

        stage.setTitle("Step number 1");
        this.VisualizationArray = new DrawType[sizeX + 1][sizeY + 1];
        Generator generator = new Generator();
        for (int i = 0; i <= sizeX; i++)
            for (int j = 0; i <= sizeY; i++)
                this.VisualizationArray[i][j] = DrawType.BLANK;
        this.map = new Map(this.sizeX, this.sizeY, this.grassEnergy, this.VisualizationArray);
        for (int i = 0; i < animalNumber; i++) {
            this.map.place(new Animal(generator.point(sizeX, sizeY), generator.genesArray(), energy));
        }
        for (int x = 0; x < VisualizationArray.length; x++) {
            for (int y = 0; y < VisualizationArray[x].length; y++) {
                Rectangle rect = new Rectangle(recSize, recSize);
//                rect.setStroke(Color.TRANSPARENT);
//                rect.setStroke(Color.RED);
                DrawType vis = VisualizationArray[x][y];
                if (vis == null) vis = DrawType.BLANK;
                Color color = vis.getColor();
                rect.setFill(color);
                rect.setStroke(color);
                Label label = new Label(" ", rect);
                pane.add(label, x, y);
            }
        }

    }

    boolean next(Stage stage, Text p, Text p2) throws IOException, InterruptedException {
        boolean flag = map.process(pane, recSize);
        print1(this.VisualizationArray, stage, p, p2);
        VisualizationArray = this.map.drawing();
        return flag;
    }

//    void next10(Stage stage, Text p, Text p2)
//            throws IOException, InterruptedException {
//        for (int i = 0; i < 10 && map.process(); i++) ;
//        print1(this.VisualizationArray, stage, p, p2);
//        VisualizationArray = this.map.drawing();
//    }
//
//    public boolean next100(Stage stage, Text p, Text p2)
//            throws IOException, InterruptedException {
//        for (int i = 0; i < 100 && map.process(); i++) ;
//        print1(this.VisualizationArray, stage, p, p2);
//        VisualizationArray = this.map.drawing();
//        return true;
//    }

    private void print1(DrawType[][] VisualizationArray, Stage stage, Text p, Text p2) throws InterruptedException {
//        GridPane pane = new GridPane();
//        for (int x = 0; x < VisualizationArray.length; x++) {
//            for (int y = 0; y < VisualizationArray[x].length; y++) {
//                Rectangle rect = new Rectangle(recSize, recSize);
////                rect.setStroke(Color.TRANSPARENT);
////                rect.setStroke(Color.RED);
//                DrawType vis = VisualizationArray[x][y];
//                if (vis == null) vis = DrawType.BLANK;
//                Color color = vis.getColor();
//                rect.setFill(color);
//                rect.setStroke(color);
//                Label label = new Label(" ", rect);
//                pane.add(label, x, y);
//            }
//        }

        StringBuilder genes = new StringBuilder("[");
        for (int i = 0; i < 32; i++) {
            genes.append(this.map.genes[i]);
            genes.append(", ");
        }
        genes.append("]");
        String energy = "";
        energy += this.map.maxAnimalEnergy;
        p2.setText(energy);
        p.setText(genes.toString());
//        Rectangle r=new Rectangle(10, 10);
//        r.setFill(Color.RED);
//        r.setStroke(Color.RED);
//        pane.add(new Label(" ",r), 3, 1);  // column=3 row=1
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }
}
