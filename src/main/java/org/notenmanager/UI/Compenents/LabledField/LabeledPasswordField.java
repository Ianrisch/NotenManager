package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledPasswordField extends Lable implements TextField {
    public JPasswordField passwordField = new JPasswordField();

    public LabeledPasswordField(String label, boolean shouldBeMultiline) {
        super(label, shouldBeMultiline);

        passwordField.setPreferredSize(new Dimension(250, 30));
        add(passwordField, gbc);
    }

    public LabeledPasswordField() {
        this("");
    }

    public LabeledPasswordField(String label) {
        this(label, true);
    }

    public LabeledPasswordField(boolean shouldBeMultiline) {
        this("", shouldBeMultiline);
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
