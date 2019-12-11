package com.company;

public class Generator {
    public Vector2d point(int rangeX, int rangeY) {
        int x = (int) ((Math.random()) * (rangeX + 1));
        int y = (int) ((Math.random()) * (rangeY + 1));
        return new Vector2d(x, y);
    }

    public Vector2d outsidePoint(int rangeX, int rangeY) {
        int x = (int) ((Math.random()) * (rangeX + 1));
        int y = (int) ((Math.random()) * (rangeY + 1));
        while (x > 3 * rangeX / 8 && x < (3 * rangeX / 8 + rangeX / 4 + 1) && y > 3 * rangeY / 8 && y < (3 * rangeY / 8 + rangeY / 4 + 1)) {
            x = (int) ((Math.random()) * (rangeX + 1));
            y = (int) ((Math.random()) * (rangeY + 1));
        }
        return new Vector2d(x, y);
    }

    public Vector2d junglePoint(int rangeX, int rangeY) {

        int x = (int) ((Math.random()) * (rangeX / 4 + 1) + 3 * rangeX / 8);
        int y = (int) ((Math.random()) * (rangeY / 4 + 1) + 3 * rangeY / 8);
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
