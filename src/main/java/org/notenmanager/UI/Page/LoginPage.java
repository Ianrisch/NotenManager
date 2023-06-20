package org.notenmanager.UI.Page;

import org.notenmanager.Models.SchoolClass;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.UI.Compenents.LabledField.LabeledComboBoxField;
import org.notenmanager.UI.Compenents.LabledField.LabeledPasswordField;
import org.notenmanager.UI.Compenents.LabledField.LabeledTextField;
import org.notenmanager.Utils.Constants.Lang.LanguageConstants;
import org.notenmanager.Utils.Constants.Lang.Languages;
import org.notenmanager.Utils.Dataservice.DataService;
import org.notenmanager.Utils.Dataservice.DatabaseService;
import org.notenmanager.Utils.Dataservice.JsonService;
import org.notenmanager.Utils.PasswordService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class LoginPage extends JFrame {
    private final JPanel base = new JPanel();
    private final JComboBox ComboBox = new JComboBox();
    private final JPanel content = new JPanel();
    private final LabeledTextField username = new LabeledTextField();
    private final LabeledPasswordField password = new LabeledPasswordField();
    private final LabeledPasswordField repeatedPassword = new LabeledPasswordField();
    private final LabeledTextField eMail = new LabeledTextField();
    private final LabeledTextField schoolClass = new LabeledTextField();
    private final JButton submitButton = new JButton();
    private LanguageConstants languageConstants;
    private LabeledComboBoxField languagePicker = new LabeledComboBoxField(false);
    private LabeledComboBoxField persistencePicker = new LabeledComboBoxField(false);
    private boolean isRegistration = false;

    private DataService dataService;

    public LoginPage(DataService dataService, String language) {
        this();
        languagePicker.comboBox.setSelectedItem(null);
        languagePicker.comboBox.setSelectedItem(language);

        persistencePicker.comboBox.setSelectedItem(null);

        if (dataService instanceof DatabaseService) {
            persistencePicker.comboBox.setSelectedItem(languageConstants.Online);
        } else {
            persistencePicker.comboBox.setSelectedItem(languageConstants.Local);
        }
    }

    public LoginPage() {
        super();
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));
        setSize(new Dimension(400, 600));
        setResizable(false);

        createUIComponents();

        setContentPane(base);

    }

    public boolean isLogin() {
        return !isRegistration();
    }

    public boolean isRegistration() {
        return isRegistration;
    }

    private void createUIComponents() {


        base.setLayout(new GridBagLayout());
        base.setBorder(new EmptyBorder(25, 25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        Languages.setupLanguagePicker(languagePicker, a -> OnPickLanguage(a));
        OnPickLanguage(null);
        base.add(languagePicker, gbc);
        gbc.gridy++;

        setupPersistencePicker();
        base.add(persistencePicker, gbc);

        gbc.gridy++;
        setupComboBox();
        base.add(ComboBox, gbc);

        gbc.gridy++;

        setupContent();
        base.add(content, gbc);
    }

    private void setupPersistencePicker() {
        persistencePicker.comboBox.addActionListener(a -> {
            setDataService();
        });
        setDataService();
    }

    private void setDataService() {
        if (Objects.equals(persistencePicker.getSelectedItem(), languageConstants.Online)) {
            dataService = new DatabaseService();
        } else {
            dataService = new JsonService();
        }
    }

    private void OnPickLanguage(ActionEvent a) {
        languageConstants = Languages.PickLanguage(languagePicker.getSelectedItem());
        setLabels();
    }

    private void setLabels() {
        username.setLabel(languageConstants.Username);
        password.setLabel(languageConstants.Password);
        repeatedPassword.setLabel(languageConstants.RepeatedPassword);
        eMail.setLabel(languageConstants.EMail);
        schoolClass.setLabel(languageConstants.SchoolClass);
        submitButton.setLabel(languageConstants.Submit);
        languagePicker.setLabel(languageConstants.PickALanguage);
        ComboBox.removeAllItems();
        ComboBox.addItem(languageConstants.Login);
        ComboBox.addItem(languageConstants.Registration);
        persistencePicker.setLabel(languageConstants.Persistence);
        persistencePicker.comboBox.removeAllItems();
        persistencePicker.comboBox.addItem(languageConstants.Local);
        persistencePicker.comboBox.addItem(languageConstants.Online);
    }

    private void setupComboBox() {
        ComboBox.setPreferredSize(new Dimension(150, 30));
        ComboBox.addActionListener(e -> {
            String selectedItem = (String) ComboBox.getSelectedItem();

            if (selectedItem == null) return;
            if (selectedItem.equals(languageConstants.Login)) {
                onSetLogin();
            } else if (selectedItem.equals(languageConstants.Registration)) {
                onSetRegistration();
            }
        });

        setRegistration();
        SetLogin();
    }

    public void onSetLogin() {
        isRegistration = false;
        setTitle(languageConstants.Login);
        password.clearTextField();
        repeatedPassword.clearTextField();
        eMail.clearTextField();
        schoolClass.clearTextField();
        setVisibilitiesOfRegistrationComponents(false);
    }

    public void onSetRegistration() {
        isRegistration = true;
        setTitle(languageConstants.Registration);
        setVisibilitiesOfRegistrationComponents(true);
    }

    private void setVisibilitiesOfRegistrationComponents(boolean visibility) {
        repeatedPassword.setVisible(visibility);
        eMail.setVisible(visibility);
        schoolClass.setVisible(visibility);
    }

    private void setupContent() {
        content.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.ipady = 20;

        content.add(new JSeparator(), gbc);

        gbc.gridy++;
        gbc.ipady = 10;
        content.add(username, gbc);
        gbc.gridy++;
        content.add(password, gbc);
        gbc.gridy++;
        content.add(repeatedPassword, gbc);
        gbc.gridy++;
        content.add(eMail, gbc);
        gbc.gridy++;
        content.add(schoolClass, gbc);
        gbc.gridy++;
        content.add(new JSeparator(), gbc);

        gbc.gridy++;

        setupSubmitButton();

        content.add(submitButton, gbc);
    }

    private void setupSubmitButton() {
        submitButton.addActionListener(a -> {
            String username = this.username.getText();
            String password = this.password.getText();
            String repeatedPassword = this.repeatedPassword.getText();
            String hashedPassword = PasswordService.CreatePasswordFromUserData(username, password);
            if (isLogin()) {
                if (!dataService.AuthenticateUser(username, hashedPassword)) {
                    JOptionPane.showMessageDialog(this, "The Username or Password is Incorrect!", "Innate Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                User user = dataService.GetUser(username);
                List<SchoolSubject> schoolSubjects = dataService.GetSchoolSubjectsFromUser(user);

                OpenMainPage(user, schoolSubjects);

                dispose();

            } else if (isRegistration()) {
                if (isInvalidCredentials(username, password, repeatedPassword)) return;
                dataService.CreateUser(new User(username, password, eMail.getText(), new SchoolClass(schoolClass.getText())));
                SetLogin();
            }
        });
    }

    private boolean isInvalidCredentials(String username, String password, String repeatedPassword) {
        if (dataService.UserExist(username)) {
            JOptionPane.showMessageDialog(this, languageConstants.UsernameAlreadyExists(username), languageConstants.InnateWarning, JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (dataService.EmailExist(eMail.getText())) {
            JOptionPane.showMessageDialog(this, languageConstants.EmailAlreadyExists(eMail.getText()), languageConstants.InnateWarning, JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (!Objects.equals(password, repeatedPassword)) {
            JOptionPane.showMessageDialog(this, languageConstants.PasswordMismatch, languageConstants.InnateWarning, JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private void OpenMainPage(User user, List<SchoolSubject> schoolSubjects) {
        MainPage mainPage = new MainPage(dataService, user, schoolSubjects, languagePicker.getSelectedItem());
        mainPage.pack();
        mainPage.setVisible(true);
    }

    private void SetLogin() {
        ComboBox.setSelectedIndex(0);
    }

    private void setRegistration() {
        ComboBox.setSelectedIndex(1);
    }

}
