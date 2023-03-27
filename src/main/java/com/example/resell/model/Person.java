package com.example.resell.model;

import jakarta.persistence.*;

@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"

    )
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppPersonRole appPersonRole;

    public Person() {

    }

    public Person(Long id,
                  String name,
                  String username,
                  String email,
                  String password,
                  AppPersonRole appPersonRole) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appPersonRole = appPersonRole;
    }

    public Person(String name,
                  String username,
                  String email,
                  String password,
                  AppPersonRole appPersonRole) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appPersonRole = appPersonRole;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AppPersonRole getAppPersonRole() {
        return appPersonRole;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAppUserRole(AppPersonRole appPersonRole) {
        this.appPersonRole = appPersonRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appPersonRole=" + appPersonRole +
                '}';
    }
}