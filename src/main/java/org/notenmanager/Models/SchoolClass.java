package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;

    public SchoolClass() {
    }

    public SchoolClass(String name) {
        this.name = name;
    }

    @JsonCreator
    private static SchoolClass jsonCreator(@JsonProperty("name") String name){
        return new SchoolClass(name);
    }
}
