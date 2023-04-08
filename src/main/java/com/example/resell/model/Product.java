package com.example.resell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @Column(name = "category")
    private String productCategory;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "producer")
    private String productManufacturer;

    @Column(name = "price")
    private double productPrice;

    @Column(name = "unit")
    private String unitStock;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "product")
//    private List<Order> orders = new ArrayList<>();

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", productPrice=" + productPrice +
                ", unitStock='" + unitStock + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getUnitStock() {
        return unitStock;
    }

    public void setUnitStock(String unitStock) {
        this.unitStock = unitStock;
    }

}
