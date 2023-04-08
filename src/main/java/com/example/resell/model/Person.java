package com.example.resell.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppPersonRole appPersonRole;


    @OneToOne(mappedBy = "person")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppPersonRole getAppPersonRole() {
        return appPersonRole;
    }

    public void setAppPersonRole(AppPersonRole appPersonRole) {
        this.appPersonRole = appPersonRole;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appPersonRole=" + appPersonRole +
                ", customer=" + customer +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}