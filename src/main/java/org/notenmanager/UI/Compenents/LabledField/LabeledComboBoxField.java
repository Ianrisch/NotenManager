package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledComboBoxField extends LabeledField{
    public JComboBox comboBox = new JComboBox();
    public LabeledComboBoxField(String label, boolean shouldBeMultiline) {
        super(label);
        comboBox.setPreferredSize(new Dimension(250, 30));
        if (shouldBeMultiline) gbc.gridy++;
        add(comboBox, gbc);
    }
    public LabeledComboBoxField(String label){
        this(label, true);
    }
}
