package projekt_ewolucja;

public enum Direction {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    private static final String[] dirString = new String[]{"A", "A", "A", "A", "A", "A", "A", "A"};

    public Integer directionToNumber() throws IllegalArgumentException {
        switch (this) {
            case NORTH:
                return 0;
            case NORTH_EAST:
                return 1;
            case EAST:
                return 2;
            case SOUTH_EAST:
                return 3;
            case SOUTH:
                return 4;
            case SOUTH_WEST:
                return 5;
            case WEST:
                return 6;
            case NORTH_WEST:
                return 7;
            default:
                throw new IllegalArgumentException("Wystąpił problem z orientacją zwierzęcia");
        }
    }

    public Direction numberToDirection(Integer value) throws IllegalArgumentException {
        switch ((this.directionToNumber() + value) % Genotype.getDiffGenes()) {
            case 0:
                return NORTH;
            case 1:
                return NORTH_EAST;
            case 2:
                return EAST;
            case 3:
                return SOUTH_EAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTH_WEST;
            case 6:
                return WEST;
            case 7:
                return NORTH_WEST;
            default:
                throw new IllegalArgumentException("Wystąpił problem z orientacją zwierzęcia");
        }
    }

    public Vector2d orientationToVector() throws IllegalArgumentException {
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case NORTH_EAST:
                return new Vector2d(1, 1);
            case EAST:
                return new Vector2d(1, 0);
            case SOUTH_EAST:
                return new Vector2d(1, -1);
            case SOUTH:
                return new Vector2d(0, -1);
            case SOUTH_WEST:
                return new Vector2d(-1, -1);
            case WEST:
                return new Vector2d(-1, 0);
            case NORTH_WEST:
                return new Vector2d(-1, 1);
            default:
                throw new IllegalArgumentException("Wystąpił problem z orientacją zwierzęcia");
        }
    }

    @Override
    public String toString() {
        return dirString[this.ordinal()];
    }
}

