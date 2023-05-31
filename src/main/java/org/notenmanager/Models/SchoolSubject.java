package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class SchoolSubject {

    public String name;

    public Person teacher;

    public List<Grade> grades;

    public SchoolSubject(Person teacher, String name, List<Grade> grades){
        this.teacher=teacher;
        this.name=name;
        this.grades=grades;
    }
    @JsonCreator
    private static SchoolSubject createSchoolSubject(@JsonProperty("teacher") Person teacher,@JsonProperty("name") String name,@JsonProperty("grades") List<Grade> grades){
        return new SchoolSubject(teacher,name,grades);
    }
}
