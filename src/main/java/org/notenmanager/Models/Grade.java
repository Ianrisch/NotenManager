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
    public float value;
    public double gravity;

    public Grade(float value, double gravity) {
        this.value = value;
        this.gravity = gravity;
    }

    public Grade() {
    }

    @JsonCreator
    private static Grade createGrade(@JsonProperty("value") float value, @JsonProperty("gravity") double gravity) {
        return new Grade(value, gravity);
    }

    /**
     * Only for DB pls ignore!!
     */
    @ManyToOne
    @JoinColumn(name = "subject_id",nullable = false)
    private SchoolSubject subject;
    public void addRelationPartner(SchoolSubject subject) {
        this.subject = subject;
    }
}
