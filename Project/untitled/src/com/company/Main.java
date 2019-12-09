package com.company;

import javax.print.attribute.SetOfIntegerSyntax;
import java.io.Console;
import java.io.IOException;

public class Main {
    public static int[] generate(){
        int[] tab=new int[32];
        for(int i=0;i<32;i++){
            tab[i]=(int)(Math.random()*8);
        }
        return tab;
    }
    public static void main(String[] args) throws IOException {
	// write your code here
        int sizeX=15;
        int sizeY=15;
        int energy=30;
        Generator generator=new Generator();
        System.out.println("It does compile");
        System.out.println("At least for now");
        Map map=new Map(sizeX,sizeY);
        int steps=10000;
        int animalNumber=15;
        for(int i =0;i<animalNumber;i++){
            map.place(new Animal(generator.point(sizeX,sizeY),  generator.genesArray(),energy ));
        }
        map.draw();
        for(int i =0;i<steps;i++){
            if(!map.process()) break;
            map.draw();
        }
//        map.place(new Animal(generator.point(sizeX,sizeY), generator.genesArray(), energy, Direction.SOUTH,true));
//        map.place(new Animal(generator.point(sizeX,sizeY),  generator.genesArray(),energy ));
//        Animal myDog=new Animal(generator.point(sizeX,sizeY), generator.genesArray(),energy, Direction.NORTHEAST);
//        myDog.setEnergy(0);
//        map.place(myDog);
//        map.deleteDead();

//        map.place(new Grass(new Vector2d(7,8)));
//        map.place(new Grass(new Vector2d(7,9)));
//        map.place(new Grass(new Vector2d(7,10)));
//        map.place(new Animal(generator.point(sizeX,sizeY), generator.genesArray(),energy));
//        map.place(new Animal(generator.point(sizeX,sizeY), generator.genesArray(),energy));
//        map.place(new Animal(generator.point(sizeX,sizeY), generator.genesArray(),energy));
//        map.place(new Grass(new Vector2d(1,1)));
//        map.place(new Grass(new Vector2d(1,2)));
//        map.place(new Grass(new Vector2d(1,3)));
        map.draw();
        while(true){
            System.in.read();
            if(!map.process()) break;
            map.draw();
        }

//        map.place(new Grass(new Vector2d(5,5)));

    }
}
