package projekt_ewolucja;

public class Grass {
    public Vector2d grassPosition;
    public Integer energyValue;

    public Grass(Vector2d position){
        this.grassPosition = position;
        this.energyValue = WorldMap.parameters.grassStartingEnergy;
    }

    public Grass(Vector2d position, Integer energy){
        this.grassPosition = position;
        this.energyValue = energy;
    }

    public Grass grow(){
        return new Grass(this.grassPosition, this.energyValue + WorldMap.parameters.growValue);
    }

    @Override
    public String toString() {
        return "*";
    }
}
