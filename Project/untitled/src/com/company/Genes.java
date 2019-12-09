package com.company;

public class Genes {
    int[] genes=new int[32];
    public Genes(int tab[]){
        for(int i=0;i<tab.length;i++){
            this.genes[i]=tab[i];
        }
    }

    public int getRotation(){
        return this.genes[(int) (Math.random()*32)];
    }

    public int[] combine(Genes Father){
        int[] tab=new int[this.genes.length];
        for(int i=0;i<this.genes.length;i++){
            int p=(int) (Math.random()*31);
            if(p<15) tab[i]=this.genes[i]; //Something from his mother
            if(p>15) tab[i]=Father.genes[i]; //Something from his father
            if(p==15) tab[i]=(int) (Math.random()*8); //Mutations
        }
        return tab;
    }

}
