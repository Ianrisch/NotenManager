package org.notenmanager.UI.Page;

import org.notenmanager.Models.Grade;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.UI.Compenents.LabledField.LabeledComboBoxField;
import org.notenmanager.UI.Compenents.LabledField.LabeledText;
import org.notenmanager.UI.Compenents.LabledField.LabeledTextField;
import org.notenmanager.Utils.Constants.Lang.LanguageConstants;
import org.notenmanager.Utils.Dataservice.DataService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainPage extends JFrame {
    private final DataService dataService;
    private User user;
    private LabeledComboBoxField schoolSubject =new LabeledComboBoxField(false);

    private LabeledText teacher=new LabeledText(false);
    private LabeledText schoolClass =new LabeledText(false);
    private JScrollPane scrollPane=new JScrollPane();
    private List<SchoolSubject> schoolSubjects;
    private final JPanel base = new JPanel();

    private LanguageConstants languageConstants;
    public MainPage(DataService dataService, User user, List<SchoolSubject> schoolSubjects,LanguageConstants languageConstants) {
        super();
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));
        setSize(new Dimension(400, 600));

        this.dataService = dataService;
        this.user = user;
        updateTitle();
        this.schoolSubjects = schoolSubjects;
        this.languageConstants = languageConstants;

        setupComboBox();
        createUIComponents();

        setContentPane(base);

    }
    private void setupComboBox(){
        for(SchoolSubject sb:schoolSubjects){
            schoolSubject.comboBox.addItem(sb.name);
        }
        schoolSubject.comboBox.addActionListener(e -> {
            String selectedItem = (String) schoolSubject.comboBox.getSelectedItem();
            for (SchoolSubject sa : schoolSubjects) {
                if (sa.name == selectedItem) {
                    for(Grade grade:sa.grades){
                        scrollPane.add(new JLabel("Grade:"+grade.value+" Gravity:"+grade.gravity));
                    }
                }
            }
        });
    }
    private void setLabels(){
        teacher.setLabel(languageConstants.Teacher);
        schoolClass.setLabel(languageConstants.SchoolClass);
        schoolSubject.setLabel(languageConstants.Subject);
    }
    public void updateTitle() {
        setTitle(getNewTitle());
    }

    private String getNewTitle() {
        return "NotenManager of User: " + user.username;
    }

    private void createUIComponents() {
        base.setLayout(new GridBagLayout());
        base.setBorder(new EmptyBorder(25,25,25,25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.ipady = 20;

        base.add(teacher, gbc);
        gbc.gridy++;
        base.add(schoolClass, gbc);
        gbc.gridy++;
        base.add(schoolSubject, gbc);
        gbc.gridy++;
    }
}
