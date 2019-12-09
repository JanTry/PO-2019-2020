package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Map {
    private int sizeX;
    private int sizeY;
    List<Animal> animals = new LinkedList<Animal>();
    List<Grass> grasses = new LinkedList<Grass>();
    private HashMap<Vector2d,  IObject> hashMap = new HashMap<Vector2d, IObject>();
    public Map(int sizeX, int sizeY){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }
    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }

    private boolean isOccupied(Vector2d position){ //Checks if there is anything on this spot
        return hashMap.containsKey(position);
    }

    public boolean canMoveTo(Vector2d position, Type type){ //Checks
        if(!isOccupied(position))return true;
        IObject p=hashMap.get(position); //And what about 2 already in 1 place?
        return type!=Type.GRASS;
    }

    public boolean place(IObject object){
        if(!canMoveTo(object.getPosition(), object.objectType())) return false;
        if(object.objectType()==Type.GRASS) grasses.add((Grass)object);
        else animals.add((Animal)object);
        return true;
    }
}
