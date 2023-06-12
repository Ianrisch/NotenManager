package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public double value;
    public double gravity;

    public Grade(double value, double gravity) {
        this.value = value;
        this.gravity = gravity;
    }

    public Grade() {
    }

    @Override
    public String toString() {
        return "Grade: " + value + " Gravity: " + gravity;
    }

    @JsonCreator
    private static Grade createGrade(@JsonProperty("value") double value, @JsonProperty("gravity") double gravity) {
        return new Grade(value, gravity);
    }

    /**
     * Only for DB pls ignore!!
     */
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private SchoolSubject subject;

    public void addRelationPartner(SchoolSubject subject) {
        this.subject = subject;
    }
}
