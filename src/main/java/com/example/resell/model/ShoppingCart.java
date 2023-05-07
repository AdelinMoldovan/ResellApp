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

    @OneToOne(mappedBy = "shoppingCart")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SingleCartItem> singleProductCart;

    private double totalCartPrice;

    public void addItem(SingleCartItem item) {
        if(this.singleProductCart == null) {
            this.singleProductCart = new ArrayList<SingleCartItem>();
        }

        singleProductCart.add(item);
        item.setShoppingCart(this);
        totalCartPrice += item.getPrice();
    }

    public void removeItem(SingleCartItem item) {
        singleProductCart.remove(item);
        totalCartPrice -= item.getPrice();
    }
}
