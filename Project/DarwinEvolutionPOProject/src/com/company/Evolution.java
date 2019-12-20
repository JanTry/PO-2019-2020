package com.company;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Evolution {
    private int sizeX = 10;
    private int sizeY = 10;
    private int energy = 10;
    private int animalNumber = 10;
    private int grassEnergy = 10;
    private Map map;
    private GridPane VisualizationArray;
    private ObservableList<Integer> options;

    Evolution(int sizeX, int sizeY, int energy, int animalNumber, int grassEnergy, Stage stage, ObservableList<Integer> options) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.energy = energy;
        this.options = options;
        this.animalNumber = animalNumber;
        this.grassEnergy = grassEnergy;
        stage.setTitle("Step number 1");
        this.VisualizationArray = new GridPane();
        Generator generator = new Generator();
        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
                Rectangle rect = new Rectangle(6, 6);
                rect.setStroke(DrawType.BLANK.getColor());
                rect.setFill(DrawType.BLANK.getColor());
                Label label = new Label(" ", rect);
                this.VisualizationArray.add(label, x, y);
            }
        }
        this.map = new Map(this.sizeX, this.sizeY, this.grassEnergy, this.VisualizationArray, this.options);
        for (int i = 0; i < animalNumber; i++) {
            if (this.map.place(new Animal(generator.point(sizeX, sizeY), generator.genesArray(), energy, map.actualIndex))) {
                options.add(map.actualIndex);
                map.actualIndex += 1;
            }


        }
        Scene scene = new Scene(VisualizationArray);
        stage.setScene(scene);
    }

    boolean next(Text p, Text p2) {
        boolean flag = map.process();

        print1(p, p2);
        VisualizationArray = this.map.drawing();
        return flag;
    }

    private void print1(Text p, Text p2) {
        StringBuilder genes = new StringBuilder("[");
        for (int i = 0; i < 32; i++) {
            genes.append(this.map.genes[i]);
            genes.append(", ");
        }
        genes.append("]");
        String energy = "";
        energy += this.map.maxAnimalEnergy;
        p2.setText(energy);
        p.setText(String.valueOf(genes));

    }

    public Animal getAnimal(int index) {
        return map.getAnimal(index);
    }
}
