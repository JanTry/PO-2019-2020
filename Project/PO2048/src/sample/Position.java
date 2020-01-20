package sample;

public class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    Position add(Position position) {
        return new Position(this.x + position.x, this.y + position.y);
    }
}
