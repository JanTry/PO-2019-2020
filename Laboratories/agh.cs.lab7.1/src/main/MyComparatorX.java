import java.util.Comparator;

public class MyComparatorX implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d o1, Vector2d o2) {
        if(o1.getX()==o2.getX()) return o1.getY()-o2.getY();
        return (o1.getX()-o2.getX());
    }
}
