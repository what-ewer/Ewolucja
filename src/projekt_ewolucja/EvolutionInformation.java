package projekt_ewolucja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EvolutionInformation extends JPanel {

    public WorldMap map;
    public Simulation simulation;
    public Integer totalDays;

    public EvolutionInformation(WorldMap map, Simulation simulation) {
        this.map = map;
        this.simulation = simulation;
        totalDays = 0;
        setLayout(new BorderLayout());
        JButton pauseButton = new JButton("Pauza");
        pauseButton.setHorizontalAlignment(SwingConstants.LEFT);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(simulation.timer.isRunning()) simulation.timer.stop();
                else simulation.timer.start();
            }
        });
        this.add(pauseButton, BorderLayout.SOUTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulation.frame.getWidth() * 0.3), simulation.frame.getHeight() - 38);
        this.setLocation((int)(simulation.frame.getWidth() * 0.7), 0);
        totalDays++;

        g.drawString("Symulacja trwa już: ", 10, 38) ;
        g.drawString(totalDays + " dni", 10 , 53);

        g.drawString("Aktualnie żyje: ", 10, 78) ;
        g.drawString(map.animalList.size() + " zwierząt", 10 , 93);

        g.drawString("Na mapie jest: ", 10, 118) ;
        g.drawString(map.grassList.size() + " trawy", 10 , 133);

        g.drawString("Podczas symulacji zginęło: ", 10, 158) ;
        g.drawString(WorldMap.deadAnimals + " zwierząt", 10 , 173);

        g.drawString("Podczas symulacji zwierzęta zjadły: ", 10, 198) ;
        g.drawString(WorldMap.eatenGrass + " trawy", 10 , 213);

        g.drawString("Średnia ilość dzieci żywych zwierząt: ", 10, 238) ;
        if(map.animalList.size() > 0) g.drawString((double)WorldMap.children/map.animalList.size() + "", 10 , 253);
        else g.drawString("0", 10 , 253);

        g.drawString("Średnia długość życia zwierząt: ", 10, 278);
        if(WorldMap.deadAnimals > 0) g.drawString((double)WorldMap.lifespan/WorldMap.deadAnimals + "", 10 , 293);
        else g.drawString("0", 10 , 293);

        Integer energy = 0;
        for(Animal a : map.animalList) energy+= a.energy;
        g.drawString("Średni poziom energii zwierząt: " , 10, 318);
        if(map.animalList.size() > 0) g.drawString((double)energy/map.animalList.size() + "", 10 , 333);
        else g.drawString("0", 10 , 333);

        if(WorldMap.parameters.saveEveryN != 0){
            if (totalDays == WorldMap.parameters.saveEveryN ){
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("ewolucja.txt", "UTF-8");
                } catch (FileNotFoundException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                assert writer != null;
                writer.println("Czas trwania symulacji: " + totalDays);
                writer.println("W danym momencie żyło: " + map.animalList.size() + " zwierząt");
                writer.println("Na mapie było wtedy: " + map.grassList.size() + " trawy");
                writer.println("Do tego czasu zginęło: " + WorldMap.deadAnimals + " zwierząt");
                writer.println("Do tego czasu zwierzęta zjadły: " + WorldMap.eatenGrass + " trawy");
                if(map.animalList.size() > 0) writer.println("W danym momencie średnia ilość dzieci wynosiła: " + (double)WorldMap.children/map.animalList.size());
                else writer.println("W danym momencie średnia ilość dzieci wynosiła: 0");
                if(WorldMap.deadAnimals > 0) writer.println("W danym momencie średnia długość życia wynosiła: " + (double)WorldMap.lifespan/WorldMap.deadAnimals + " dni");
                else writer.println("--do tego czasu nie umarło żadne zwierzę");
                if(map.animalList.size() > 0) writer.println("Średni poziom energii zwierząt: " + energy/map.animalList.size());
                else writer.println("Średni poziom energii zwierząt: 0");
                writer.println("\n\n");
                writer.close();
            } else if (totalDays % WorldMap.parameters.saveEveryN == 0){
                try(FileWriter fw = new FileWriter("ewolucja.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println("Czas trwania symulacji: " + totalDays);
                    out.println("W danym momencie żyło: " + map.animalList.size() + " zwierząt");
                    out.println("Na mapie było wtedy: " + map.grassList.size() + " trawy");
                    out.println("Do tego czasu zginęło: " + WorldMap.deadAnimals + " zwierząt");
                    out.println("Do tego czasu zwierzęta zjadły: " + WorldMap.eatenGrass + " trawy");
                    if(map.animalList.size() > 0) out.println("W danym momencie średnia ilość dzieci wynosiła: " + (double)WorldMap.children/map.animalList.size());
                    else out.println("W danym momencie średnia ilość dzieci wynosiła: 0");
                    if(WorldMap.deadAnimals > 0) out.println("W danym momencie średnia długość życia wynosiła: " + (double)WorldMap.lifespan/WorldMap.deadAnimals + " dni");
                    else out.println("--do tego czasu nie umarło żadne zwierzę");
                    if(map.animalList.size() > 0) out.println("Średni poziom energii zwierząt: " + energy/map.animalList.size());
                    else out.println("Średni poziom energii zwierząt: 0");
                    out.println("\n\n");
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            }
        }

    }

}