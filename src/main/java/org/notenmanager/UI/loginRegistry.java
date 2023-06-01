package org.notenmanager.UI;

import javax.swing.*;
import java.awt.event.*;

public class loginRegistry {
    private JPanel base;
    private JComboBox ComboBox;
    private JPanel content = new JPanel();

    private LabeledTextField username=new LabeledTextField();
    private LabeledTextField password=new LabeledTextField();
    private LabeledTextField repeatedPassword=new LabeledTextField();
    private LabeledTextField eMail=new LabeledTextField();
    private LabeledTextField schoolClass=new LabeledTextField();


    public static void main(String[] args) {
        JFrame frame = new JFrame("loginRegistry");
        frame.setContentPane(new loginRegistry().base);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public loginRegistry() {
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
        content.add(username);
        content.add(password);
        content.add(repeatedPassword);
        content.add(eMail);
        content.add(schoolClass);

    }
}