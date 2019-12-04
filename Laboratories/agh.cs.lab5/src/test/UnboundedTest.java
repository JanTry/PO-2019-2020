import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class UnboundedTest {
    @Test
    public void fTest(){
        String[] test={"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionParser().parse(test);
        List<HayStack> hays = new LinkedList<HayStack>();
        hays.add(new HayStack(new vector2d(-4,-4)));
        hays.add(new HayStack(new vector2d(7,7)));
        hays.add(new HayStack(new vector2d(3,6)));
        hays.add(new HayStack(new vector2d(2,0)));
        IWorldMap map = new RectangularMap(10, 5);
        Animal a=new Animal(map);
        Animal b=new Animal(map,new vector2d(3,4));
        map.place(a);
        map.place(b);
        map.run(directions);
        assertEquals(a.getX(), 2);
        assertEquals(a.getY(), 0);
        assertEquals(b.getX(), 3);
        assertEquals(b.getY(), 4);
        assertEquals(a.toString(),"S");
        assertEquals(b.toString(),"N");
    }
}

