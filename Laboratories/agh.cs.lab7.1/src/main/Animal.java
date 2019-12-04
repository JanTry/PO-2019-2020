import java.util.LinkedList;
import java.util.List;

public class Animal  implements Comparable, IMapElement {
    protected MapDirection direction=MapDirection.NORTH;
    private Vector2d position=new Vector2d(2,2);
    List<IPositionChangeObserver> observerList= new LinkedList<>();
    public boolean isAnimal(){
        return true;
    }
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
                    this.position = this.position.add(this.direction.toUnitVector());
                    map.add(this);
                    positionChanged(this.position.subtract(this.direction.toUnitVector()));
                    return this;
                }
                return null;
            }
            case BACKWARD: {
                if (map.canMoveTo(this.position.subtract(this.direction.toUnitVector()))) {
                    this.position = this.position.subtract(this.direction.toUnitVector());
                    map.add(this);
                    positionChanged(this.position.add(this.direction.toUnitVector()));
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
    public void addObserver(IPositionChangeObserver observer){
        observerList.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observerList.remove(observer);
    }
    public void positionChanged(Vector2d oldPosition){
        for(IPositionChangeObserver observer: observerList){
            observer.positionChanged(oldPosition, this.position);
        }
    }
    @Override
    public int compareTo(Object o) {
        return this.getX()*1000+this.getY();
    }
}
