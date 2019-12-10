package com.company;

public class AppVisualizer {
    private Map map;

    public AppVisualizer(Map map) {
        this.map = map;
    }

    public String[][] draw() {
        String[][] array = new String[map.sizeX + 1][map.sizeY + 1];
        for (int i = 0; i < map.sizeX + 1; i++) {
            for (int j = 0; j < map.sizeY + 1; j++) {
                if (map.isOccupied(new Vector2d(i, j))) {
                    array[i][j] = map.objectAt(new Vector2d(i, j)).toString();
                } else array[i][j] = "        ";
            }
        }
        return array;
    }
}
