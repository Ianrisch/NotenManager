package org.notenmanager.UI;

import javax.swing.*;
import java.awt.*;

public class LabeledField extends JPanel {
    public JLabel label = new JLabel();
    protected GridBagConstraints gbc = new GridBagConstraints();

    public LabeledField(String label) {
        super();
        setLayout(new GridBagLayout());
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy=0;

        this.label.setText(label);
        add(this.label, gbc);


    }

}
