package projekt_ewolucja;

import java.util.Objects;
import java.util.Random;
import java.util.Vector;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d((this.x + other.x + WorldMap.parameters.worldWidth) % WorldMap.parameters.worldWidth, (this.y + other.y + WorldMap.parameters.worldHeight) % WorldMap.parameters.worldHeight);
    }

    public static Vector2d randomVector() {
        return new Vector2d(new Random().nextInt(WorldMap.parameters.worldWidth), new Random().nextInt(WorldMap.parameters.worldHeight));
    }

    boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x &&
                y == vector2d.y;
    }

    public Vector2d[] positionsNearby() {
        return new Vector2d[]{
                this.add(new Vector2d(1, 1)),
                this.add(new Vector2d(1, 0)),
                this.add(new Vector2d(1, -1)),
                this.add(new Vector2d(0, -1)),
                this.add(new Vector2d(0, 1)),
                this.add(new Vector2d(-1, -1)),
                this.add(new Vector2d(-1, 0)),
                this.add(new Vector2d(-1, 1)),
        };
    }

    public Vector2d realCoords(){
        return new Vector2d(this.x, WorldMap.parameters.worldHeight-this.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
