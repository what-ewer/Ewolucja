package projekt_ewolucja;

public class Animal {
    public Vector2d position;
    public WorldMap map;
    public Integer energy;
    public Genotype genotype;
    public Direction orientation;

    public Animal(WorldMap map, Vector2d initialPosition, Integer energy, Genotype genes, boolean newAnimal) {
        this.position = initialPosition;
        this.energy = energy;
        this.orientation = Direction.NORTH;
        this.map = map;
        if (newAnimal) {
            this.genotype = new Genotype();
        } else {
            this.genotype = genes;
        }
    }

    public boolean canCopulate(Animal dog) {
        return this.energy > WorldMap.parameters.energyToCopulate && dog.energy > WorldMap.parameters.energyToCopulate;
    }


    public Animal copulation(Animal cat, Vector2d pos, Genotype genes) {
        return new Animal(this.map, pos, this.childEnergy(cat), genes, false);
    }

    public Integer childEnergy(Animal cat) {
        Integer cEnergy = (this.energy) / 4 + (cat.energy) / 4;
        cat.energy -= (cat.energy) / 4;
        this.energy -= (this.energy) / 4;
        return cEnergy;
    }

    @Override
    public String toString() {
        return this.orientation.toString();
    }
}
