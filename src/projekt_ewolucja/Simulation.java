package projekt_ewolucja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation implements ActionListener {

    public WorldMap map;
    public JFrame frame;
    public MapRenderer mapRenderer;
    public EvolutionInformation evolutionInformation;
    public Timer timer;

    public Simulation(WorldMap map) {

        this.map = map;

        timer = new Timer(WorldMap.parameters.refreshTime, this);

        frame = new JFrame("Ewolucja");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        mapRenderer = new MapRenderer(map, this);
        mapRenderer.setSize(new Dimension(1, 1));

        evolutionInformation = new EvolutionInformation(map, this);
        evolutionInformation.setSize(1, 1);

        frame.add(mapRenderer);
        frame.add(evolutionInformation);

    }

    public void startSimulation() {

        for (int i = 0; i < WorldMap.parameters.animalsToSpawn; i++) {
            map.place(new Animal(map, Vector2d.randomVector(), WorldMap.parameters.animalInitialEnergy, null, true));
        }
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(evolutionInformation.totalDays.equals(WorldMap.parameters.numberOfDays)) timer.stop();

        evolutionInformation.repaint();
        mapRenderer.repaint();

        map.removeTheDead();
        map.moveTheAnimals();
        map.eatTheGrass();
        map.copulateAll();
        for(int i = 0; i < WorldMap.parameters.grassPerDay; i++) map.generateGrass();

    }
}