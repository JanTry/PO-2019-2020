package com.company;

import javafx.scene.paint.Color;
//Way of coloring animals
public enum DrawType {
    ANIMAL,
    GRASS,
    BLANK,
    TiredAnimal,
    AboutToDieAnimal,
    TopAnimal,
    ChoosenAnimal,
    VeryTiredAnimal,
    VeryVeryTiredAnimal,
    VeryVeryVeryTiredAnimal;

    public Color getColor() {
        switch (this) {
            case ANIMAL:
                return Color.DARKRED;
            case GRASS:
                return Color.GREEN;
            case BLANK:
                return Color.WHITE;
            case TiredAnimal:
                return Color.ORANGERED;
            case AboutToDieAnimal:
                return Color.BLACK;
            case TopAnimal:
                return Color.GOLDENROD;
            case ChoosenAnimal:
                return Color.BLUE;
            case VeryTiredAnimal:
                return Color.INDIANRED;
            case VeryVeryTiredAnimal:
                return Color.DARKGRAY;
            case VeryVeryVeryTiredAnimal:
                return Color.GRAY;
        }
        return Color.WHITE;
    }
}
