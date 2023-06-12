package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends Lable implements TextField {
    public JTextField textField = new JTextField();


    public LabeledTextField(String label, boolean shouldBeMultiline) {
        super(label, shouldBeMultiline);

        textField.setPreferredSize(new Dimension(250, 30));
        this.add(textField, gbc);
    }

    public LabeledTextField() {
        this("");
        ;
    }

    public LabeledTextField(String label) {
        this(label, true);
        ;
    }

    public LabeledTextField(boolean shouldBeMultiline) {
        this("", shouldBeMultiline);
        ;
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
