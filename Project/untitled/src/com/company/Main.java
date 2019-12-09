package com.company;

public class Main {
    public static int[] generate(){
        int[] tab=new int[32];
        for(int i=0;i<32;i++){
            tab[i]=(int)(Math.random()*8);
        }
        return tab;
    }
    public static void main(String[] args) {
	// write your code here

        System.out.println("It does compile");
        System.out.println("At least for now");
        Map map=new Map(9,9);

        map.place(new Animal(2,2, generate(), Direction.SOUTH));
        map.place(new Animal(5,5, generate()));
        Animal myDog=new Animal(3,3,generate(),Direction.NORTHEAST);
        myDog.setEnergy(0);
        map.place(myDog);
//        map.deleteDead();
        map.process();
        map.place(new Grass(new Vector2d(7,8)));
        map.place(new Grass(new Vector2d(7,9)));
        map.place(new Grass(new Vector2d(7,10)));
        map.process();
        map.process();
//        map.place(new Grass(new Vector2d(5,5)));

        map.draw();
    }
}
