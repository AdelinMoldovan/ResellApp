package com.example.resell.service;

import com.example.resell.model.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(int shoppingCartId);
}
