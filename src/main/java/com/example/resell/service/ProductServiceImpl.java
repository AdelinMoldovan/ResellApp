package com.example.resell.service;

import com.example.resell.exception.ProductNotFoundException;
import com.example.resell.model.Product;
import com.example.resell.observer.CustomerObserver;
import com.example.resell.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private List<CustomerObserver> observers = new ArrayList<>();

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Method to find a product by id
     * @param id
     * @return product found
     * @throws ProductNotFoundException
     */
    @Override
    public Product findById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product.get();
    }

    /**
     * Method to find all products by name
     * @param name
     * @return list of products
     */
    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }


    /**
     * Method to find all products by category
     * @param category
     * @return list of products
     */
    @Override
    public List<Product> findAllByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    /**
     * Method to find all products from DB
     * @return lust of products
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Method to add and save a product in the DB
     * @param product
     * @return saved product
     */
    @Override
    public Product addProduct(Product product) {
        // Add the product to the database
        productRepository.save(product);

        // Notify observers (customers)
        notifyObservers(product);

        return product;
    }

    /**
     * Method to rigister an observer
     * @param observer
     */
    public void registerObserver(CustomerObserver observer) {
        observers.add(observer);
    }
    /**
     * Method to unrigister an observer
     * @param observer
     */
    public void unregisterObserver(CustomerObserver observer) {
        observers.remove(observer);
    }

    /**
     * Method to notify observers, created for Observable Design Pattern
     * @param product
     */
    public void notifyObservers(Product product) {
        for (CustomerObserver observer : observers) {
            observer.update(product);
        }
    }

    /**
     * Method to update an existing product from DB
     * @param product
     * @return updated product
     * @throws ProductNotFoundException
     */
    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException{
        Optional<Product> productToUpdate = productRepository.findById(product.getId());
        if (productToUpdate.isPresent()) {
            if (product.getName() != null) {
                productToUpdate.get().setName(product.getName());
            }
            if (product.getPrice() != 0) {
                productToUpdate.get().setPrice(product.getPrice());
            }
            productRepository.save(productToUpdate.get());
        } else {
            throw new ProductNotFoundException("Product to update not found");
        }
        return productToUpdate.get();
    }

    /**
     * Method to delete a product from DB
     * @param id
     */
    @Override
    public void deleteById(long id) {
        Optional<Product> productToDelete = productRepository.findById(id);
        if (productToDelete.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product to delete not found");
        }
    }
}
