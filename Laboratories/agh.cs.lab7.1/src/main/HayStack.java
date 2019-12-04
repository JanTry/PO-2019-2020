public class HayStack  implements Comparable, IMapElement{
    private Vector2d position;
    public boolean isAnimal(){
        return false;
    }
    public HayStack(Vector2d position) {
        this.position=position;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return "s";
    }
    public int getX(){
        return this.getPosition().getX();
    }
    public int getY(){
        return this.getPosition().getY();
    }

    @Override
    public int compareTo(Object o) {
        return this.getX()*1000+this.getY();
    }
}
