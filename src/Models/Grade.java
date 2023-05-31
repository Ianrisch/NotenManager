package Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Grade {
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
