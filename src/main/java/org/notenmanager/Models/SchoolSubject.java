package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class SchoolSubject {

    public String name;
    @OneToOne(targetEntity = Person.class, cascade = CascadeType.REMOVE)
    public Person teacher;
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<Grade> grades;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Only for DB pls ignore!!
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public SchoolSubject() {
    }

    public SchoolSubject(Person teacher, String name, List<Grade> grades) {
        this.teacher = teacher;
        this.name = name;
        this.grades = grades;
    }

    @JsonCreator
    private static SchoolSubject createSchoolSubject(@JsonProperty("teacher") Person teacher, @JsonProperty("name") String name, @JsonProperty("grades") List<Grade> grades) {
        return new SchoolSubject(teacher, name, grades);
    }

    public void addRelationPartner(User user) {
        this.user = user;
    }
}
