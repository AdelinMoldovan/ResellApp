package com.example.resell.service;


import com.example.resell.exception.CustomerNotFoundException;
import com.example.resell.exception.InvalidCustomerException;
import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.Customer;
import com.example.resell.repository.CustomerRepository;
import com.example.resell.validator.CustomerDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDetailsValidator customerDetailsValidator;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDetailsValidator customerDetailsValidator) {
        this.customerRepository = customerRepository;
        this.customerDetailsValidator = customerDetailsValidator;
    }

    /**
     * Method to find a customer by id
     * @param id
     * @return customer found
     * @throws CustomerNotFoundException
     */
    @Override
    public Customer findById(long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id" + id + " not found");

        }
        return customer.get();
    }

    /**
     * Method to find a customer by email
     * @param email
     * @return customer found
     * @throws CustomerNotFoundException
     */
    @Override
    public Customer findByEmail(String email) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with email" + email + " not found");

        }
        return customer.get();
    }

    /**
     * Method to find a customer by first name and last name
     * @param firstName
     * @param lastName
     * @return customer found
     * @throws CustomerNotFoundException
     */
    @Override
    public Customer findByFirstNameAndLastName(String firstName, String lastName) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with first name" + firstName + "and last name" + lastName + " not found");

        }
        return customer.get();
    }

    /**
     * Method to find all customers from DB
     * @return a list containg all customers from DB
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Method to add and save a customer in the DB
     * @param customer
     * @return saved customer
     * @throws InvalidCustomerException
     */
    @Override
    public Customer addCustomer(Customer customer) throws InvalidCustomerException {
        try {
            customerDetailsValidator.validateCustomerDetails(customer);
        } catch (WrongDetailsException exp) {
            throw new InvalidCustomerException(exp.getMessage());
        }
        return customerRepository.save(customer);
    }

    /**
     * Method to update an existing customer from DB
     * @param customer
     * @return updated customer
     * @throws CustomerNotFoundException
     * @throws InvalidCustomerException
     */
    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerException {
        Optional<Customer> customerToUpdate = customerRepository.findById(customer.getId());
        if (customerToUpdate.isPresent()) {
            Customer finalCustomer = customerToUpdate.get();

            finalCustomer.setFirstName(customer.getFirstName());
            finalCustomer.setLastName(customer.getLastName());
            finalCustomer.setEmail(customer.getEmail());
            finalCustomer.setPassword(customer.getPassword());
            finalCustomer.setCustomerPhone(customer.getCustomerPhone());
            try {
                customerDetailsValidator.validateCustomerDetails(finalCustomer);
            } catch (WrongDetailsException exp) {
                throw new InvalidCustomerException(exp.getMessage());
            }
            customerRepository.save(finalCustomer);
        } else {
            throw new CustomerNotFoundException("Customer to update not found");
        }
        return customerToUpdate.get();
    }

    /**
     * Method to delete a customer from DB
     * @param id
     * @throws CustomerNotFoundException
     */
    @Override
    public void deleteById(long id) throws CustomerNotFoundException {
        Optional<Customer> doctorToDelete = customerRepository.findById(id);
        if (doctorToDelete.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer to delete not found");
        }
    }
}
