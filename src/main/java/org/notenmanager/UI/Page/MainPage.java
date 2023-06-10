package org.notenmanager.UI.Page;

import org.notenmanager.Utils.Dataservice.DataService;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    private final DataService dataService;
    private final JPanel base = new JPanel();

    public MainPage(String title, DataService dataService) {
        super(title);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));
        setSize(new Dimension(400, 600));

        this.dataService = dataService;

        createUIComponents();

        setContentPane(base);

    }

    private void createUIComponents() {
    }
}
