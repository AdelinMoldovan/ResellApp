package com.example.resell.service;

import com.example.resell.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer findById(int id); //throws CustomerNotFoundException;

    Customer findByEmail(String email); //throws CustomerNotFoundException;

    Customer findByFirstNameAndLastName(String firstName, String lastName); //throws CustomerNotFoundException

    List<Customer> findAll();

    Customer addCustomer(Customer customer);

    Customer getCustomerByUsername(String username); //throws CustomerNotFoundException;

    Customer updateCustomer(Customer customer); //throws CustomerNotFoundException, InvalidCustomerException;
    void deleteById(int id); //throws CustomerNotFoundException;
}
