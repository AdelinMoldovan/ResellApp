package com.example.resell;


import com.example.resell.model.Admin;
import com.example.resell.model.Customer;
import com.example.resell.model.Order;
import com.example.resell.model.Product;
import com.example.resell.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;


@SpringBootApplication
public class ResellApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResellApplication.class, args);
    }




    @Bean
    CommandLineRunner init(PersonService personService,
                           AdminService adminService,
                           CustomerService customerService,
                           OrderService orderService,
                           ProductService productService) {
        return args -> {

            Admin admin = new Admin();
            admin.setEmail("Adelin");
            admin.setLastName("Moldovan");
            admin.setEmail("adelin@gmail.com");
            admin.setPassword("parola@1234");
            admin.setPhoneNumber("0755538466");
            adminService.addAdmin(admin);

            Product product1 = new Product();
            product1.setName("Nike AirForce 1");
            product1.setProductCategory("Sneakers");
            product1.setProductDescription("Culoare alb, marimea 41");
            product1.setProductManufacturer("Nike");
            product1.setProductPrice(650);
            product1.setUnitStock("5");

            Product product2 = new Product();
            product2.setName("Nike Jordan 1 University Blue");
            product2.setProductCategory("Sneakers");
            product2.setProductDescription("Culoare albstru, marimea 42");
            product2.setProductManufacturer("Nike");
            product2.setProductPrice(2000);
            product2.setUnitStock("4");


            Customer customer = new Customer();
            customer.setFirstName("Andrei");
            customer.setLastName("Bugnar");
            customer.setEmail("andreibugnar@yahoo.com");
            customer.setPassword("amdreo@1234");
            customer.setCustomerPhone("0425388997");
            customerService.addCustomer(customer);

            Order order = new Order();
            order.setCustomer(customer);
            order.setProduct(product1);
            orderService.addOrder(order);


            Product updateProduct = new Product();
            updateProduct.setId(product2.getId());
            updateProduct.setName("Nike Jordan 1 Royal");
            productService.updateProduct(updateProduct);

            Admin ownerAdmin = new Admin();
            ownerAdmin.setId(admin.getId());
            ownerAdmin.setEmail("newemail@app.com");
            adminService.updateAdmin(ownerAdmin);


            Customer customerUpdate = new Customer();
            customerUpdate.setId(customer.getId());
            customerUpdate.setEmail("newemail@yahoo.com");
            customerService.updateCustomer(customerUpdate);


        };
    }

}