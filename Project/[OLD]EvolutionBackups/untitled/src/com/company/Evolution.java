package com.company;

import java.io.IOException;

public class Evolution {
    int sizeX = 10;
    int sizeY = 10;
    int energy = 10;
    int animalNumber = 10;
    int grassEnergy = 10;

    public Evolution(int sizeX, int sizeY, int energy, int animalNumber, int grassEnergy) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.energy = energy;
        this.animalNumber = animalNumber;
        this.grassEnergy = grassEnergy;
    }

    public void run(int presetSteps) throws IOException {
        Generator generator = new Generator();
        Map map = new Map(this.sizeX, this.sizeY, this.grassEnergy);
        for (int i = 0; i < animalNumber; i++) {
            map.place(new Animal(generator.point(sizeX, sizeY), generator.genesArray(), energy));
        }
        map.draw();
        for (int i = 0; i < presetSteps; i++) {
            if (!map.process()) break;
            map.draw();
            System.out.println("That was step number " + i);
        }

        map.draw();
        while (true) {
            System.in.read();
            if (!map.process()) break;
            map.draw();
        }
    }
}
