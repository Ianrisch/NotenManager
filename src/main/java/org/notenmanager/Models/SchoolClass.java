package org.notenmanager.Models;

import jakarta.persistence.Entity;

@Entity
public class SchoolClass {
    public String name;

    public SchoolClass(String name){
        this.name=name;
    }
}
