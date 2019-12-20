package com.company;

class Genes {
    private int[] genes = new int[32];

    Genes(int[] tab) {
        System.arraycopy(tab, 0, this.genes, 0, tab.length);
    }

    int[] getGenes() {
        return this.genes;
    }

    int getRotation() {
        return this.genes[(int) (Math.random() * 32)];
    }

    int[] combine(Genes Father) {
        int[] tab = new int[this.genes.length];
        for (int i = 0; i < this.genes.length; i++) {
            int p = (int) (Math.random() * 301);
            if (p < 150) tab[i] = this.genes[i]; //Something from his mother
            if (p > 150) tab[i] = Father.genes[i]; //Something from his father
            if (p == 150) tab[i] = (int) (Math.random() * 8); //Mutations
            //This is implemented as it was decided during classes
        }
        return tab;
    }

    public String toString() {
        String genes = "[";
        for (int i = 0; i < 32; i++) {
            genes = genes + this.genes[i];
            genes = genes + ", ";
        }
        genes = genes + "]";
        return genes;
    }

}
