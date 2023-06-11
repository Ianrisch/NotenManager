package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledComboBoxField extends Lable {
    public JComboBox comboBox = new JComboBox();

    public LabeledComboBoxField(String label, boolean shouldBeMultiline) {
        super(label);

        comboBox.setPreferredSize(new Dimension(250, 30));
        add(comboBox, gbc);
    }

    public LabeledComboBoxField(String label) {
        super(label);
    }

    public LabeledComboBoxField() {
        super();
    }

    public LabeledComboBoxField(boolean shouldBeMultiline) {
        super(shouldBeMultiline);
    }
}
