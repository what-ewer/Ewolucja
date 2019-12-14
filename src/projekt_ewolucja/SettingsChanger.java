package projekt_ewolucja;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.activation.ActivationID;

public class SettingsChanger extends JPanel {

    public JLabel worldWidth;
    public JLabel worldHeight;
    public JLabel jungleWidth;
    public JLabel jungleHeight;
    public JLabel numberOfDays;
    public JLabel refreshTime;
    public JLabel grassStartingValue;
    public JLabel growValue;
    public JLabel grassPerDay;
    public JLabel animalsToSpawn;
    public JLabel animalInitialEnergy;
    public JLabel energyToCopulate;
    public JLabel movementValue;
    public JLabel saveEveryN;
    public JLabel twoSimulations;

    public JTextField worldWidthField;
    public JTextField worldHeightField;
    public JTextField jungleWidthField;
    public JTextField jungleHeightField;
    public JTextField numberOfDaysField;
    public JTextField refreshTimeField;
    public JTextField animalInitialEnergyField;
    public JTextField energyToCopulateField;
    public JTextField animalsToSpawnField;
    public JTextField movementValueField;
    public JTextField grassStartingValueField;
    public JTextField growValueField;
    public JTextField grassPerDayField;
    public JTextField saveEveryNField;
    public JTextField twoSimulationsField;


