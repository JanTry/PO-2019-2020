package com.company;

import javafx.scene.paint.Color;

public enum DrawType {
    ANIMAL,
    GRASS,
    BLANK,
    TiredAnimal,
    AboutToDieAnimal;

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
        }
        return Color.WHITE;
    }
}
