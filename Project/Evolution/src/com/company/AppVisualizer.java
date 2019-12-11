package com.company;

public class AppVisualizer {
    private Map map;

    public AppVisualizer(Map map) {
        this.map = map;
    }

    public DrawType[][] draw() {
        DrawType[][] array = new DrawType[map.sizeX + 1][map.sizeY + 1];
        for (int i = 0; i < map.sizeX + 1; i++) {
            for (int j = 0; j < map.sizeY + 1; j++) {
                if (map.isOccupied(new Vector2d(i, j))) {
                    IObject p= (IObject) map.objectAt(new Vector2d(i, j));
                    if(p.objectType()==Type.ANIMAL){
                        Animal t=(Animal)p;
                        if(t.getEnergy()>map.getGrassEnergy())array[i][j]=DrawType.ANIMAL;
                        else if(t.getEnergy()==0) array[i][j]=DrawType.AboutToDieAnimal;
                            else array[i][j]=DrawType.TiredAnimal;
                    }
                    else array[i][j]=DrawType.GRASS;

                } else array[i][j] = DrawType.BLANK;
            }
        }
        return array;
    }
}
