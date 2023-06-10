package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledPasswordField extends LabeledField implements TextField {
    public JPasswordField passwordField = new JPasswordField();

    public LabeledPasswordField(String label, boolean shouldBeMultiline) {
        super(label);
        passwordField.setPreferredSize(new Dimension(250, 30));
        if (shouldBeMultiline) gbc.gridy++;
        add(passwordField, gbc);
    }

    public LabeledPasswordField(String label) {
        this(label, true);
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
