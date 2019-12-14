package projekt_ewolucja;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            String parameters = Files.readString(Paths.get("parameters.json"));
            Parameters pars = new Gson().fromJson(parameters, Parameters.class);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame settings = new SettingsMenu("Settings" , pars);
                    settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    settings.setVisible(true);
                    settings.setSize(400,600);
                }
            });

        } catch (IllegalArgumentException | IOException ex) {
            System.out.println(ex.toString());
        }

    }
}
