package com.example.resell.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="shoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 3000)
    private List<Product> singleProductCart;

    private double totalCartPrice;

    public void addItem(Product item) {
        if(this.singleProductCart == null) {
            this.singleProductCart = new ArrayList<Product>();
        }

        singleProductCart.add(item);
        totalCartPrice += item.getPrice();
    }

    public void removeItem(Product item) {
        singleProductCart.remove(item);
        totalCartPrice -= item.getPrice();
    }
}
