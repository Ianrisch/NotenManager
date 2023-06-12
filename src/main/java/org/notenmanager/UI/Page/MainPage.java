package org.notenmanager.UI.Page;

import org.notenmanager.Models.Grade;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.UI.Compenents.LabledField.LabeledComboBoxField;
import org.notenmanager.UI.Compenents.LabledField.LabeledText;
import org.notenmanager.UI.Popups.MultipleOptionDialog;
import org.notenmanager.Utils.Constants.Lang.LanguageConstants;
import org.notenmanager.Utils.Dataservice.DataService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Consumer;

public class MainPage extends JFrame {
    private final DataService dataService;
    private final User user;
    private final LabeledComboBoxField schoolSubjectComboBox = new LabeledComboBoxField(false);

    private final LabeledText teacher = new LabeledText(false);
    private final LabeledText schoolClass = new LabeledText(false);
    private final JButton addGrade = new JButton();
    private final JButton removeGrade = new JButton();
    private JButton addSubject = new JButton();
    private JButton removeSubject = new JButton();
    private List<SchoolSubject> schoolSubjects;
    private final JPanel base = new JPanel();
    private LanguageConstants languageConstants;
    private JList gradeList;
    private final DefaultListModel<String> gradeListModel = new DefaultListModel();
    private JScrollPane scrollPane;

    public MainPage(DataService dataService, User user, List<SchoolSubject> schoolSubjects, LanguageConstants languageConstants) {
        super();
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1350, 500));
        setPreferredSize(new Dimension(1350, 500));

        this.dataService = dataService;
        this.user = user;
        updateTitle();
        this.schoolSubjects = schoolSubjects;
        this.languageConstants = languageConstants;

        createUIComponents();

        setContentPane(base);

    }

    private static GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        return gbc;
    }

    private void setupComboBox() {
        refillComboBox();

        schoolSubjectComboBox.comboBox.addActionListener(e -> {
            schoolClass.setText(user.schoolClass.name);
            SetGradeList();
        });

        if ( schoolSubjectComboBox.comboBox.getItemCount() != 0) schoolSubjectComboBox.comboBox.setSelectedIndex(0);
        revalidate();
    }

    private void refillComboBox() {
        schoolSubjectComboBox.comboBox.removeAllItems();

        for (SchoolSubject sj : schoolSubjects) {
            schoolSubjectComboBox.comboBox.addItem(sj.name);
        }
    }

    private void SetGradeList() {
        String selectedItem = schoolSubjectComboBox.getSelectedItem();

        gradeListModel.removeAllElements();

        for (SchoolSubject schoolSubject : schoolSubjects) {
            if (schoolSubject.name == selectedItem) {

                setTeacherName(schoolSubject);

                addGradesToGradeList(schoolSubject.grades);

                if (gradeListModel.isEmpty()) setEmptyTextOnGradePanel();

                break;
            }
        }
    }

    private void setTeacherName(SchoolSubject schoolSubject) {
        teacher.setText(schoolSubject.teacher.fullName());
    }

    private void setEmptyTextOnGradePanel() {
        gradeListModel.addElement(languageConstants.Empty);
        revalidate();
    }

    private void addGradesToGradeList(List<Grade> grades) {
        int length = grades.size();

        for (int i = 0; i < length; i++) {
            Grade grade = grades.get(i);
            gradeListModel.addElement(grade.toString());
            revalidate();
        }
    }

    private void setLabels() {
        teacher.setLabel(languageConstants.Teacher);
        schoolClass.setLabel(languageConstants.SchoolClass);
        schoolSubjectComboBox.setLabel(languageConstants.Subject);
        addGrade.setText(languageConstants.AddGrade);
        removeGrade.setText(languageConstants.RemoveGrade);
        addSubject.setText(languageConstants.AddSubject);
        removeSubject.setText(languageConstants.RemoveSubject);
    }

    public void updateTitle() {
        setTitle(getNewTitle());
    }

    private String getNewTitle() {
        return "NotenManager of User: " + user.username;
    }

    private void createUIComponents() {
        base.setLayout(new GridBagLayout());
        base.setBorder(new EmptyBorder(25, 25, 25, 25));

        gradeList = new JList(gradeListModel);
        AddActionListeners();

        GridBagConstraints gbc = createGridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        setLabels();

        base.add(teacher, gbc);
        gbc.gridx++;
        base.add(schoolClass, gbc);

        gbc.gridx++;
        setupComboBox();
        base.add(schoolSubjectComboBox, gbc);

        gbc.gridx++;
        base.add(addSubject, gbc);

        gbc.gridx++;
        base.add(removeSubject, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        setupScrollPane();
        base.add(scrollPane, gbc);

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.weighty = 0;
        gbc.gridy++;
        base.add(addGrade, gbc);

        gbc.gridx++;
        base.add(removeGrade, gbc);
    }

    private void AddActionListeners() {
        addGrade.addActionListener(a -> OnAddGrade(a));
        removeGrade.addActionListener(a -> OnRemoveGrade(a));
        addSubject.addActionListener(a -> OnAddSubject(a));
        removeSubject.addActionListener(a -> OnRemoveSubject(a));
    }

    private void OnRemoveSubject(ActionEvent a) {
        String selectedSchoolSubject = schoolSubjectComboBox.getSelectedItem();

        dataService.DeleteSchoolSubjectForUser(user, selectedSchoolSubject);

        schoolSubjects = dataService.GetSchoolSubjectsFromUser(user);

        refillComboBox();
    }

    private void OnAddSubject(ActionEvent a) {
        SchoolSubject schoolSubject = MultipleOptionDialog.showClassInputMessageDialog(
                this,
                languageConstants.AddSubject,
                languageConstants.SubjectName,
                languageConstants.Teacher + ": "
        );

        if (schoolSubject == null) return;

        dataService.CreateSchoolSubjectForUser(user, schoolSubject);

        schoolSubjects = dataService.GetSchoolSubjectsFromUser(user);

        refillComboBox();
    }

    private void OnAddGrade(ActionEvent a) {
        Grade grade = MultipleOptionDialog.showGradeInputMessageDialog(
                this,
                languageConstants.AddGrade,
                languageConstants.Grade,
                languageConstants.Gravity);
        if (grade == null) return;

        RunOnCurrentSchoolSubject(schoolSubject -> {
            schoolSubject.grades.add(grade);

            dataService.UpdateSchoolSubjectForUser(user, schoolSubject.name, schoolSubject);
        });

        SetGradeList();
    }

    private void OnRemoveGrade(ActionEvent a) {
        int[] selectedIndices = gradeList.getSelectedIndices();

        RunOnCurrentSchoolSubject(schoolSubject -> {
            for (int i = 0; i < selectedIndices.length; i++) {
                schoolSubject.grades.remove(i);
            }

            dataService.UpdateSchoolSubjectForUser(user, schoolSubject.name, schoolSubject);
        });

        SetGradeList();
    }

    private void RunOnCurrentSchoolSubject(Consumer<SchoolSubject> consumer) {
        String selectedSchoolSubject = schoolSubjectComboBox.getSelectedItem();

        for (SchoolSubject schoolSubject : schoolSubjects) {
            if (schoolSubject.name == selectedSchoolSubject)
                consumer.accept(schoolSubject);
            break;
        }
    }

    private void setupScrollPane() {
        scrollPane = new JScrollPane(gradeList);
        scrollPane.setPreferredSize(new Dimension(250, 30));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
}
