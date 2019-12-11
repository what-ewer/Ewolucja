package projekt_ewolucja;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.*;

public class Main {
    public static void main (String[] args){
        try{
            String parameters = Files.readString(Paths.get("parameters.json"));
            Parameters pars = new Gson().fromJson(parameters, Parameters.class);

            WorldMap polska = new WorldMap(pars);

            for(int i = 0; i < WorldMap.parameters.animalsToSpawn; i++){
                polska.place(new Animal(polska, Vector2d.randomVector(), WorldMap.parameters.animalInitialEnergy, null ,true));
            }

            polska.dayOfLife(WorldMap.parameters.numberOfDays);
            System.out.println(polska.toString());

        }
        catch (IllegalArgumentException | InterruptedException | IOException ex){
            System.out.println(ex.toString());
        }

    }
}
