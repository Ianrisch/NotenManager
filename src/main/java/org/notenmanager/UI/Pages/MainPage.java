package org.notenmanager.UI.Pages;

import org.notenmanager.Models.Grade;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.UI.Compenents.LabledField.LabeledComboBoxField;
import org.notenmanager.UI.Compenents.LabledField.LabeledText;
import org.notenmanager.UI.Popups.MultipleOptionDialog;
import org.notenmanager.Utils.AverageCalculation;
import org.notenmanager.Utils.Constants.Lang.LanguageConstants;
import org.notenmanager.Utils.Constants.Lang.Languages;
import org.notenmanager.Utils.Dataservice.DataService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Consumer;

public class MainPage extends JFrame {
    private final DataService dataService;
    private final User user;
    private final LabeledComboBoxField schoolSubjectComboBox = new LabeledComboBoxField(false);

    private final LabeledText teacher = new LabeledText(false);
    private final LabeledText schoolClass = new LabeledText(false);
    private final LabeledText average = new LabeledText(false);
    private final LabeledText averageWithGravity = new LabeledText(false);
    private final JButton addGrade = new JButton();
    private final JButton removeGrade = new JButton();
    private final JPanel base = new JPanel();
    private final DefaultListModel<String> gradeListModel = new DefaultListModel();
    private final JButton addSubject = new JButton();
    private final JButton removeSubject = new JButton();
    private final JButton backButton = new JButton();
    private List<SchoolSubject> schoolSubjects;
    private LanguageConstants languageConstants;
    private JList gradeList;
    private JScrollPane scrollPane;
    private LabeledComboBoxField languagePicker = new LabeledComboBoxField(false);

    public MainPage(DataService dataService, User user, List<SchoolSubject> schoolSubjects, String language) {
        super();
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 500));
        setPreferredSize(new Dimension(950, 500));

        this.schoolSubjects = schoolSubjects;
        this.dataService = dataService;
        this.user = user;

        createUIComponents();
        languagePicker.comboBox.setSelectedItem(null);
        languagePicker.comboBox.setSelectedItem(language);

        updateTitle();


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

        if (schoolSubjectComboBox.comboBox.getItemCount() != 0) schoolSubjectComboBox.comboBox.setSelectedIndex(0);
        revalidate();
    }

    private void UpdateAverage() {
        RunOnCurrentSchoolSubject(schoolSubject -> {
            DecimalFormat df = new DecimalFormat("#.##");
            average.setText(df.format(AverageCalculation.average(schoolSubject)));
            averageWithGravity.setText(df.format(AverageCalculation.averageWithGravity(schoolSubject)));
        });
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
            if (schoolSubject.name.equals(selectedItem)) {

                setTeacherName(schoolSubject);

                addGradesToGradeList(schoolSubject.grades);

                if (gradeListModel.isEmpty()) setEmptyTextOnGradePanel();

                break;
            }
        }
        UpdateAverage();
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
            gradeListModel.addElement(languageConstants.asString(grade));
            revalidate();
        }
    }

    private void setLabels() {
        teacher.setLabel(languageConstants.Teacher);
        schoolClass.setLabel(languageConstants.SchoolClass);
        average.setLabel(languageConstants.Average);
        averageWithGravity.setLabel(languageConstants.AverageWithGravity);
        languagePicker.setLabel(languageConstants.PickALanguage);
        schoolSubjectComboBox.setLabel(languageConstants.Subject);
        addGrade.setText(languageConstants.AddGrade);
        removeGrade.setText(languageConstants.RemoveGrade);
        addSubject.setText(languageConstants.AddSubject);
        removeSubject.setText(languageConstants.RemoveSubject);
        backButton.setText(languageConstants.Back);
    }

    public void updateTitle() {
        setTitle(languageConstants.NotenManagerTitle(user.username));
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

        base.add(backButton, gbc);
        gbc.gridx = 4;
        Languages.setupLanguagePicker(languagePicker, a -> OnPickLanguage(a));
        base.add(languagePicker, gbc);
        gbc.gridx = 0;
        gbc.gridy++;

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
        gbc.gridx++;
        base.add(average, gbc);
        gbc.gridx++;
        base.add(averageWithGravity, gbc);
    }

    private void AddActionListeners() {
        addGrade.addActionListener(a -> OnAddGrade(a));
        removeGrade.addActionListener(a -> OnRemoveGrade(a));
        addSubject.addActionListener(a -> OnAddSubject(a));
        removeSubject.addActionListener(a -> OnRemoveSubject(a));
        backButton.addActionListener(a -> OnBack(a));
    }

    private void OnPickLanguage(ActionEvent a) {
        languageConstants = Languages.PickLanguage(languagePicker.getSelectedItem());
        setLabels();
        SetGradeList();
    }

    private void OnBack(ActionEvent a) {
        OpenLoginPage();
        this.dispose();
    }

    private void OpenLoginPage() {
        LoginPage loginPage = new LoginPage(dataService, languagePicker.getSelectedItem());
        loginPage.pack();
        loginPage.setVisible(true);
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
                languageConstants.Teacher
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
            if (schoolSubject.name.equals(selectedSchoolSubject)) {
                consumer.accept(schoolSubject);
                break;
            }
        }
    }

    private void setupScrollPane() {
        scrollPane = new JScrollPane(gradeList);
        scrollPane.setPreferredSize(new Dimension(250, 30));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
}
