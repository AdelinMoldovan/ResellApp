package com.example.resell.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private ShoppingCart shoppingCart;

    @OneToOne
    private Customer customer;

    @OneToOne
    private ShippingAddress shippingAddress;

    @OneToOne
    private BillingAddress billingAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Product product;
    private LocalDateTime time;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    @Transactional
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shoppingCart=" + shoppingCart +
                ", customer=" + customer +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", product=" + product +
                ", time=" + time +
                '}';
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }
}
