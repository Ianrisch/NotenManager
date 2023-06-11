package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;

public class LabeledText extends Lable {
    public JLabel text = new JLabel();

    public LabeledText(String label, boolean shouldBeMultiline){
        super(label, shouldBeMultiline);
    }
    public LabeledText( boolean shouldBeMultiline){
        this("", shouldBeMultiline);
    }
    public LabeledText(String label){
        this(label, true);
    }
    public LabeledText(){
        this("");
    }
    public void setText(String text){
        this.text.setText(text);
    }
}
