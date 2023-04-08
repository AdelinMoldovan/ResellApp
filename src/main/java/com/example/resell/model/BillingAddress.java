package com.example.resell.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="billingAddress")
public class BillingAddress implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String address;
    private String city;
    private String zipcode;
    private String country;

    @OneToOne(mappedBy = "billingAddress")
    private Customer customer;


    public BillingAddress(String address,
                          String city,
                          String zipcode,
                          String country) {
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BillingAddress() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
