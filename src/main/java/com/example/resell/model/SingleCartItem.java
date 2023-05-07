package com.example.resell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="single_cart_item")
public class SingleCartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @OneToOne
    private Product product;

    private double price;

    @ManyToOne
    @JsonIgnore
    private ShoppingCart shoppingCart;

    @PostLoad
    public void calculatePrice() {
        if(this.product != null) {
            this.price = this.product.getPrice() * this.quantity;
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.calculatePrice();
    }
}
