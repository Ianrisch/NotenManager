package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends LabeledField implements TextField {
    public JTextField textField = new JTextField();


    public LabeledTextField(String label, boolean shouldBeMultiline) {
        super(label);

        textField.setPreferredSize(new Dimension(250, 30));
        if (shouldBeMultiline) gbc.gridy++;
        add(textField, gbc);

    }

    public LabeledTextField(String label) {
        this(label, true);
    }

    @Override
    public String getText() {
        return textField.getText();
    }

    @Override
    public void clearTextField() {
        textField.setText("");
    }
}
