package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends Lable implements TextField {
    public JTextField textField = new JTextField();


    public LabeledTextField(String label, boolean shouldBeMultiline) {
        super(label, shouldBeMultiline);

        textField.setPreferredSize(new Dimension(250, 30));
        add(textField, gbc);
    }

    public LabeledTextField() {
        super();
    }

    public LabeledTextField(boolean shouldBeMultiline) {
        super(shouldBeMultiline);
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
