package org.notenmanager.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel base= new JPanel();
    private JComboBox ComboBox= new JComboBox();
    private JPanel content = new JPanel();

    private LabeledTextField username=new LabeledTextField("Username");
    private LabeledTextField password=new LabeledTextField("Password");
    private LabeledTextField repeatedPassword=new LabeledTextField("Repeat Password");
    private LabeledTextField eMail=new LabeledTextField("EMail");
    private LabeledTextField schoolClass=new LabeledTextField("Class");


    public static void main(String[] args) {
        JFrame frame = new JFrame("loginRegistry");
        frame.setContentPane(new Login().base);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);
    }

    public Login() {
        createUIComponents();
        ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) ComboBox.getSelectedItem();

                switch (selectedItem){
                    case "Login":

                        break;
                    case"Registration":
                        break;
                    default:
                        break;

                }
            }
        });

    }

    private void createUIComponents() {
        base.setLayout(new GridLayout());
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.anchor=GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill=GridBagConstraints.VERTICAL;
        gridBagConstraints.gridy=0;
        base.add(ComboBox,gridBagConstraints);
        gridBagConstraints.weighty=1;
        gridBagConstraints.gridy++;
        base.add(content,gridBagConstraints);
        //base.setAlignmentX();
        ComboBox.setPreferredSize(new Dimension(150,30));
        content.setLayout(new GridLayout(5,1));
        base.setLayout(new FlowLayout());
        content.add(username);
        content.add(password);
        content.add(repeatedPassword);
        content.add(eMail);
        content.add(schoolClass);

    }

}
