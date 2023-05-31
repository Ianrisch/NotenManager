package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    public float Value;
    public double Gravity;

    public Grade(float value, double gravity) {
        Value = value;
        Gravity = gravity;
    }
    @JsonCreator
    private static Grade createGrade(@JsonProperty("value") float value, @JsonProperty("gravity") float gravity){
        return new Grade(value,gravity);
    }
}
