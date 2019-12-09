package com.company;

public class Animal implements IObject {
    public boolean valid = true;
    private Vector2d position = new Vector2d(2, 2);
    private Direction direction = Direction.NORTH;
    private int energy;
    private Genes genes;

    public Animal(Vector2d position, int[] tab, int energy, Direction dir) {
        this.position.x = position.getX();
        this.position.y = position.getY();
        this.direction = dir;
        this.energy = energy;
        genes = new Genes(tab);
    }

    public Animal(Vector2d position, int[] tab, int energy) {
        this.position.x = position.getX();
        this.position.y = position.getY();
        this.energy = energy;
        genes = new Genes(tab);
    }

    public Animal(Vector2d position, int[] tab, int energy, Direction dir, boolean valid) {
        this.position.x = position.getX();
        this.position.y = position.getY();
        this.energy = energy;
        genes = new Genes(tab);
        this.direction = dir;
        this.valid = valid;
    }

    public Genes getGenes() {
        return this.genes;
    }

    public String toString() {
        return this.direction.toString();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getX() {
        return this.position.getX();
    }

    public int getY() {
        return this.position.getY();
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public void rotate(int number) {
        for (int i = 0; i < number; i++) {
            this.direction = this.direction.next();
        }
    }

    public Animal moveForward() {
        this.position = this.position.add(this.direction.toUnitVector());
//        Here we shall move it on the map
//        And probably a few cases will be here - covering being unable to move
//        Also important to remember about removing animal on the opposite side of the map
//        In case of moving out of boundaries
        return this;
    }

    public void eatGrass() {
        this.energy += 10;
    }

    public Direction randomDirection() {
        Direction dir = Direction.NORTH;
        int p = (int) Math.random() * 8;
        for (int i = 0; i < p; i++) {
            dir = dir.next();
        }
        return dir;
    }

    public void process(Map map) {
//        It has to:
//        Rotate
//        Check if can move forward
//        If yes, Move
//        If there is grass - eat it
//        If there is animal - have sex with it

        this.rotate(this.genes.getRotation());
        Vector2d newPosition = this.position.add(this.direction.toUnitVector());

        newPosition = map.bound(newPosition); //Covers going out of edges

        if (map.canMoveTo(newPosition, this.objectType())) {
            if (map.isOccupied(newPosition)) {
                IObject object = map.myObjectAt(newPosition);
                if (object.objectType() == Type.GRASS) {
                    map.deleteFromPosition(newPosition);
                    this.eatGrass();
                    map.replace(this, this.position, newPosition);
                    this.position = newPosition;
                    this.moveForward();
                    map.replace(this, this.position, newPosition);
                    this.position = newPosition;
                } else if (object.getEnergy() > 5 && this.getEnergy() > 5) {
                    int[] tab = this.genes.combine(object.getGenes());
                    Direction temp = this.randomDirection();
                    Vector2d childPosition = newPosition.add(temp.toUnitVector());
                    int childEnergy = (int) (this.getEnergy() / 4) + (object.getEnergy() / 4);
                    Animal animal = new Animal(childPosition, tab, childEnergy, temp, false);

                    for (int i = 0; i < 20 && !(map.place(animal)); i++) {
                        temp = this.randomDirection();
                        childPosition = newPosition.add(temp.toUnitVector());
                        animal = new Animal(childPosition, tab, childEnergy, temp, false);
                    }
                    this.setEnergy((int) (this.getEnergy() * 3) / 4);
                    object.setEnergy((int) 3 * object.getEnergy() / 4);
                }
                this.energy--;
                return;
            }
            map.replace(this, this.position, newPosition);
            this.position = newPosition;
//            map.replace(this,this.getPosition(),newPosition);


        }
        this.energy--;
    }

    @Override
    public Type objectType() {
        return Type.ANIMAL;
    }
}
