package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        animalLable.setText("Genes of Animal with the most energy:                                                            ");
        Text topAnimalGenes = new Text();
        topAnimalGenes.setText("");
        Text blank1 = new Text();
        Text energyLabel = new Text();
        Text blank2 = new Text();
        energyLabel.setText("His energy is:");
        Text choosenAnimalPower = new Text();
        choosenAnimalPower.setText("This animal power is: ");
        Text choosenLabel=new Text();
        choosenLabel.setText("His genes: ");
        Text choosenAnimalGenes = new Text();
        choosenAnimalGenes.setText("");
        Text topAnimalEnergy = new Text();
        topAnimalEnergy.setText(" ");
        blank1.setX(20.0f);
        blank1.setY(65.0f);
        ObservableList<Integer> options =
                FXCollections.observableArrayList(
                );
        options.add(6);
        Integer[] animalID = {-1};
        Text pickedAnimal = new Text();
        pickedAnimal.setText("You picked animal number "+animalID[0]);
//        Text pickedAnimal = new Text();
//        pickedAnimal.setText("You picked animal number "+animalID[0]);

        Evolution darwin = new Evolution(sizeX, sizeY, energy, animalNumber, grassEnergy, stage, options);
//        Boolean done = darwin.next(topAnimalGenes, topAnimalEnergy);
        ComboBox<Integer> comboBox = new ComboBox<>(options);
        comboBox.setPromptText("Pick an animal to watch");
        comboBox.setEditable(false);
        comboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
                animalID[0] = t1;
                pickedAnimal.setText("You picked animal number "+animalID[0]);
                Animal a=darwin.getAnimal(animalID[0]);
                if(a!=null){
                    choosenAnimalPower.setText("This animal power is: "+a.getEnergy());
                    choosenAnimalGenes.setText(a.getGenes().toString());
                }
                else{
                    choosenAnimalPower.setText("This animal power is: ");
                    choosenAnimalGenes.setText(" ");
                }
            }
        });
        stage.show();
        Text generalInfo1 = new Text();
        generalInfo1.setText("Size of map is: " + sizeX + " " + sizeY);
        Text generalInfo2 = new Text();
        generalInfo2.setText(animalNumber + " animals start with " + energy + " energy each");
        Text generalInfo3 = new Text();
        generalInfo3.setText("Every eaten grass gives " + grassEnergy + " energy");


        AnimationTimer programButtonAnimation = new AnimationTimer(){
            private long lastUpdate = 0;
            public void handle(long now){
                if(now - lastUpdate >= 28_000_000){
                    darwin.next(topAnimalGenes, topAnimalEnergy);
                    s+=1;
                    stage.setTitle("Step number " + (s + 1));
                    Animal a=darwin.getAnimal(animalID[0]);
                    if(a!=null){
                        choosenAnimalPower.setText("This animal power is: "+a.getEnergy());
                        choosenAnimalGenes.setText(a.getGenes().toString());
                    }
                    else{
                        choosenAnimalPower.setText("This animal power is: ");
                        choosenAnimalGenes.setText(" ");
                    }
                }
            }
        };
        Button start = new Button("Start the animation");
        start.setOnAction(e -> programButtonAnimation.start());
        Button stop = new Button("Stop the animation");
        stop.setOnAction(e -> programButtonAnimation.stop());
        HBox buttonBox = new HBox(10, start, stop);
        Stage stage1 = new Stage();
        root.getChildren().addAll(generalInfo1, generalInfo2, generalInfo3, blank1, animalLable, topAnimalGenes, energyLabel, topAnimalEnergy, blank2, buttonBox, comboBox, pickedAnimal,choosenAnimalPower,choosenLabel,choosenAnimalGenes);
        Scene scene2 = new Scene(root);
        stage1.setScene(scene2);
        stage1.setTitle("MAIN MENU");
        stage1.show();



    }

}
