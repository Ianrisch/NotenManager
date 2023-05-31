package org.notenmanager.Models;

import jakarta.persistence.Entity;

@Entity
public class User {
    public String username;
    public String password;
    public String mail;
    public SchoolClass schoolClass;

    public User(String username,String password, String mail,SchoolClass schoolClass){
        this.username=username;
        this.password=password;
        this.mail=mail;
        this.schoolClass=schoolClass;
    }
}
