package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;
import java.awt.*;

public class LabeledText extends Lable {
    public JLabel text = new JLabel();

    public LabeledText(String label, boolean shouldBeMultiline) {
        super(label, shouldBeMultiline);

        text.setPreferredSize(new Dimension(100, 30));
        add(text, gbc);
    }

    public LabeledText(boolean shouldBeMultiline) {
        this("", shouldBeMultiline);
    }

    public LabeledText(String label) {
        this(label, true);
    }

    public LabeledText() {
        this("");
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
