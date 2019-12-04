public class Animal {
    protected MapDirection direction=MapDirection.NORTH;
    private Vector2d position=new Vector2d(2,2);
    private IWorldMap map;
    public String toString(){
        switch(direction){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
        }
        return null;
    }
    public int getX(){
        return this.position.getX();
    }
    public int getY(){
        return this.position.getY();
    }
    public MapDirection getdir(){
        return this.direction;
    }
    public Animal move(MoveDirection direction, IWorldMap map){
        switch (direction){
            case RIGHT: {
                this.direction = this.direction.next();
                return this;
            }
            case LEFT: {
                this.direction = this.direction.previous();
                return this;
            }
            case FORWARD: {
                if (map.canMoveTo(this.position.add(this.direction.toUnitVector()))) {
                    //System.out.println("For 1 "+ this.position.toString());
                    this.position = this.position.add(this.direction.toUnitVector());
                    //System.out.println("For 2 "+ this.position.toString());
                    map.add(this);
                    return this;
                }
                return null;
            }
            case BACKWARD: {
                if (map.canMoveTo(this.position.subtract(this.direction.toUnitVector()))) {
                    this.position = this.position.subtract(this.direction.toUnitVector());
                    map.add(this);
                    return this;
                }
                return null;
            }
        }
        return this;
    }
    public Animal(IWorldMap map) {
        if(map.isOccupied(this.position)) throw new IllegalArgumentException("position " + this.getX() +" " + this.getY() +" is already occupied");
        map.place(this);
    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        position=initialPosition;
        if(map.isOccupied(this.position)) throw new IllegalArgumentException("position " + this.getX() +" " + this.getY() +" is already occupied");
        map.place(this);
    }
    public Vector2d getPosition(){
        return position;
    }
}
