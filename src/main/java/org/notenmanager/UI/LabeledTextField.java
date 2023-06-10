package org.notenmanager.UI;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends JPanel {
    public JLabel label =new JLabel();
    public JTextField textField=new JTextField();



    public LabeledTextField(String label){
        super();
        this.label.setText(label);
        this.add(this.label);
        this.add(textField);

    }
//    @Override
//    public Dimension getPreferredSize(){
//        return new Dimension(100,100);
//    }
}
