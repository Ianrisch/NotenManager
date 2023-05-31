package org.notenmanager.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;


@Entity
public class Person {
    public String firstName;
    public String lastName;


    public Person( String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }
    @JsonCreator
    private static Person createPerson(@JsonProperty("firstName") String leftName, @JsonProperty("lastName") String rightName) {
        return new Person(leftName, rightName);
    }

    public String fullName() {
        return firstName+" "+lastName;
    }
}
