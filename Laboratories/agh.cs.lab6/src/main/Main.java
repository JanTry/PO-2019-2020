import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Animal kot = new Animal();
////        String temp[]=new String[5];
////        temp[0]="forward";
////        temp[1]="for";
////        temp[2]="forward";
////        temp[3]="right";
////        temp[4]="right";
////
////        OptionParser tool=new OptionParser();
////        MoveDirection tab[]=tool.parse(temp);
////        for (MoveDirection element:tab) {
////            kot=kot.move(element);
////        }
////        System.out.println(kot.toString());
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
