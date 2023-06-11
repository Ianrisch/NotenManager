package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class Lable extends JPanel {
    public JLabel label = new JLabel();
    protected GridBagConstraints gbc = new GridBagConstraints();

    public Lable(String label, boolean shouldBeMultiline) {
        super();
        setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx = 0;

        this.label.setText(label);
        add(this.label, gbc);

        if (shouldBeMultiline) gbc.gridy++;
        else gbc.gridx++;
    }

    public Lable(boolean shouldBeMultiline) {
        this("", shouldBeMultiline);
    }

    public Lable(String label) {
        this(label, true);
    }

    public Lable() {
        this("");
    }

    public void setLabel(String text) {
        label.setText(text);
    }

}
