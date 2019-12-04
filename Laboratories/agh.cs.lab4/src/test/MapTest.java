import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MapTest {
    @Test
    public void fTest(){
        String[] test={"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionParser().parse(test);
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
    @Test
    public void sTest(){
        String[] test={"f","b","r","l","f","f","l","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionParser().parse(test);
        IWorldMap map = new RectangularMap(10, 5);
        Animal a=new Animal(map);
        Animal b=new Animal(map,new vector2d(3,4));
        map.place(a);
        map.place(b);
        map.run(directions);
        assertEquals(a.getX(), 2);
        assertEquals(a.getY(), 4);
        assertEquals(b.getX(), 3);
        assertEquals(b.getY(), 4);
        assertEquals(a.toString(),"N");
        assertEquals(b.toString(),"N");
    }
}
