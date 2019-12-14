package projekt_ewolucja;

import javax.swing.*;
import java.awt.*;

public class MapRenderer extends JPanel {

    public WorldMap map;
    public Simulation simulation;

    public MapRenderer(WorldMap map, Simulation sim) {
        this.map = map;
        this.simulation = sim;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulation.frame.getWidth() * 0.7), simulation.frame.getHeight()-38);
        this.setLocation(0, 0);
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = Math.round(width / WorldMap.parameters.worldWidth);
        int heightScale = height / WorldMap.parameters.worldHeight;

        g.setColor(new Color(185, 224, 111));
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 160, 36));
        g.fillRect(map.lowerLeftJgl.x * widthScale,
                map.lowerLeftJgl.y * heightScale,
                WorldMap.parameters.jungleWidth * widthScale,
                WorldMap.parameters.jungleHeight * heightScale);

        for (Grass grass : map.grassList) {
            g.setColor(grass.toColor());
            int y = (grass.grassPosition).y * heightScale;
            int x = (grass.grassPosition).x * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }

        for (Animal a : map.animalList) {
            g.setColor(a.toColor());
            int y = (a.position).y * heightScale;
            int x = (a.position).x * widthScale;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }
}