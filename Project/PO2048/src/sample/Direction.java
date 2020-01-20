package sample;

public enum Direction {
    top,
    left,
    right,
    bottom;

    Position toPosition() {
        switch (this) {
            case top:
                return new Position(0, -1);
            case left:
                return new Position(-1, 0);
            case right:
                return new Position(1, 0);
            case bottom:
                return new Position(0, 1);
            default:
                return new Position(0, 0);
        }
    }
}
