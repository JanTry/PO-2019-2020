public enum MapDirection {
        EAST,
        NORTH,
        SOUTH,
        WEST;
        public boolean equals(MapDirection other){
            return this==other;
        }
        public String toString() {
            switch (this) {
                case NORTH:
                    return "Północ";
                case SOUTH:
                    return "Południe";
                case EAST:
                    return "Wschód";
                case WEST:
                    return "Zachód";
            }
            return null;
        }

        public MapDirection next() {
            switch (this) {
                case NORTH:
                    return EAST;
                case SOUTH:
                    return WEST;
                case EAST:
                    return SOUTH;
                case WEST:
                    return NORTH;
            }
            return null;
        }

        public MapDirection previous() {
            switch (this) {
                case NORTH:
                    return WEST;
                case SOUTH:
                    return EAST;
                case EAST:
                    return NORTH;
                case WEST:
                    return SOUTH;
            }
            return null;
        }

        public vector2d toUnitVector() {
            switch (this) {
                case NORTH:
                    return new vector2d(0, 1);
                case SOUTH:
                    return new vector2d(0, -1);
                case EAST:
                    return new vector2d(1, 0);
                case WEST:
                    return new vector2d(-1, 0);
            }
            return new vector2d(0, 1);
        }
    }
