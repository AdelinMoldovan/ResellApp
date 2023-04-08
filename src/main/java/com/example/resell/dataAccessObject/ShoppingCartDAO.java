package com.example.resell.dataAccessObject;

import com.example.resell.model.ShoppingCart;

import java.io.IOException;

public interface ShoppingCartDAO {

    ShoppingCart getCartById(int shoppingCartId);
    ShoppingCart validate(int shoppingCartId) throws IOException;

}
