package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledPasswordField extends LabeledField implements TextField {
    public JPasswordField passwordField = new JPasswordField();

    public LabeledPasswordField(String label) {
        super(label);
        passwordField.setPreferredSize(new Dimension(250, 30));
        gbc.gridy++;
        add(passwordField, gbc);
    }

    @Override
    public String getText() {
        return new String(passwordField.getPassword());
    }

    @Override
    public void clearTextField() {
        passwordField.setText("");
    }
}
