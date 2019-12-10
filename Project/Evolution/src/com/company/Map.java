package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Map {
    List<Animal> animals = new LinkedList<Animal>();
    List<Grass> grasses = new LinkedList<Grass>();
    public int sizeX;
    public int sizeY;
    private HashMap<Vector2d, IObject> hashMap = new HashMap<Vector2d, IObject>();
    private int grassEnergy;

    public Map(int sizeX, int sizeY, int grassEnergy) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.grassEnergy = grassEnergy;
    }

    public int getGrassEnergy() {
        return this.grassEnergy;
    }

    public boolean isOccupied(Vector2d position) { //Checks if there is anything on this spot
        return hashMap.containsKey(position);
    }

    public boolean canMoveTo(Vector2d position, Type type) { //Checks
        if (!isOccupied(position)) return true;
        return type != Type.GRASS;
    }

    public boolean place(IObject object) {
        if (isOccupied(object.getPosition())) return false;
        if (object.objectType() == Type.GRASS) grasses.add((Grass) object);
        else animals.add((Animal) object);
        hashMap.put(object.getPosition(), object);
        return true;
    }

    public void replace(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        hashMap.remove(oldPosition);
        hashMap.put(newPosition, animal);
    }

    public Object objectAt(Vector2d position) {
        return hashMap.get(position);
    }

    public IObject myObjectAt(Vector2d position) {
        return hashMap.get(position);
    }

    public void deleteFromPosition(Vector2d position) {
        IObject t = hashMap.get(position);
        hashMap.remove(position);
        if (t.objectType() == Type.GRASS) {
            grasses.remove(t);
        } else
            animals.remove(t);
    }

    public void draw() {
        MapVisualizer a = new MapVisualizer(this);
        System.out.println(a.draw(new Vector2d(0, 0), new Vector2d(this.sizeX, this.sizeY)));
    }

    public String[][] drawing(){
        AppVisualizer a = new AppVisualizer(this);
        return a.draw();
    }

    public void delete(Animal animal) {
        hashMap.remove(animal.getPosition());
        animals.remove(animal);
    }

    public void deleteDead() {
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

    public boolean process() { //Basic part
        this.deleteDead();
//        System.out.println(animals.size());
        if (animals.size() == 0) return false;
        int size = animals.size();
        Animal animal;
        while (animals.get(0).valid) {
            animal = animals.get(0);
            animals.remove(0);
            animal.process(this);
            animal.valid = false;
            animals.add(animal);
        }
        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).valid = true;
        }
        Generator tmp = new Generator();
        for (int i = 0; i < 30 && !this.place(new Grass(tmp.outsidePoint(sizeX, sizeY))); i++)
            ; //To make it more realistic - the grass wont spawn every time
        for (int i = 0; i < 30 && !this.place(new Grass(tmp.junglePoint(sizeX, sizeY))); i++)
            ; //It might not spawn if there is too much grass all around.
//         I stayed with 30 tries, as it was efficient enough
//        Now, when there are not many animals on a big map we might see their path in estimation as the map kind of shows where it was
        return true;
    }

    public Vector2d bound(Vector2d position) {
        Vector2d newPosition = new Vector2d((position.x + this.sizeX + 1) % (this.sizeX + 1), (position.y + this.sizeY + 1) % (this.sizeY + 1));

        return newPosition;
    }
}
