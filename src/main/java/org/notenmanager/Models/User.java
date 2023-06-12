package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.notenmanager.Utils.PasswordService;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Column(unique = true)
    public String username;
    public String password;
    public String mail;
    @OneToOne(targetEntity = SchoolClass.class, cascade = CascadeType.REMOVE)
    public SchoolClass schoolClass;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<SchoolSubject> schoolSubjects;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public User() {
    }

    public User(String username, String password, String mail, SchoolClass schoolClass) {
        this.username = username;
        this.password = PasswordService.CreatePasswordFromUserData(username, password);
        this.mail = mail;
        this.schoolClass = schoolClass;
        this.schoolSubjects = new ArrayList<>();
    }

    private User(String username, String password, String mail, SchoolClass schoolClass, boolean i) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.schoolClass = schoolClass;
        this.schoolSubjects = new ArrayList<>();
    }

    @JsonCreator
    private static User jsonCreator(@JsonProperty("username") String username, @JsonProperty("password") String password, @JsonProperty("mail") String mail, @JsonProperty("schoolClass") SchoolClass schoolClass) {
        return new User(username, password, mail, schoolClass, false);
    }
}
