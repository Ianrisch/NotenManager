package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Person {
    public String firstName;
    public String lastName;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    public List<SchoolSubject> schoolSubjects;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonCreator
    private static Person createPerson(@JsonProperty("firstName") String leftName, @JsonProperty("lastName") String rightName) {
        return new Person(leftName, rightName);
    }

    public String fullName() {
        return firstName + " " + lastName;
    }
}
