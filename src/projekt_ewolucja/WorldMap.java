package projekt_ewolucja;

import java.io.IOException;
import java.util.*;

public class WorldMap {
    public static Parameters parameters;

    public final Vector2d upperRightCorner;
    public final Vector2d lowerLeftCorner;
    public final Vector2d upperRightJgl;
    public final Vector2d lowerLeftJgl;

    public static int deadAnimals;

    public Map<Vector2d, Grass> grassMap = new HashMap<>();
    public Map<Vector2d, LinkedList<Animal>> animalMap = new HashMap<>();
    public List<Animal> animalList = new LinkedList<>();

    public WorldMap(Parameters p) throws IllegalArgumentException {
        parameters = p;
        if (p.worldHeight < p.jungleHeight)
            throw new IllegalArgumentException("Wysokosc dzungli musi byc mniejszy niz mapy");
        if (p.worldWidth < p.jungleWidth)
            throw new IllegalArgumentException("Rozmiar dzungli musi byc mniejszy niz mapy");

        deadAnimals = 0;

        upperRightCorner = new Vector2d(parameters.worldWidth - 1, parameters.worldHeight - 1);
        lowerLeftCorner = new Vector2d(0, 0);
        lowerLeftJgl = new Vector2d((parameters.worldWidth - parameters.jungleWidth) / 2, (parameters.worldHeight - parameters.jungleHeight) / 2);
        upperRightJgl = lowerLeftJgl.add(new Vector2d(parameters.jungleWidth - 1, parameters.jungleHeight - 1));
    }

    public void place(Animal dog) {
        addAnimal(dog.position, dog);
        animalList.add(dog);
    }

    public void addAnimal(Vector2d position, Animal dog) {
        if (dog == null) return;

        LinkedList<Animal> animals = animalMap.get(position);
        if (animals == null) {
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(dog);
            animalMap.put(position, tmp);
        } else {
            animals.add(dog);
        }
    }

    public void removeAnimal(Vector2d position, Animal dog) {
        if (dog == null) return;
        LinkedList<Animal> animals = animalMap.get(position);
        animals.remove(dog);
    }

    public void removeGrass(Grass g) {
        grassMap.remove(g.grassPosition);
    }

    public boolean isGrass(Vector2d pos) {
        return this.grassMap.containsKey(pos);
    }

    public boolean isThereAnything(Vector2d position) {
        return this.animalMap.containsKey(position) || this.grassMap.containsKey(position);
    }

    public Object objectAt(Vector2d position) {
        if (this.animalMap.get(position) != null && !this.animalMap.get(position).isEmpty())
            return this.animalMap.get(position).get(0);

        return this.grassMap.get(position);
    }

    public void removeTheDead() {
        List<Animal> toDie = new LinkedList<>();
        for (Animal dog : animalList) {
            if (!this.isAlive(dog)) {
                removeAnimal(dog.position, dog);
                toDie.add(dog);
                deadAnimals++;
            }
        }
        animalList.removeAll(toDie);
    }

    public boolean isAlive(Animal dog) {
        return dog.energy > 0;
    }

    public void moveTheAnimals() {
        for (Animal dog : animalList) {
            moveSingleAnimal(dog);
        }
    }

    public void moveSingleAnimal(Animal dog) {
        Vector2d oldCoords = dog.position;
        Integer[] movePattern = dog.genotype.getGenes();
        Random rand = new Random();
        int randNum = movePattern[rand.nextInt(Genotype.getGenotypeSize())];
        int x = dog.orientation.directionToNumber() + randNum;
        dog.orientation = dog.orientation.numberToDirection(x);
        removeAnimal(oldCoords, dog);
        dog.position = oldCoords.add(dog.orientation.orientationToVector());
        addAnimal(oldCoords.add(dog.orientation.orientationToVector()), dog);
        dog.energy -= WorldMap.parameters.movementValue;
    }

    public void eatTheGrass() {
        for (Animal dog : animalList) {
            if (isGrass(dog.position)) eatGrass(dog, grassMap.get(dog.position));
        }
    }

    public void eatGrass(Animal dog, Grass g) {
        dog.energy += g.energyValue;
        removeGrass(g);
    }


    public void copulateAll() {
        for (LinkedList<Animal> atmpList : animalMap.values()) {
            if (atmpList != null) {
                if (atmpList.size() >= 2) {
                    Animal dog = atmpList.get(0);
                    Animal cat = atmpList.get(1);
                    if (dog.canCopulate(cat)) {
                        Vector2d[] randPositions = cat.position.positionsNearby();
                        List<Vector2d> randPosList = Arrays.asList(randPositions);
                        Collections.shuffle(randPosList);
                        randPosList.toArray(randPositions);
                        for (Vector2d posTmp : randPositions)
                            if (this.animalMap.get(posTmp) != null && this.animalMap.get(posTmp).isEmpty()) {
                                Genotype newGenes = Genotype.childGenotype(cat, dog);
                                Animal bird = cat.copulation(dog, posTmp, newGenes);
                                place(bird);
                                break;
                            }
                    }
                }
            }
        }
    }

    public void generateGrass() {
        // zmiana względem opisu w projekcie -> trawa możesz urosnąć i dawać więcej energii
        generateJungleGrass();
        generateStepGrass();
    }

    public void generateJungleGrass() {
        Random rand = new Random();
        Vector2d randomPos = new Vector2d(lowerLeftJgl.x + rand.nextInt(parameters.jungleWidth), lowerLeftJgl.y + rand.nextInt(parameters.jungleHeight));
        if (this.grassMap.containsKey(randomPos)) {
            this.grassMap.replace(randomPos, this.grassMap.get(randomPos).grow());
        } else {
            this.grassMap.put(randomPos, new Grass(randomPos));
        }
    }

    public void generateStepGrass() {
        Random rand = new Random();
        Vector2d randomPos = new Vector2d(rand.nextInt(parameters.worldWidth), rand.nextInt(parameters.worldHeight));
        while (!isStep(randomPos)) {
            randomPos = new Vector2d(rand.nextInt(parameters.worldWidth), rand.nextInt(parameters.worldHeight));
        }
        if (this.grassMap.containsKey(randomPos))
            this.grassMap.replace(randomPos, this.grassMap.get(randomPos).grow());
        else
            this.grassMap.put(randomPos, new Grass(randomPos));
    }

    public boolean isStep(Vector2d position) {
        return !(position.precedes(upperRightJgl) && position.follows(lowerLeftJgl));
    }

    public void dayOfLife(Integer days) throws InterruptedException, IOException {
        int day = 0;
        while (day < days) {
            removeTheDead();
            moveTheAnimals();
            eatTheGrass();
            copulateAll();
            for(int i = 0; i < WorldMap.parameters.grassPerDay; i++) generateGrass();
            System.out.println(this.toString());
            System.out.println("dzień: " + day);
            System.out.println("żywych zwierząt: " + this.animalList.size());
            System.out.println("trawy: " + this.grassMap.size());
            System.out.println("licznik śmierci: " + deadAnimals);
            Thread.sleep(1000 / WorldMap.parameters.fps);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            day++;
        }

    }

    public String toString() {
        return new MapVisualizer(this).draw(lowerLeftCorner, upperRightCorner);
    }
}
