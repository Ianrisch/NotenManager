package org.notenmanager.UI.Page;

import org.notenmanager.Models.SchoolClass;
import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.UI.Compenents.LabledField.LabeledPasswordField;
import org.notenmanager.UI.Compenents.LabledField.LabeledTextField;
import org.notenmanager.Utils.Dataservice.DataService;
import org.notenmanager.Utils.Dataservice.JsonService;
import org.notenmanager.Utils.PasswordService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class LoginPage extends JFrame {
    private final JPanel base = new JPanel();
    private final JComboBox ComboBox = new JComboBox();
    private final JPanel content = new JPanel();

    private final LabeledTextField username = new LabeledTextField("Username");
    private final LabeledPasswordField password = new LabeledPasswordField("Password");
    private final LabeledPasswordField repeatedPassword = new LabeledPasswordField("Repeat Password");
    private final LabeledTextField eMail = new LabeledTextField("EMail");
    private final LabeledTextField schoolClass = new LabeledTextField("Class");
    private final JButton submitButton = new JButton("Submit");
    private boolean isRegistration = false;

    private DataService dataService;

    public boolean isLogin() {
        return !isRegistration();
    }

    public boolean isRegistration() {
        return isRegistration;
    }

    public static void main(String[] args) {
        JFrame frame = new LoginPage("loginRegistry");
        frame.pack();
        frame.setVisible(true);
    }

    public LoginPage(String title) {
        super(title);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));
        setSize(new Dimension(400, 600));
        setResizable(false);

        dataService = new JsonService();

        createUIComponents();

        setContentPane(base);

    }

    private void createUIComponents() {
        base.setLayout(new GridBagLayout());
        base.setBorder(new EmptyBorder(25, 25, 25, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        setupComboBox();
        base.add(ComboBox, gbc);

        gbc.gridy++;

        setupContent();
        base.add(content, gbc);
    }

    private void setupComboBox() {
        ComboBox.setPreferredSize(new Dimension(150, 30));
        ComboBox.addActionListener(e -> {
            String selectedItem = (String) ComboBox.getSelectedItem();

            switch (selectedItem) {
                case "Login":
                    onSetLogin();
                    break;
                case "Registration":
                    onSetRegistration();
                    break;
                default:
                    break;
            }
        });

        ComboBox.addItem("Login");
        ComboBox.addItem("Registration");

        setRegistration();
        SetLogin();
    }

    public void onSetLogin() {
        isRegistration = false;
        password.clearTextField();
        repeatedPassword.clearTextField();
        eMail.clearTextField();
        schoolClass.clearTextField();
        setVisibilitiesOfRegistrationComponents(false);
    }

    public void onSetRegistration() {
        isRegistration = true;
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
            JOptionPane.showMessageDialog(this, "The Username \"" + username + "\" already Exists!", "Innate Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (dataService.EmailExist(eMail.getText())) {
            JOptionPane.showMessageDialog(this, "The EMail \"" + eMail.getText() + "\" already Exists!", "Innate Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        if (!Objects.equals(password, repeatedPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords are not matching!", "Innate Warning", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private void OpenMainPage(User user, List<SchoolSubject> schoolSubjects) {
        MainPage mainPage = new MainPage(dataService, user, schoolSubjects);
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
