package projekt_ewolucja;

public class Parameters {
    int worldWidth;
    int worldHeight;
    int jungleWidth;
    int jungleHeight;
    int grassStartingEnergy;
    //ile energii daje trawa po wyrośnięciu
    int growValue;
    //wzrost wartości energii trawy gdy wyrośnie w tym samym miejscu
    int energyToCopulate;
    int animalsToSpawn;
    int animalInitialEnergy;
    int numberOfDays;
    int fps;
    int movementValue;

    @Override
    public String toString() {
        return "Parameters{" +
                "worldWidth=" + worldWidth +
                ", worldHeight=" + worldHeight +
                ", jungleWidth=" + jungleWidth +
                ", jungleHeight=" + jungleHeight +
                ", grassStartingEnergy=" + grassStartingEnergy +
                ", growValue=" + growValue +
                ", energyToCopulate=" + energyToCopulate +
                ", animalsToSpawn=" + animalsToSpawn +
                ", animalInitialEnergy=" + animalInitialEnergy +
                ", numberOfDays=" + numberOfDays +
                ", fps=" + fps +
                ", movementValue=" + movementValue +
                '}';
    }
}
