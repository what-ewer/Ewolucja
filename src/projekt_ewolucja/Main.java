package projekt_ewolucja;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        try {
            String parameters = Files.readString(Paths.get("parameters.json"));
            Parameters pars = new Gson().fromJson(parameters, Parameters.class);

            WorldMap polska = new WorldMap(pars);

            for (int i = 0; i < WorldMap.parameters.animalsToSpawn; i++) {
                polska.place(new Animal(polska, Vector2d.randomVector(), WorldMap.parameters.animalInitialEnergy, null, true));
            }

            polska.dayOfLife(WorldMap.parameters.numberOfDays);
            System.out.println(polska.toString());
            System.out.println("Symulacja trwałą: " + WorldMap.parameters.numberOfDays + " dni");
            System.out.println("Na końcu symulacji zostało: " + polska.animalList.size() + " żywych zwierząt");
            System.out.println("Na końcu symulacji zostało: " + polska.grassMap.size() + " trawy" );
            System.out.println("Podczas symulacji zginęło: " + WorldMap.deadAnimals + " zwierząt");

        } catch (IllegalArgumentException | InterruptedException | IOException ex) {
            System.out.println(ex.toString());
        }

    }
}
