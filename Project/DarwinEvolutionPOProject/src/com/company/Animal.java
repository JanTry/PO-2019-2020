package com.company;

import java.util.List;
//import java.util.List;

public class Animal implements IObject {
    boolean valid = true;
    private Vector2d position = new Vector2d(2, 2);
    private Direction direction = Direction.NORTH;
    private int energy;
    private Genes genes;

    Animal(Vector2d position, int[] tab, int energy) {
        this.position.x = position.getX();
        this.position.y = position.getY();
        this.energy = energy;
        genes = new Genes(tab);
    }

    private Animal(Vector2d position, int[] tab, int energy, Direction dir, boolean valid) {
        this.position.x = position.getX();
        this.position.y = position.getY();
        this.energy = energy;
        genes = new Genes(tab);
        this.direction = dir;
        this.valid = valid;
    }

    Direction getDirection() {
        return this.direction;
    }

    public Genes getGenes() {
        return this.genes;
    }

    public String toString() {
        return this.direction.toString();
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    private void rotate(int number) {
        for (int i = 0; i < number; i++) {
            this.direction = this.direction.next();
        }
    }

    void moveForward(Map map) {
        this.position = this.position.add(this.direction.toUnitVector());
        this.position = map.bound(this.position);
    }

    private void eatGrass(int grassEnergy) {
        this.energy += grassEnergy;
    }

    private Direction randomDirection() {
        Direction dir = Direction.NORTH;
        int p = (int) (Math.random() * 8);
        for (int i = 0; i < p; i++) {
            dir = dir.next();
        }
        return dir;
    }

    void process(Map map, List<Animal> animalsToMove) {

        this.rotate(this.genes.getRotation());
        Vector2d newPosition = this.position.add(this.direction.toUnitVector());

        newPosition = map.bound(newPosition); //Covers going out of edges

        if (map.canMoveTo(newPosition, this.objectType())) {
            if (map.isOccupied(newPosition)) {
                IObject object = map.myObjectAt(newPosition);
                if (object.objectType() == Type.GRASS) {
                    map.deleteFromPosition(newPosition);
                    this.eatGrass(map.getGrassEnergy());
                    map.replace(this, this.position, newPosition);
                    this.moveForward(map);
                } else if (object.getEnergy() >= map.getGrassEnergy() / 2 && this.getEnergy() >= map.getGrassEnergy() / 2) {
                    int[] tab = this.genes.combine(object.getGenes());
                    Direction temp = this.randomDirection();
                    Vector2d childPosition = newPosition.add(temp.toUnitVector());
                    childPosition = map.bound(childPosition);
                    int childEnergy = (this.getEnergy() / 4) + (object.getEnergy() / 4);
                    Animal animal = new Animal(childPosition, tab, childEnergy, temp, false);
                    animalsToMove.add(this);
                    for (int i = 0; i < 20 && !(map.place(animal)); i++) {
                        temp = this.randomDirection();
                        childPosition = newPosition.add(temp.toUnitVector());
                        childPosition = map.bound(childPosition);
                        animal = new Animal(childPosition, tab, childEnergy, temp, false);
                    }
                    this.setEnergy((this.getEnergy() * 3) / 4);
                    object.setEnergy(3 * object.getEnergy() / 4);
                    this.valid = false;
                }
                this.energy--;
                return;
            }
            map.replace(this, this.position, newPosition);
            this.position = newPosition;
        }
        this.energy--;
    }

    @Override
    public Type objectType() {
        return Type.ANIMAL;
    }
}
