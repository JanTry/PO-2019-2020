import java.util.*;


public class UnboundedMap extends AbstractWorldMap {
//
//    Object map[][];
//    int width;
//    int height;
//    List<Animal> animals = new LinkedList<Animal>();

    public UnboundedMap(int width, int height, List<HayStack> hays){
        map = new Object[width+1][height+1];
        this.width = width;
        this.height = height;
        for(HayStack i:hays){
            if(i.getPosition().getX()<width &&  i.getPosition().getX()>=0
                    && i.getPosition().getY()<height &&  i.getPosition().getY()>=0) {
                if (canMoveTo(i.getPosition())) {
                    map[i.getPosition().getX()][i.getPosition().getY()] = i;
                }
                else throw new IllegalArgumentException("position " + i.getPosition().x + " " + i.getPosition().y + " is already occupied");
            }
        }
    }

    public String toString(){
        MapVisualizer a=new MapVisualizer(this);
        String b=a.draw(new vector2d(0,0), new vector2d(width,height));
        return b;
    }

//    @Override
//    public boolean canMoveTo(vector2d position) {
//        if(position.getX()>=width ||  position.getX()<0
//                || position.getY()>=height ||  position.getY()<0) return false;
//        return(!isOccupied(position));
//    }
//
//    @Override
//    public void clear(vector2d position){
//        map[position.getX()][position.getY()]=null;
//    }
//
//    public boolean place(Animal animal) {
//        if(canMoveTo(animal.getPosition())) {
//            map[animal.getPosition().getX()][animal.getPosition().getY()] = animal;
//            animals.add(animal);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean place(HayStack hay) {
//        if(canMoveTo(hay.getPosition())) {
//            map[hay.getPosition().getX()][hay.getPosition().getY()] = hay;
////            animals.add(animal);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void run(MoveDirection[] directions) {
//        Animal temp=null;
//        for (MoveDirection direction : directions){
//            if(direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT){
//                temp=animals.get(0);
//                temp.move(direction, this);
//                animals.add(temp);
//                animals.remove(0);
//            }
//            else {
//                temp = animals.get(0);
//                Animal p=null;
//                p=temp.move(direction, this);
//                if (p == null) animals.add(temp);
//                animals.remove(0);
//            }
//            //animals.add(temp);
//        }
//    }
//
//    @Override
//    public boolean isOccupied(vector2d position) {
//        return !(map[position.getX()][position.getY()]==null);
//    }
//
//    @Override
//    public void draw(){
//        MapVisualizer a=new MapVisualizer(this);
//
//        System.out.print(a.draw(new vector2d(0,0), new vector2d(width-1,height-1)));
//    }
//
//    @Override
//    public Object objectAt(vector2d position) {
//        if(position.getX()>=width ||  position.getX()<0
//                || position.getY()>=height ||  position.getY()<0)return null;
//        return map[position.getX()][position.getY()];
//    }
}
