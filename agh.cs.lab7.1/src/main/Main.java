import java.util.*;

public class Main {
    public static void main(String[] args) {

//        SortedSet<Integer> setNumbers = new TreeSet<>();
//        setNumbers.add(2);
//        setNumbers.add(0);
//        setNumbers.add(7);
//        setNumbers.add(2);
//        System.out.println("Sorted Set: " + setNumbers);
        //"f","b","r","l","f","f","l","r","f","f","f","f","f","f","f","f"
        String[] test={"f","b","r","l","f","f","l","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionParser().parse(test);
        List<HayStack> hays = new LinkedList<HayStack>();
        hays.add(new HayStack(new Vector2d(-4,-4)));
        hays.add(new HayStack(new Vector2d(7,7)));
        hays.add(new HayStack(new Vector2d(3,6)));
        hays.add(new HayStack(new Vector2d(2,0)));
        IWorldMap map = new UnboundedMap( hays);
        map.place(new Animal(map));
        map.place(new Animal(map,new Vector2d(3,4)));
        map.run(directions);
        map.draw();


    }
}
