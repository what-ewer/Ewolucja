package projekt_ewolucja;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Genotype {
    public static final Integer genotypeSize = 32;
    public static final Integer diffGenes = 8;
    public Integer[] genes;

    public Genotype() {
        this.genes = new Integer[genotypeSize];
        this.randomGenes();
    }

    public Genotype(Animal cat, Animal dog) {
        this();
        System.arraycopy(dog.genotype.genes, 0, this.genes, 0, 21);
        System.arraycopy(cat.genotype.genes, 21, this.genes, 21, 11);
        this.checkGenotypeRequirements();
        this.Mutation();
    }

    public static Genotype childGenotype(Animal cat, Animal dog) {
        return new Genotype(cat, dog);
    }

    public void Mutation() {
        Random rand = new Random();
        this.genes[genotypeSize - 1] = rand.nextInt(diffGenes);
    }

    private void randomGenes() {
        for (int i = 0; i < genotypeSize; i++) this.genes[i] = 0;
        Random rand = new Random();
        for (int i = 0; i < 32; i++) this.genes[i] = rand.nextInt(diffGenes);
        checkGenotypeRequirements();
    }

    public void checkGenotypeRequirements() {
        int[] minimalGenes = new int[diffGenes];
        for (int i = 0; i < genotypeSize - 1; i++) {
            minimalGenes[this.genes[i]]++;
        }
        for (int i = 0; i < diffGenes; i++) {
            if (minimalGenes[i] == 0) this.fixGenes(i, minimalGenes);
        }
    }

    public void fixGenes(int missingGene, int[] minimalGenes) throws IllegalArgumentException {
        Integer[] intArray = {0, 1, 2, 3, 4, 5, 6, 7};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);
        //przetasowanie żeby iść losowo po tablicy genotypów

        for (int i : intArray) {
            if (minimalGenes[i] > 1) {
                for (int j=0;j<genotypeSize;j++){
                    if (minimalGenes[this.genes[j]] > 1){
                        this.genes[j] = missingGene;
                        break;
                    }
                }
                minimalGenes[i]--;
                minimalGenes[missingGene]++;

                return;
            }
        }
        throw new IllegalArgumentException("Problem z tablicą genotypu");
    }

    public Integer[] getGenes() {
        return this.genes;
    }

    public static Integer getGenotypeSize() {
        return genotypeSize;
    }

    public static Integer getDiffGenes() {
        return diffGenes;
    }

    @Override
    public String toString() {
        int[] eachGene = new int[diffGenes];
        for (int i = 0; i < genotypeSize - 1; i++) {
            eachGene[this.genes[i]]++;
        }
        return Arrays.toString(eachGene);
    }
}
