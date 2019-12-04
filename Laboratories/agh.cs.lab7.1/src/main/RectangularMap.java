public class RectangularMap extends AbstractWorldMap {
    int width;
    int height;
    public RectangularMap (int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(){
        MapVisualizer a=new MapVisualizer(this);
        System.out.print(a.draw(new Vector2d(0,0), new Vector2d(width-1,height-1)));
    }
}
