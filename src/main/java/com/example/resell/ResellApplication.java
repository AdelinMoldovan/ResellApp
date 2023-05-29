package com.example.resell;


import com.example.resell.model.*;
import com.example.resell.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"com.example.resell"})
@EntityScan("com.example.resell.model")
@EnableJpaRepositories("com.example.resell.repository")
public class ResellApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResellApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerService customerService,
                           OrderService orderService,
                           ProductService productService) {
        return args -> {
                Product product1 = new Product();
                product1.setName("Nike AirForce 1");
                product1.setCategory("Sneakers");
                product1.setDescription("Culoare alb, marimea 41");
                product1.setManufacturer("Nike");
                product1.setPrice(650);
                product1.setStock("5");
                productService.addProduct(product1);

                Product product2 = new Product();
                product2.setName("Nike Jordan 1 University Blue");
                product2.setCategory("Sneakers");
                product2.setDescription("Culoare albastru, marimea 42");
                product2.setManufacturer("Nike");
                product2.setPrice(2000);
                product2.setStock("4");
                productService.addProduct(product2);

                Product product3 = new Product();
                product3.setName("Nike Jordan 1 University Green");
                product3.setCategory("Sneakers");
                product3.setDescription("Culoare albastru, marimea 42");
                product3.setManufacturer("Nike");
                product3.setPrice(2000);
                product3.setStock("4");
                productService.addProduct(product3);

                Customer customer = new Customer();
                customer.setFirstName("Andrei");
                customer.setLastName("Bugnar");
                customer.setEmail("andreibugnar@yahoo.com");
                customer.setPassword("Amdreo@1234");
                customer.setCustomerPhone("0425388997");
                customerService.addCustomer(customer);

                Product updateProduct = new Product();
                updateProduct.setId(product2.getId());
                updateProduct.setName("Nike Jordan 1 Royal");
                productService.updateProduct(updateProduct);

                ShoppingCart cart = new ShoppingCart();
                cart.addItem(product1);

                ShippingAddress addr = new ShippingAddress();
                addr.setAddress("Baritiu 69");
                addr.setCity("Cluj");
                addr.setZipcode("12345");
                addr.setCountry("Ungaria");

                Order order1 = new Order();
                order1.setShoppingCart(cart);
                order1.setCustomer(customer);
                order1.setShippingAddress(addr);
                order1.setTime(LocalDateTime.now());
                orderService.addOrder(order1);

                System.out.println(orderService.generateInvoice(order1));
        };
    }

}