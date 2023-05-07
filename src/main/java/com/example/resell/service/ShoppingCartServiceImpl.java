package com.example.resell.service;


import com.example.resell.exception.ShoppingCartNotFoundException;
import com.example.resell.model.ShoppingCart;
import com.example.resell.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * Method to find shooping cart by id
     * @param shoppingCartId
     * @return shopping cart found
     * @throws ShoppingCartNotFoundException
     */
    @Override
    public ShoppingCart findShoppingCartById(long shoppingCartId) throws ShoppingCartNotFoundException{
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);
        if(!shoppingCart.isPresent()){
            throw new ShoppingCartNotFoundException("Shopping cart with id" + shoppingCartId + "not found");
        }
        return  shoppingCart.get();
    }
}
