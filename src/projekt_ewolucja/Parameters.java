package projekt_ewolucja;

public class Parameters {
    Integer worldWidth;
    Integer worldHeight;
    Integer jungleWidth;
    Integer jungleHeight;
    Integer grassStartingEnergy;
    //ile energii daje trawa po wyrośnięciu
    Integer growValue;
    //wzrost wartości energii trawy gdy wyrośnie w tym samym miejscu
    Integer energyToCopulate;
    Integer animalsToSpawn;
    Integer animalInitialEnergy;
    Integer numberOfDays;
    Integer refreshTime;
    Integer movementValue;
    Integer grassPerDay;
    Integer saveEveryN;
    Integer twoSimulations;
    //grassPerDay -> per dzungla + step czyli dla 1 narasta 2 trawy

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
                ", refreshTime=" + refreshTime +
                ", movementValue=" + movementValue +
                ", grassPerDay=" + grassPerDay +
                ", saveEveryN=" + saveEveryN +
                ", twoSimulations=" + twoSimulations +
                '}';
    }
}
