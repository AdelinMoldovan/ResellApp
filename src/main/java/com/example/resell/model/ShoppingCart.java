package com.example.resell.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(mappedBy = "shoppingCart")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SingleCartItem> singleProductCart;

    private double totalCartPrice;

    public ShoppingCart() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SingleCartItem> getSingleProductCart() {
        return singleProductCart;
    }

    public void setSingleProductCart(List<SingleCartItem> singleProductCart) {
        this.singleProductCart = singleProductCart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalCartPrice(double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    public double getTotalCartPrice() {
        return totalCartPrice;
    }

    public ShoppingCart(int id, double totalCartPrice) {
        this.id = id;
        this.totalCartPrice = totalCartPrice;
    }
}
