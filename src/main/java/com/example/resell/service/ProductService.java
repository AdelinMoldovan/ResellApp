package com.example.resell.service;

import com.example.resell.model.Admin;
import com.example.resell.model.Product;
import com.example.resell.observer.CustomerObserver;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ProductService {

    Product findById(long id); //throws ProductNotFoundException;

    List<Product> findAllByName(String name); //throws ProductNotFoundException;
    List<Product> findAllByCategory(String category); //throws ProductNotFoundException;
    List<Product> findAll();

    Product addProduct(Product product); //throws InvalidProductException;
    Product updateProduct(Product product); //throws InvalidProductException, ProductNotFoundException;

    void deleteById(long id); //throws ProductNotFoundException;

    void registerObserver(CustomerObserver observer);
    void unregisterObserver(CustomerObserver observer);
    void notifyObservers(Product product);
}
