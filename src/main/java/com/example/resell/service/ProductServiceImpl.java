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

    @Autowired
    private AdminService adminService;
    private List<CustomerObserver> observers = new ArrayList<>();

    public ProductServiceImpl(ProductRepository productRepository, AdminService adminService) {
        this.productRepository = productRepository;
        this.adminService = adminService;
    }

    @Override
    public Product getById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return product.get();
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }


    @Override
    public List<Product> findAllByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        // Add the product to the database
        productRepository.save(product);

        // Notify observers (customers)
        notifyObservers(product);

        return product;
    }

    public void registerObserver(CustomerObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(CustomerObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Product product) {
        for (CustomerObserver observer : observers) {
            observer.update(product);
        }
    }

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
