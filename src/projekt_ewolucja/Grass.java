package projekt_ewolucja;

import java.awt.*;

public class Grass {
    public Vector2d grassPosition;
    public Integer energyValue;

    public Grass(Vector2d position) {
        this.grassPosition = position;
        this.energyValue = WorldMap.parameters.grassStartingEnergy;
    }

    public Grass(Vector2d position, Integer energy) {
        this.grassPosition = position;
        this.energyValue = energy;
    }

    public void grow() {
        this.energyValue += WorldMap.parameters.growValue;
    }

    @Override
    public String toString() {
        return (this.grassPosition.toString() + "\nWartość energetyczna: " + this.energyValue);
    }

    public Color toColor() {
        if (energyValue.equals(WorldMap.parameters.grassStartingEnergy)) return new Color(112, 224, 39);
        return new Color(9, 224, 19);
    }
}
