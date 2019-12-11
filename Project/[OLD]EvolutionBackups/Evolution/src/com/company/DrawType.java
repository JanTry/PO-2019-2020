package com.company;

import javafx.scene.paint.Color;

public enum DrawType {
    ANIMAL,
    GRASS,
    BLANK,
    TiredAnimal,
    AboutToDieAnimal,
    TopAnimal;

    public Color getColor(){
        switch(this){
            case ANIMAL:
                return Color.DARKRED;
            case GRASS:
                return Color.GREEN;
            case BLANK:
                return Color.WHITE;
            case TiredAnimal:
                return Color.INDIANRED;
            case AboutToDieAnimal:
                return Color.BLACK;
            case TopAnimal:
                return Color.GOLDENROD;
        }
        return Color.WHITE;
    }
}
