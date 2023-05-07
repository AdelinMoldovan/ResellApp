package com.example.resell.service;


import com.example.resell.exception.ProductNotFoundException;
import com.example.resell.model.ShoppingCart;
import com.example.resell.model.SingleCartItem;
import com.example.resell.repository.SingleCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleCartItemServiceImpl implements SingleCartItemService{

    @Autowired
    private SingleCartItemRepository singleCartItemRepository;


    @Override
    public SingleCartItem addSingleCartItem(SingleCartItem singleCartItem) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteById(int id) throws ProductNotFoundException {

    }

    @Override
    public void deleteAllCartItems(ShoppingCart shoppingCart) throws ProductNotFoundException {

    }
}
