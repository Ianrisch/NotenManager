package org.notenmanager.UI;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends LabeledField implements TextField {
    public JTextField textField = new JTextField();


    public LabeledTextField(String label) {
        super(label);

        textField.setPreferredSize(new Dimension(250, 30));
        gbc.gridy++;
        add(textField, gbc);

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
