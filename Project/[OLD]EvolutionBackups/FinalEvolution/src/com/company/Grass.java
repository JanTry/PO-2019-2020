package com.company;

public class Grass implements IObject {
    private Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public Type objectType() {
        return Type.GRASS;
    }

    public int getEnergy() {
        return 10;
    }

    public void setEnergy(int energy) {
        throw new IllegalStateException("Unexpected setEnergy call at GRASS element");
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString() {
        return "g";
    }

    public int getX() {
        return this.getPosition().getX();
    }

    public int getY() {
        return this.getPosition().getY();
    }

    public Genes getGenes() {
        throw new IllegalStateException("Unexpected getGenes call at GRASS element");
    }

}
