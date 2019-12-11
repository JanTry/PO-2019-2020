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

    public boolean next(Stage stage, Text p, Text p2) throws IOException, InterruptedException {
        boolean flag=map.process();
        print1(this.VisualizationArray, stage,p, p2);
        VisualizationArray=this.map.drawing();
        return flag;
    }

    public boolean next10(Stage stage , Text p, Text p2)
            throws IOException, InterruptedException {
        for(int i=0;i<10 && map.process(); i++);
        print1(this.VisualizationArray, stage,p, p2);
        VisualizationArray = this.map.drawing();
        return true;
    }
    public boolean next100(Stage stage, Text p , Text p2)
            throws IOException, InterruptedException {
        for(int i=0;i<100 && map.process(); i++);
        print1(this.VisualizationArray, stage,p,p2);
        VisualizationArray = this.map.drawing();
        return true;
    }

    private void print1 (DrawType[][] VisualizationArray, Stage stage, Text p, Text p2) throws InterruptedException {
        GridPane pane = new GridPane();
        for (int x = 0; x < VisualizationArray.length; x++) {
            for (int y = 0; y < VisualizationArray[x].length; y++) {
                if (false) throw new InterruptedException();
                Rectangle rect = new Rectangle(6, 6);
                rect.setStroke(Color.TRANSPARENT);
                DrawType vis=VisualizationArray[x][y];
                if(vis==null)vis=DrawType.BLANK;
                Color color=vis.getColor();
                rect.setFill(color);
                Label label = new Label(" ", rect);
                pane.add(label, x, y);
            }
        }
        String genes="[";
        for(int i=0;i<32;i++) {
            genes += this.map.genes[i];
            genes += ", ";
        }
        genes+="]";
        String energy="";
        energy+=this.map.maxAnimalEnergy;
        p2.setText(energy);
        p.setText(genes);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }
}



//package com.company;
//
//import java.io.IOException;
//
//public class Evolution {
//    int sizeX = 10;
//    int sizeY = 10;
//    int energy = 10;
//    int animalNumber = 10;
//    int grassEnergy = 10;
//
//    public Evolution(int sizeX, int sizeY, int energy, int animalNumber, int grassEnergy) {
//        this.sizeX = sizeX;
//        this.sizeY = sizeY;
//        this.energy = energy;
//        this.animalNumber = animalNumber;
//        this.grassEnergy = grassEnergy;
//    }
//
//    public DrawType[][][] run(int presetSteps) throws IOException {
//        DrawType[][][] o=new DrawType[presetSteps][][];
//        Generator generator = new Generator();
//        Map map = new Map(this.sizeX, this.sizeY, this.grassEnergy);
//        for (int i = 0; i < animalNumber; i++) {
//            map.place(new Animal(generator.point(sizeX, sizeY), generator.genesArray(), energy));
//        }
////        map.draw();
//        for (int i = 0; i < presetSteps; i++) {
//            if (!map.process()) break;
//            o[i]=map.drawing();
////            map.draw();
////            System.out.println("That was step number " + i);
//        }
//
////        map.draw();
////        while (true) {
//////            System.in.read();
////            if (!map.process()) break;
//////            map.draw();
////        }
//        return o;
//    }
//}