    public SettingsChanger(Parameters parameters){
        Dimension size = getPreferredSize();
        size.width = 400;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Ustawienia symulacji"));

        worldWidth = new JLabel("Długość świata: ");
        worldHeight = new JLabel("Szerokość świata: ");
        jungleWidth = new JLabel("Długość dżungli: ");
        jungleHeight = new JLabel("Szerokość dżungli: ");
        numberOfDays = new JLabel("Czas trwania symulacji: ");
        refreshTime = new JLabel("Częstotliwość odświeżania: ");
        grassStartingValue = new JLabel("Wartość energii trawy: ");
        growValue = new JLabel("Wartość rozwoju trawy: ");
        grassPerDay = new JLabel("Ilość nowej trawy codziennie: ");
        animalsToSpawn = new JLabel("Ilość zwierząt na początku symulacji: ");
        animalInitialEnergy = new JLabel("Początkowa energia zwierzęcia: ");
        energyToCopulate = new JLabel("Wymagana energia do kopulacji: ");
        movementValue = new JLabel("Koszt energii na dzień: ");
        saveEveryN = new JLabel("Zapisuj do pliku co N dni: ");
        twoSimulations = new JLabel("Czy puścić 2 symulacje? (tak = 1)");

        worldWidthField = new JTextField(10);
        worldHeightField = new JTextField(10);
        jungleWidthField = new JTextField(10);
        jungleHeightField = new JTextField(10);
        numberOfDaysField = new JTextField(10);
        refreshTimeField = new JTextField(10);
        animalInitialEnergyField = new JTextField(10);
        energyToCopulateField = new JTextField(10);
        animalsToSpawnField = new JTextField(10);
        movementValueField = new JTextField(10);
        grassStartingValueField = new JTextField(10);
        growValueField = new JTextField(10);
        grassPerDayField = new JTextField(10);
        saveEveryNField = new JTextField(10);
        twoSimulationsField = new JTextField(10);

        worldWidthField.setText(parameters.worldWidth.toString());
        worldHeightField.setText(parameters.worldHeight.toString());
        jungleWidthField.setText(parameters.jungleWidth.toString());
        jungleHeightField.setText(parameters.jungleHeight.toString());
        numberOfDaysField.setText(parameters.numberOfDays.toString());
        refreshTimeField.setText(parameters.refreshTime.toString());
        animalInitialEnergyField.setText(parameters.animalInitialEnergy.toString());
        energyToCopulateField.setText(parameters.energyToCopulate.toString());
        animalsToSpawnField.setText(parameters.animalsToSpawn.toString());
        movementValueField.setText(parameters.movementValue.toString());
        grassStartingValueField.setText(parameters.grassStartingEnergy.toString());
        growValueField.setText(parameters.growValue.toString());
        grassPerDayField.setText(parameters.grassPerDay.toString());
        saveEveryNField.setText(parameters.saveEveryN.toString());
        twoSimulationsField.setText(parameters.twoSimulations.toString());

        JButton startButton = new JButton("Zacznij symulację");

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //1st column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(worldWidth, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(worldHeight, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        add(jungleWidth, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        add(jungleHeight, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        add(numberOfDays, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        add(refreshTime, gc);

        gc.gridx = 0;
        gc.gridy = 6;
        add(animalInitialEnergy, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        add(energyToCopulate, gc);

        gc.gridx = 0;
        gc.gridy = 8;
        add(animalsToSpawn, gc);

        gc.gridx = 0;
        gc.gridy = 9;
        add(movementValue, gc);

        gc.gridx = 0;
        gc.gridy = 10;
        add(grassStartingValue, gc);

        gc.gridx = 0;
        gc.gridy = 11;
        add(growValue, gc);

        gc.gridx = 0;
        gc.gridy = 12;
        add(grassPerDay, gc);

        gc.gridx = 0;
        gc.gridy = 13;
        add(twoSimulations, gc);

        gc.gridx = 0;
        gc.gridy = 14;
        add(saveEveryN, gc);

        gc.gridx = 0;
        gc.gridy = 15;
        add(startButton, gc);

        //2nd Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(worldWidthField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(worldHeightField, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(jungleWidthField, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(jungleHeightField, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        add(numberOfDaysField, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        add(refreshTimeField, gc);

        gc.gridx = 1;
        gc.gridy = 6;
        add(animalInitialEnergyField, gc);

        gc.gridx = 1;
        gc.gridy = 7;
        add(energyToCopulateField, gc);

        gc.gridx = 1;
        gc.gridy = 8;
        add(animalsToSpawnField, gc);

        gc.gridx = 1;
        gc.gridy = 9;
        add(movementValueField, gc);

        gc.gridx = 1;
        gc.gridy = 10;
        add(grassStartingValueField, gc);

        gc.gridx = 1;
        gc.gridy = 11;
        add(growValueField, gc);

        gc.gridx = 1;
        gc.gridy = 12;
        add(grassPerDayField, gc);

        gc.gridx = 1;
        gc.gridy = 13;
        add(twoSimulationsField, gc);

        gc.gridx = 1;
        gc.gridy = 14;
        add(saveEveryNField, gc);

        gc.gridx = 1;
        gc.gridy = 15;
        add(startButton, gc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parameters.worldWidth = Integer.parseInt(worldWidthField.getText());
                parameters.worldHeight = Integer.parseInt(worldHeightField.getText());
                parameters.jungleWidth = Integer.parseInt(jungleWidthField.getText());
                parameters.jungleHeight = Integer.parseInt(jungleHeightField.getText());
                parameters.numberOfDays = Integer.parseInt(numberOfDaysField.getText());
                parameters.refreshTime = Integer.parseInt(refreshTimeField.getText());
                parameters.animalInitialEnergy = Integer.parseInt(animalInitialEnergyField.getText());
                parameters.energyToCopulate = Integer.parseInt(energyToCopulateField.getText());
                parameters.animalsToSpawn = Integer.parseInt(animalsToSpawnField.getText());
                parameters.movementValue = Integer.parseInt(movementValueField.getText());
                parameters.grassStartingEnergy = Integer.parseInt(grassStartingValueField.getText());
                parameters.growValue = Integer.parseInt(growValueField.getText());
                parameters.grassPerDay = Integer.parseInt(grassPerDayField.getText());
                parameters.saveEveryN = Integer.parseInt(saveEveryNField.getText());
                parameters.twoSimulations = Integer.parseInt(twoSimulationsField.getText());

                WorldMap map1 = new WorldMap(parameters);

                Simulation sim = new Simulation(map1, "sim1");
                sim.startSimulation();

                if(parameters.twoSimulations.equals(1)){
                    WorldMap map2 = new WorldMap(parameters);
                    Simulation sim2 = new Simulation(map2, "sim2");
                    sim2.startSimulation();
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    sim2.frame.setLocation((int) (ge.getCenterPoint().getX()),0);
                }
            }
        });
    }
}
