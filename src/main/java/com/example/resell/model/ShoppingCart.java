package com.example.resell.model;


import jakarta.persistence.*;

@Entity
@Table(name="shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double totalCartPrice;

    public ShoppingCart() {

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
