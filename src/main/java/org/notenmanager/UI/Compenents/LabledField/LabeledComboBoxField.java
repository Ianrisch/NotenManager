package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledComboBoxField extends Lable {
    public JComboBox comboBox = new JComboBox();

    public LabeledComboBoxField(String label, boolean shouldBeMultiline) {
        super(label, shouldBeMultiline);

        comboBox.setPreferredSize(new Dimension(150, 30));
        add(comboBox, gbc);
    }

    public LabeledComboBoxField(String label) {
        this(label, true);
    }

    public LabeledComboBoxField() {
        this("");
    }

    public LabeledComboBoxField(boolean shouldBeMultiline) {
        this("", shouldBeMultiline);
    }

    public String getSelectedItem() {
        Object selectedItem = comboBox.getSelectedItem();
        if (selectedItem == null) return null;
        return selectedItem.toString();
    }
}
