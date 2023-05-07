package com.example.resell.service;


import com.example.resell.exception.AdminNotFoundException;
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

    @Override
    public Customer findById(long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with id" + id + " not found");

        }
        return customer.get();
    }

    @Override
    public Customer findByEmail(String email) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with email" + email + " not found");

        }
        return customer.get();
    }

    @Override
    public Customer findByFirstNameAndLastName(String firstName, String lastName) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Customer with first name" + firstName + "and last name" + lastName + " not found");

        }
        return customer.get();
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer) throws InvalidCustomerException {
        try {
            customerDetailsValidator.validateCustomerDetails(customer);
        } catch (WrongDetailsException exp) {
            throw new InvalidCustomerException(exp.getMessage());
        }
        return customerRepository.save(customer);
    }


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


    @Override
    public Customer updateOrderList(Customer customer) throws CustomerNotFoundException {
        if (customerRepository.findById(customer.getId()).isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + customer.getId() + " not found");
        }
        customerRepository.save(customer);
        return customer;
    }

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
