package com.company;

public class Grass implements IObject{
    private Vector2d position;
    public boolean isAnimal(){
        return false;
    }
    public int getEnergy(){
        return 10;
    }
    public Grass(Vector2d position) {
        this.position=position;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return "g";
    }
    public int getX(){
        return this.getPosition().getX();
    }
    public int getY(){
        return this.getPosition().getY();
    }

//    public int compareTo(Object o) {
//        return this.getX()*1000+this.getY();
//    }
}
