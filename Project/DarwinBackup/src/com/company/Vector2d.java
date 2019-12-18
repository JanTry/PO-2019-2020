package com.company;

public class Vector2d {
    int x;
    int y;

    Vector2d(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }


    Vector2d add(Vector2d other) {

        int a = this.getX() + other.getX();
        int b = this.getY() + other.getY();
        return new Vector2d(a, b);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return true;
    }

    public String toString() {
        String w = "(";
        w += x;
        w += ',';
        w += y;
        w += ')';
        return w;
    }
}
