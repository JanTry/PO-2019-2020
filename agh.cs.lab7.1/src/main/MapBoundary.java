import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

//    SortedSet<IMapElement> sortedSetX=new TreeSet<>(new MyComparatorX());
//    SortedSet<IMapElement> sortedSetY=new TreeSet<>(new MyComparatorY());
    SortedSet<Vector2d> sortedSetX=new TreeSet<>(new MyComparatorX());
    SortedSet<Vector2d> sortedSetY=new TreeSet<>(new MyComparatorY());

    public void add(IMapElement i){
        sortedSetX.add(i.getPosition());
        sortedSetY.add(i.getPosition());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        if(oldPosition.getX()!=newPosition.getX()){
//            if(sortedSetX.first().getX()==oldPosition.getX() && sortedSetX.first().getY()==oldPosition.getY()){
//                a=sortedSetX.
//            }
//        }
        SortedSet<Vector2d> subSet = sortedSetX.subSet(oldPosition, newPosition);
        if(!subSet.isEmpty()){ sortedSetX.remove(oldPosition); sortedSetX.add(oldPosition); }
        subSet = sortedSetY.subSet(oldPosition, newPosition);
        if(!subSet.isEmpty()){ sortedSetY.remove(oldPosition); sortedSetY.add(oldPosition); }
    }

    public Vector2d bottomCorner(){
        return(new Vector2d(this.sortedSetX.first().getX(),this.sortedSetY.first().getY()));
    }
    public Vector2d topCorner(){
        return(new Vector2d(this.sortedSetX.last().getX(),this.sortedSetY.last().getY()));
    }

}
