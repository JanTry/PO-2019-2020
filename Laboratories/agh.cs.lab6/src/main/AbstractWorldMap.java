import java.util.*;

abstract class AbstractWorldMap implements IWorldMap {
    HashMap<Vector2d,  Object> hashMap = new HashMap<Vector2d, Object>();
    List<Animal> animals = new LinkedList<Animal>();
    List<HayStack> hays = new LinkedList<HayStack>();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return(!isOccupied(position));
    }



    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animals.add(animal);
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
                else {
                    hashMap.remove(tmp);
                    hashMap.put(temp.getPosition(),temp);
                }
                animals.remove(0);
            }
        }
//        for (Animal i :animals) System.out.println("ANIMAL in list: " + i.getX() + " " + i.getY());
//        for (HayStack i :hays) System.out.println("HAY in list");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
//        System.out.println(" ");
//        System.out.println("Checking if " + position.x + " "+ position.y + " " + "is occupied");
        return hashMap.containsKey(position);
//        for(HayStack i : hays){
////            System.out.println("There is a hay " + i.getPosition().x + " " + i.getPosition().y);
//            if(i.getPosition().getX()==position.getX() && i.getPosition().getY()==position.getY()) return true;
//        }
//        for(Animal i : animals){
////            System.out.println("There is an animal " + i.getX() + " " + i.getY());
//            if(i.getX()==position.getX() && i.getY()==position.getY()) return true;
//        }
////        System.out.println("false");
//        return false;
    }

    @Override
    public void draw(){
        MapVisualizer a=new MapVisualizer(this);
        Animal tmp=animals.get(0);
        int xmin=tmp.getX();
        int xmax=tmp.getX();
        int ymin=tmp.getY();
        int ymax=tmp.getY();
        for(Animal i : animals){
            if(i.getX()>xmax)xmax=i.getX();
            if(i.getX()<xmin)xmin=i.getX();
            if(i.getY()>ymax)ymax=i.getY();
            if(i.getY()<ymin)ymin=i.getY();
        }
        for(HayStack i : hays){
            if(i.getX()>xmax)xmax=i.getX();
            if(i.getX()<xmin)xmin=i.getX();
            if(i.getY()>ymax)ymax=i.getY();
            if(i.getY()<ymin)ymin=i.getY();
        }
        System.out.print(a.draw(new Vector2d(xmin,ymin), new Vector2d(xmax,ymax)));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return hashMap.get(position);
//        for(HayStack i : hays){
////            System.out.println("There is a hay");
//            if(i.getPosition().getX()==position.getX() && i.getPosition().getY()==position.getY())  return i;
//        }
//        for(Animal i : animals){
////            System.out.println("There is an animal");
//        if(i.getX()==position.getX() && i.getY()==position.getY())  return i;
//    }
//        return null;
    }
}
