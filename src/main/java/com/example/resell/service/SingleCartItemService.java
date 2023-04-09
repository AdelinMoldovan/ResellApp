package com.example.resell.service;

import com.example.resell.model.ShoppingCart;
import com.example.resell.model.SingleCartItem;
import org.springframework.stereotype.Component;

@Component
public interface SingleCartItemService {

    SingleCartItem addSingleCartItem(SingleCartItem singleCartItem); // throws ProductNotFoundException;

    void deleteById(int id); // throws ProductNotFoundException;

    void deleteAllCartItems(ShoppingCart shoppingCart); // throws ProductNotFoundException;
}
