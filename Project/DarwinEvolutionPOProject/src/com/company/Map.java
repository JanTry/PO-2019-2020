package com.company;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Map {
    int[] genes = new int[32];
    int maxAnimalEnergy = 0;
    private int sizeX;
    private int sizeY;
    private List<Animal> animals = new LinkedList<Animal>();
    private GridPane VisualizationArray;
    private HashMap<Vector2d, IObject> hashMap = new HashMap<Vector2d, IObject>();
    private int grassEnergy;


    Map(int sizeX, int sizeY, int grassEnergy, GridPane VisualizationArray) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.grassEnergy = grassEnergy;
        this.VisualizationArray = VisualizationArray;
    }

    int getGrassEnergy() {
        return this.grassEnergy;
    }

    boolean isOccupied(Vector2d position) { //Checks if there is anything on this spot
        return hashMap.containsKey(position);
    }

    boolean canMoveTo(Vector2d position, Type type) { //Checks
        if (!isOccupied(position)) return true;
        return type != Type.GRASS;
    }

    Label getLabel(DrawType type){
        Rectangle rect = new Rectangle(6, 6);
        rect.setStroke(type.getColor());
        rect.setFill(type.getColor());
        Label label = new Label(" ", rect);
        return label;
    }

    boolean place(IObject object) {
        if (isOccupied(object.getPosition())) return false;
        if (object.objectType() == Type.GRASS) {
            Label label=getLabel(DrawType.GRASS);
            this.VisualizationArray.add(label, object.getPosition().getX(), object.getPosition().getY());
//            this.VisualizationArray.add() = DrawType.GRASS;
        } else {
            animals.add((Animal) object);
            if (object.getEnergy() > this.getGrassEnergy()) {
                Label label=getLabel(DrawType.ANIMAL);
                this.VisualizationArray.add(label, object.getPosition().getX(), object.getPosition().getY());
//                this.VisualizationArray[object.getPosition().getX()][object.getPosition().getY()] = DrawType.ANIMAL;
            }
            else {
                Label label=getLabel(DrawType.TiredAnimal);
                this.VisualizationArray.add(label, object.getPosition().getX(), object.getPosition().getY());
//                this.VisualizationArray[object.getPosition().getX()][object.getPosition().getY()] = DrawType.TiredAnimal;
            }
        }
        hashMap.put(object.getPosition(), object);

        return true;
    }

    void replace(Animal animal, Vector2d oldPosition, Vector2d newPosition) {

        this.bound(oldPosition);
        this.bound(newPosition);
        if(newPosition==oldPosition){
            return;
        }
        hashMap.remove(oldPosition);
//        System.out.println("replace... "+oldPosition.getX()+" "+oldPosition.getY());
        Label label2=getLabel(DrawType.BLANK);
        this.VisualizationArray.add(label2, oldPosition.getX(), oldPosition.getY());
//        this.VisualizationArray[oldPosition.getX()][oldPosition.getY()] = DrawType.BLANK;
        hashMap.put(newPosition, animal);
        if (animal.getEnergy() > this.getGrassEnergy()){
            Label label1=getLabel(DrawType.ANIMAL);
            this.VisualizationArray.add(label1, newPosition.getX(), newPosition.getY() );
        }
        else{
            Label label1=getLabel(DrawType.TiredAnimal);
            this.VisualizationArray.add(label1, newPosition.getX(), newPosition.getY() );
//            this.VisualizationArray[newPosition.getX()][newPosition.getY()] = DrawType.TiredAnimal;
        }
    }

    IObject myObjectAt(Vector2d position) {
        return hashMap.get(position);
    }

    void deleteFromPosition(Vector2d position) {
        IObject t = hashMap.get(position);
        hashMap.remove(position);
        if (t.objectType() == Type.ANIMAL)
            animals.remove(t);
        Label label=getLabel(DrawType.BLANK);
        this.VisualizationArray.add(label, position.getX(), position.getY() );
//        this.VisualizationArray[position.getX()][position.getY()] = DrawType.BLANK;
    }

    GridPane drawing() {
        return this.VisualizationArray;
    }

    private void delete(Animal animal) {
        hashMap.remove(animal.getPosition());
        animals.remove(animal);
        Label label=getLabel(DrawType.BLANK);
        System.out.println("DELETE");
        this.VisualizationArray.add(label, animal.getPosition().getX(), animal.getPosition().getY());
//        this.VisualizationArray[animal.getPosition().getX()][animal.getPosition().getY()] = DrawType.BLANK;
    }

    private void deleteDead() {
        int size = animals.size();
        Animal animal;
        for (int i = 0; i < size; i++) {
            animal = animals.get(0);
            animals.remove(0);
            animals.add(animal);
            if (!(animal.getEnergy() > 0)) {
                this.delete(animal);
            }
        }
    }

    boolean process() { //Basic part
        int max_energy = 0;
        Animal topAnimal = animals.get(0);
        List<Animal> animalsToMove = new LinkedList<Animal>();
        this.deleteDead();
        if (animals.size() == 0) return false;
        int size = animals.size();
        Animal animal;
        while (animals.get(0).valid) {
            animal = animals.get(0);
            animals.remove(0);
            animal.process(this, animalsToMove);
            animal.valid = false;
            animals.add(animal);
            if (animal.getEnergy() > max_energy) {
                topAnimal = animal;
                max_energy = animal.getEnergy();
            }
        }
        for (Animal value : animals) {
            value.valid = true;
        }
        Generator tmp = new Generator();
        for (int i = 0; i < 30 && !this.place(new Grass(tmp.outsidePoint(sizeX, sizeY))); i++)
            ; //To make it more realistic - the grass wont spawn every time
        for (int i = 0; i < 30 && !this.place(new Grass(tmp.junglePoint(sizeX, sizeY))); i++)
            ; //It might not spawn if there is too much grass all around.
//         I stayed with 30 tries, as it was efficient enough
//        Now, when there are not many animals on a big map we might see their path in estimation as the map kind of shows where it was
        for (Animal a : animalsToMove) {
            Vector2d newPosition = a.getPosition().add(a.getDirection().toUnitVector());
            newPosition = this.bound(newPosition);
            if (!this.isOccupied(newPosition)) {
                this.replace(a, a.getPosition(), newPosition);
                a.moveForward(this);
            }
        }
        animalsToMove.clear();
        Label label=getLabel(DrawType.TopAnimal);
        this.VisualizationArray.add(label, topAnimal.getPosition().getX(), topAnimal.getPosition().getY() );
//        this.VisualizationArray[topAnimal.getPosition().getX()][topAnimal.getPosition().getY()] = DrawType.TopAnimal;
        this.genes = topAnimal.getGenes().getGenes();
        this.maxAnimalEnergy = topAnimal.getEnergy();
        return true;
    }

    Vector2d bound(Vector2d position) {

        return new Vector2d((position.x + this.sizeX + 1) % (this.sizeX + 1), (position.y + this.sizeY + 1) % (this.sizeY + 1));
    }
}
