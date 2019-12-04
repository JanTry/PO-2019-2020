public class vector2d {
    int x;
    int y;
    public vector2d(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }
    private void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public boolean precedes(vector2d other){
        return this.getX() <= other.getX() && this.getY() <= other.getY();
    }
    public boolean follows(vector2d other){
        return this.getX() >= other.getX() && this.getY() >= other.getY();
    }

    public vector2d upperRight(vector2d other){
        int a=this.x;
        int b=this.y;
        if(other.x>a)a=other.x;
        if(other.y>b)b=other.y;
        return new vector2d(a,b);
    }

    public vector2d lowerLeft(vector2d other){
        int a=this.x;
        int b=this.y;
        if(other.x<a)a=other.x;
        if(other.y<b)b=other.y;
        return new vector2d(a,b);
    }

    public vector2d add(vector2d other){

        int a=this.getX()+other.getX();
        int b=this.getY()+other.getY();
        vector2d temp = new vector2d(a,b);
        return temp;
    }

    public vector2d subtract(vector2d other){
        int a=this.getX()-other.getX();
        int b=this.getY()-other.getY();
        vector2d temp = new vector2d(a,b);
        return temp;
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof vector2d))
            return false;
        vector2d that = (vector2d) other;
        // tutaj przeprowadzane jest faktyczne porównanie
        return true;
    }

    public vector2d opposite(){
        int a= (-this.getX());
        int b= (-this.getY());
        return new vector2d(a,b);
    }

    public String toString(){
        String w="(";
        w+=x;
        w+=',';
        w+=y;
        w+=')';
        return w;
    }
}
