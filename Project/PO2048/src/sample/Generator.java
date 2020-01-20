package sample;

public class Generator {
    Position point() {
        int x = (int) ((Math.random()) * (4));
        int y = (int) ((Math.random()) * (4));
        return new Position(x, y);
    }

    int value() {
        int x = 2 * (int) ((Math.random()) * (2) + 1);
        return x;
    }
}
