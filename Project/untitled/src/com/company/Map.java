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

    public boolean canMoveTo(Vector2d position, Type type){
        if(!isOccupied(position))return true;
        IObject p=hashMap.get(position);
        if(p.isAnimal()) {
            if(p.getEnergy()>4) return true;
            else return false;
        }
        return true;
    }

}
