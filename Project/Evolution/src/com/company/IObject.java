package com.company;

public interface IObject {
    Type objectType();

    int getEnergy();

    void setEnergy(int energy);

    Vector2d getPosition();

    String toString();

    Genes getGenes();
}
