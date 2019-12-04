import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    HashMap<Vector2d,  Object> hashMap = new HashMap<Vector2d, Object>();
    List<Animal> animals = new LinkedList<Animal>();
    List<HayStack> hays = new LinkedList<HayStack>();
    MapBoundary boundary=new MapBoundary();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return(!isOccupied(position));
    }

    public void boundaryAdd(IMapElement animal){
        boundary.add(animal);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Object i=hashMap.get(oldPosition);
        hashMap.remove(oldPosition);
        hashMap.put(newPosition, i);
        boundary.positionChanged(oldPosition, newPosition);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.add(animal);
            this.boundary.add(animal);
            return true;
        }
        else return false;
    }
    @Override
    public void add(Animal animal){
        animals.add(animal);
        hashMap.put(animal.getPosition(),animal);
    }
    @Override
    public void run(MoveDirection[] directions) {
        Animal temp=null;
        for (MoveDirection direction : directions){
            if(direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT){
                temp=animals.get(0);
                temp.move(direction, this);
                animals.add(temp);
                animals.remove(0);
            }
            else {
                temp = animals.get(0);
                Vector2d tmp=temp.getPosition();
                Animal p=null;
                p=temp.move(direction, this);
                if (p == null) animals.add(temp);
                animals.remove(0);
            }
        }
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return hashMap.containsKey(position);
    }
    @Override
    public void draw(){
        MapVisualizer a=new MapVisualizer(this);
//        Animal tmp=animals.get(0);
//        int xmin=tmp.getX();
//        int xmax=tmp.getX();
//        int ymin=tmp.getY();
//        int ymax=tmp.getY();
//        for(Animal i : animals){
//            if(i.getX()>xmax)xmax=i.getX();
//            if(i.getX()<xmin)xmin=i.getX();
//            if(i.getY()>ymax)ymax=i.getY();
//            if(i.getY()<ymin)ymin=i.getY();
//        }
//        for(HayStack i : hays){
//            if(i.getX()>xmax)xmax=i.getX();
//            if(i.getX()<xmin)xmin=i.getX();
//            if(i.getY()>ymax)ymax=i.getY();
//            if(i.getY()<ymin)ymin=i.getY();
//        }
        System.out.print(a.draw(this.boundary.bottomCorner(), this.boundary.topCorner()));
    }
    @Override
    public Object objectAt(Vector2d position) {
        return hashMap.get(position);
    }
}
