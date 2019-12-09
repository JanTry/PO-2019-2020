package com.company;

public enum Direction {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;
    public Direction next() {
        switch (this) {
            case NORTH:
                return NORTHEAST;
            case NORTHEAST:
                return EAST;
            case EAST:
                return SOUTHEAST;
            case SOUTHEAST:
                return SOUTH;
            case SOUTH:
                return SOUTHWEST;
            case SOUTHWEST:
                return WEST;
            case WEST:
                return NORTHWEST;
            case NORTHWEST:
                return NORTH;
            default:
                throw new IllegalStateException("Unexpected Direction value: " + this);
        }
    }
    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case SOUTH:
                return new Vector2d(0, -1);
            case EAST:
                return new Vector2d(1, 0);
            case WEST:
                return new Vector2d(-1, 0);
        }
        return new Vector2d(0, 1);
    }
}
