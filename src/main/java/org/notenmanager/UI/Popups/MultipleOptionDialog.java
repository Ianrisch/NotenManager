package org.notenmanager.UI.Popups;

import org.notenmanager.Models.Grade;
import org.notenmanager.Models.Person;
import org.notenmanager.Models.SchoolSubject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MultipleOptionDialog {
    public static SchoolSubject showClassInputMessageDialog(Component parent, String title, String subjectNameDescription, String personNameDescription) {

        JTextField subjectName = new JTextField();
        JTextField personName = new JTextField();

        Object[] inputFields = {subjectNameDescription, subjectName,
                personNameDescription, personName};

        int option = JOptionPane.showConfirmDialog(parent, inputFields, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String [] splitName = personName.getText().split(" ");
            if(splitName.length < 2) return null;
            return new SchoolSubject(new Person(splitName[0],splitName[1]) , subjectName.getText(), new ArrayList());
        }
        return null;
    }
    public static Grade showGradeInputMessageDialog(Component parent, String title, String gradeDescription, String gravityDescription) {

        JSpinner value = new JSpinner(new SpinnerNumberModel(1.0f,1.0f,6.0f,0.5f));
        JSpinner gravity = new JSpinner(new SpinnerNumberModel(1.0d,1.0d,6.0d,0.5d));



        Object[] inputFields = {gradeDescription, value,
                gravityDescription, gravity};

        int option = JOptionPane.showConfirmDialog(parent, inputFields, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            return new Grade((Double) value.getValue(),(Double) gravity.getValue());
        }
        return null;
    }
}
