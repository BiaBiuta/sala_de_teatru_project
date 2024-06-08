package com.example.iss.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

//@MappedSuperclass
public class Person extends GenericEntity<Integer> {
    private String name;
    private String username;
    private String password;

    public Person() {
    }

//    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//    @Column(name = "password")
    public String getPassword() {
        return password;
    }
//    @Column(name = "cnp")
    public void setPassword(String password) {
        this.password = password;
    }
    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
