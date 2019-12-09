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
    public Animal(int x, int y, int[] tab, int energy, Direction dir){
        this.position.x=x;
        this.position.y=y;
        this.energy=energy;
        genes=new Genes(tab);
        this.direction=dir;
    }
    public Genes getGenes(){
        return this.genes;
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

    public Direction randomDirection(){
        Direction dir=Direction.NORTH;
        int p=(int) Math.random()*8;
        for(int i=0;i<p;i++){
            dir=dir.next();
        }
        return dir;
    }

    public void process(Map map){
//        It has to:
//        Rotate
//        Check if can move forward
//        If yes, Move
//        If there is grass - eat it
//        If there is animal - have sex with it

        this.rotate(this.genes.getRotation());
        Vector2d newPosition=this.position.add(this.direction.toUnitVector());
        newPosition=map.bound(newPosition);
        if(map.canMoveTo(newPosition,this.objectType())){
            if(map.isOccupied(newPosition)){
                IObject object=map.myObjectAt(newPosition);
                if(object.objectType()==Type.GRASS){
                    map.deleteFromPosition(newPosition);
                    this.eatGrass();
                }
                else
                    if(object.getEnergy()>5 && this.getEnergy()>5){
                        int[] tab=this.genes.combine(object.getGenes());
                        Direction temp=this.randomDirection();
                        Vector2d childPosition=newPosition.add(temp.toUnitVector());
                        Animal animal=new Animal(childPosition.getX(),childPosition.getY(),tab,3, temp);
                        while(!(map.place(animal))){
                            temp=this.randomDirection();
                            childPosition=newPosition.add(temp.toUnitVector());
                            animal=new Animal(childPosition.getX(),childPosition.getY(),tab,3, temp);
                        }
                    }
                    this.energy--;
                    return;
            }
            map.replace(this,this.getPosition(),newPosition);
            this.moveForward();

        }
        this.energy--;
    }

    @Override
    public Type objectType() {
        return Type.ANIMAL;
    }
}
