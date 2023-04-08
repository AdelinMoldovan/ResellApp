package com.example.resell.dataAccessObject;

import com.example.resell.model.Product;

import java.util.List;

public interface ProductDAO {

    Product getProductById(int productId);
    void deleteProduct(int productId);
    void addProduct(Product product);
    void updateProduct(Product product);
    List<Product> getAllProducts();


}
