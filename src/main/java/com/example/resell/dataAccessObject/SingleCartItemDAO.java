package com.example.resell.dataAccessObject;

import com.example.resell.model.ShoppingCart;
import com.example.resell.model.SingleCartItem;

public interface SingleCartItemDAO {

    void addShoppingCartItem(SingleCartItem singleCartItem);

    void removeShoppingCartItem(int singleCartItemId);

    void removeAllShoppingCartItems(ShoppingCart shoppingCart);
}
