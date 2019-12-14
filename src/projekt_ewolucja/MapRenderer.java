package projekt_ewolucja;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MapRenderer extends JPanel implements MouseMotionListener {

    public WorldMap map;
    public Simulation simulation;
    public EvolutionInformation evolutionInformation;

    public MapRenderer(WorldMap map, Simulation sim) {
        this.map = map;
        this.simulation = sim;
        this.evolutionInformation = sim.evolutionInformation;
        add(evolutionInformation.l);
        evolutionInformation.l.setText("--");
        addMouseMotionListener(this);
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

    public void mouseMoved(MouseEvent e) {
        Vector2d position = new Vector2d(e.getX()/Math.round(getWidth() / WorldMap.parameters.worldWidth),e.getY()/Math.round(getHeight() / WorldMap.parameters.worldHeight));
        Vector2d displayPos = new Vector2d(e.getX()/Math.round(getWidth() / WorldMap.parameters.worldWidth),WorldMap.parameters.worldHeight - e.getY()/Math.round(getHeight() / WorldMap.parameters.worldHeight));
        if(map.objectAt(position) != null) {
            evolutionInformation.l.setText(displayPos.toString());
            evolutionInformation.pos = displayPos.toString();
            evolutionInformation.what = "trawę";
            if((this.map.animalMap.get(position) != null && !this.map.animalMap.get(position).isEmpty())) {
                Animal temp = this.map.animalMap.get(position).getFirst();
                evolutionInformation.what = "zwierzę";
                evolutionInformation.animalChildren = temp.children.toString();
                evolutionInformation.animalEnergy = temp.energy.toString();
                evolutionInformation.genotype = temp.genotype.toString();
            } else evolutionInformation.grassEnergy = map.grassMap.get(position).energyValue.toString();

        }
        else {
            evolutionInformation.l.setText(displayPos.toString());
            evolutionInformation.pos = displayPos.toString();
            if(map.isStep(position)) evolutionInformation.what = "step";
            else evolutionInformation.what = "dżunglę";
        }
    }

    public void mouseDragged(MouseEvent e) {

    }
}