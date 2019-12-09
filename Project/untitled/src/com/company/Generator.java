package com.company;

public class Generator {
    public Vector2d point(int rangeX, int rangeY) {
        int x = (int) ((Math.random()) * (rangeX + 1));
        int y = (int) ((Math.random()) * (rangeY + 1));
        return new Vector2d(x, y);
    }

    public int[] genesArray() {
        int[] tab = new int[32];
        for (int i = 0; i < 32; i++) {
            tab[i] = (int) (Math.random() * 8);
        }
        return tab;
    }
}
