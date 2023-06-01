package org.notenmanager.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    public String username;
    public String password;
    public String mail;
    @OneToOne(targetEntity = SchoolClass.class, cascade = CascadeType.REMOVE)
    public SchoolClass schoolClass;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<SchoolSubject> schoolSubjects;


    public User() {
    }

    public User(String username, String password, String mail, SchoolClass schoolClass) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.schoolClass = schoolClass;
        this.schoolSubjects = new ArrayList<>();
    }
}
