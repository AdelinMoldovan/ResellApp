package com.example.resell.model;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity
@Table(name="Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double productPrice;
    private String productName;
    private String productDescription;
    private String productProducer;
    private String productCategory;

    public Product(double productPrice,
                   String productName,
                   String productDescription,
                   String productProducer,
                   String productCategory) {
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productProducer = productProducer;
        this.productCategory = productCategory;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductProducer() {
        return productProducer;
    }

    public void setProductProducer(String productProducer) {
        this.productProducer = productProducer;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
