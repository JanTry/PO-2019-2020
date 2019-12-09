package com.company;

public class Animal implements IObject {
    private Vector2d position=new Vector2d(2,2);
    private Direction direction=Direction.NORTH;
    private int energy;
    private Genes genes;
    public Animal(int x, int y, int[] tab, Direction dir){
        this.position.x=x;
        this.position.y=y;
        this.direction=dir;
        this.energy=10;
        genes=new Genes(tab);
    }
    public Animal(int x, int y, int[] tab){
        this.position.x=x;
        this.position.y=y;
        this.energy=10;
        genes=new Genes(tab);
    }
    public String toString(){
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
    public int getX(){
        return this.position.getX();
    }
    public int getY(){
        return this.position.getY();
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public void rotate(int number){
        for(int i=0;i<number;i++){
            this.direction=this.direction.next();
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
    public void eatGrass(){
        this.energy+=10;
    }
    public boolean process(Map map){
//        It has to:
//        Rotate
//        Check if can move forward
//        If yes, Move
//        If there is grass - eat it
//        If there is animal - have sex with it

        this.rotate(this.genes.getRotation());


        this.energy--;
        return energy > 0;
    }

    @Override
    public Type objectType() {
        return Type.ANIMAL;
    }
}
