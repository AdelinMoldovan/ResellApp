package com.example.resell.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="singleProductCart")
public class SingleProductCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int quantity;
    private double price;


    public SingleProductCart(int quantity,
                             double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public SingleProductCart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
