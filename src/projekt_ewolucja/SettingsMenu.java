package projekt_ewolucja;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JFrame {

    private SettingsChanger settings;

    public SettingsMenu(String title, Parameters parameters){
        super(title);

        setLayout(new BorderLayout());

        //Swing Components
        settings = new SettingsChanger(parameters);

        //Adding components to content pane

        Container c = getContentPane();

        c.add(settings, BorderLayout.WEST);
    }
}
