package com.company;

public interface IObject {
    public Type objectType();
    int getEnergy();
    Vector2d getPosition();
    String toString();
    void setEnergy(int energy);
    Genes getGenes();
}
