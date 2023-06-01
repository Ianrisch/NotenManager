package org.notenmanager.UI;

import javax.swing.*;

public class LabeledTextField extends JComponent {
    public JLabel label =new JLabel();
    public JTextField textField=new JTextField();

    public LabeledTextField(){
        super();
        this.add(label);
        this.add(textField);
    }
}
