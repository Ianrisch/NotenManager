package org.notenmanager.UI.Page;

import org.notenmanager.Models.SchoolSubject;
import org.notenmanager.Models.User;
import org.notenmanager.Utils.Dataservice.DataService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPage extends JFrame {
    private final DataService dataService;
    private User user;
    private List<SchoolSubject> schoolSubjects;
    private final JPanel base = new JPanel();

    public MainPage(DataService dataService, User user, List<SchoolSubject> schoolSubjects) {
        super("NotenManager of User: " + user.username);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 600));
        setSize(new Dimension(400, 600));

        this.dataService = dataService;
        this.user = user;
        this.schoolSubjects = schoolSubjects;

        createUIComponents();

        setContentPane(base);

    }

    public void updateTitle() {
        setTitle(getNewTitle());
    }

    private String getNewTitle() {
        return "NotenManager of User: " + user.username;
    }

    private void createUIComponents() {
    }
}
