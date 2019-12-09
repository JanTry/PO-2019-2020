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
        int sizeX=150;
        int sizeY=150;
        int energy=700;
        Generator generator=new Generator();
//        System.out.println("It does compile");
//        System.out.println("At least for now");
        Map map=new Map(sizeX,sizeY);
        int steps=10000;
        int animalNumber=150;
        for(int i =0;i<animalNumber;i++){
            map.place(new Animal(generator.point(sizeX,sizeY),  generator.genesArray(),energy ));
        }
        map.draw();
        for(int i =0;i<steps;i++){
            if(!map.process()) break;
            map.draw();
            System.out.println("That was step number "+i);
        }

        map.draw();
        while(true){
            System.in.read();
            if(!map.process()) break;
            map.draw();
        }

    }
}
