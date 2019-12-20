package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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


public class Main extends Application {
    private int s = 0;

    public static void main(String[] args) {
        Application.launch(args);
    }



    @Override
    public void start(Stage stage) {
        //Starting values! :D
        int sizeX = 120;
        int sizeY = 40;
        int energy = 40;
        int animalNumber = 200;
        int grassEnergy = 40;
        Darwin(stage, sizeX, sizeY, energy, animalNumber, grassEnergy);
    }

    public Text getText(String inside) {
        Text text = new Text();
        text.setText(inside);
        return text;
    }

    public void Darwin(Stage stage,
                       int sizeX,
                       int sizeY,
                       int energy,
                       int animalNumber,
                       int grassEnergy) {
        //Text Labels- nothing important
        VBox root = new VBox(5);
        TextArea text = new TextArea();
        text.setPrefRowCount(20);
        text.setPrefColumnCount(20);
        text.setWrapText(true);
        Text animalLable = getText("Genes of Animal with the most energy:                                                            ");
        Text topAnimalGenes = getText("");
        Text blank1 = getText("");
        Text energyLabel = getText("His energy is:");
        Text blank2 = getText("");
        Text choosenAnimalPower = getText("This animal power is: ");
        Text choosenLabel = getText("His genes: ");
        Text choosenAnimalGenes = getText("");
        Text topAnimalEnergy = getText("");
        Text pickedAnimal = getText("You picked animal number -1");
        Text generalInfo1 = getText("Size of map is: " + sizeX + " " + sizeY);
        Text generalInfo2 = getText(animalNumber + " animals start with " + energy + " energy each");
        Text generalInfo3 = getText("Every eaten grass gives " + grassEnergy + " energy");
        //End of getting TextLabels
        blank1.setX(20.0f);
        blank1.setY(65.0f);
        ObservableList<Integer> options =
                FXCollections.observableArrayList(
                );
        Integer[] animalID = {-1};
        Evolution darwin = new Evolution(sizeX, sizeY, energy, animalNumber, grassEnergy, stage, options);
        ComboBox<Integer> comboBox = new ComboBox<>(options);
        comboBox.setPromptText("Pick an animal to watch");
        comboBox.setEditable(false);
        comboBox.valueProperty().addListener((ov, t, t1) -> {
            animalID[0] = t1;
            Animal a;
            if (animalID[0] == null) a = null;
            else a = darwin.getAnimal(animalID[0]);
            if (a != null) {
                pickedAnimal.setText("You picked animal number " + animalID[0]);
                choosenAnimalPower.setText("This animal power is: " + a.getEnergy());
                choosenAnimalGenes.setText(a.getGenes().toString());
            } else {
                pickedAnimal.setText("You picked animal number -1");
                choosenAnimalPower.setText("This animal power is: ");
                choosenAnimalGenes.setText(" ");
            }
        });
        stage.show();
        AnimationTimer programButtonAnimation = new AnimationTimer() {
            private long lastUpdate = 0;

            public void handle(long now) {
                if (now - lastUpdate >= 28_000_000) {
                    if (!darwin.next(topAnimalGenes, topAnimalEnergy)) {
                        stop();
                        throw new RuntimeException("Procesor się buntuje! Mówi, że nie będzie ciężko pracował symulując, skoro i tak nie ma zwierząt na mapie");
                    }
                    s += 1;
                    stage.setTitle("Step number " + (s + 1));
                    Animal a;
                    if (animalID[0] == null) a = null;
                    else a = darwin.getAnimal(animalID[0]);
                    if (a != null) {
                        choosenAnimalPower.setText("This animal power is: " + a.getEnergy());
                        choosenAnimalGenes.setText(a.getGenes().toString());
                    } else {
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
        //A lot of text labels coming to root :D
        root.getChildren().addAll(generalInfo1, generalInfo2, generalInfo3, blank1, animalLable, topAnimalGenes, energyLabel, topAnimalEnergy, blank2, buttonBox, comboBox, pickedAnimal, choosenAnimalPower, choosenLabel, choosenAnimalGenes);
        Scene scene2 = new Scene(root);
        stage1.setScene(scene2);
        stage1.setTitle("MAIN MENU");
        stage1.show();
    }
}
