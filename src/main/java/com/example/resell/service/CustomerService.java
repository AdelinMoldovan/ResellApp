package com.example.resell.service;

import com.example.resell.exception.CustomerNotFoundException;
import com.example.resell.exception.InvalidCustomerException;
import com.example.resell.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    Customer findById(long id) throws CustomerNotFoundException;

    Customer findByEmail(String email) throws CustomerNotFoundException;

    Customer findByFirstNameAndLastName(String firstName, String lastName) throws CustomerNotFoundException;

    List<Customer> findAll();

    Customer addCustomer(Customer customer) throws  InvalidCustomerException;

    Customer updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerException;

    void deleteById(long id) throws CustomerNotFoundException;
}
