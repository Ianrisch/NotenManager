package org.notenmanager.UI.Compenents.LabledField;

import javax.swing.*;

public class LabeledText extends Lable {
    public JLabel text = new JLabel();

    public LabeledText(String label, boolean shouldBeMultiline){
        super(label,shouldBeMultiline);
    }
    public LabeledText( boolean shouldBeMultiline){
        super(shouldBeMultiline);
    }
    public LabeledText(String label){
        super(label);
    }
    public LabeledText(){
        super();
    }
    public void setText(String text){
        this.text.setText(text);
    }
}
