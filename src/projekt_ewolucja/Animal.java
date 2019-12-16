package projekt_ewolucja;

import java.awt.*;

public class Animal {
    public Vector2d position;
    public WorldMap map;
    public Integer energy;
    public Genotype genotype;
    public Direction orientation;
    public Integer lifespan;
    public Integer children;

    public Animal(WorldMap map, Vector2d initialPosition, Integer energy, Genotype genes, boolean newAnimal) {
        this.position = initialPosition;
        this.energy = energy;
        this.orientation = Direction.NORTH;
        this.map = map;
        this.lifespan = 0;
        this.children = 0;

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
        return (this.position.toString() + "\nIlość dzieci: " + this.children + "\nIlość energii: " + this.energy);
    }

    public Integer animalsDominatingGene(){
        Integer[] genes = genotype.genes;
        int[] minimalGenes = new int[Genotype.diffGenes];
        for (int i = 0; i < Genotype.genotypeSize - 1; i++) {
            minimalGenes[genes[i]]++;
        }
        Integer max = 0;
        for(int i = 0; i < Genotype.getDiffGenes(); i++) if (minimalGenes[i] > minimalGenes[max]) max = i;
        return max;
    }

    public Color toColor() {
        if (this.animalsDominatingGene().equals(this.map.getDominatingGene())) return new Color(0,0,0);
        if (map.deadAnimals == 0) return new Color(224, 137, 41);
        if (this.lifespan < (this.map.lifespan/map.deadAnimals)) return new Color(224, 137, 41);
        return new Color(224, 17, 26);
    }
}
