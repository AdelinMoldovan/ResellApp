package com.example.resell.observer;

import com.example.resell.model.Product;

public interface CustomerObserver {
    void update(Product product);
}