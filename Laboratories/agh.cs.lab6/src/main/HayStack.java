public class HayStack {
    private Vector2d position;
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
}
