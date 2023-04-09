package com.example.resell.service;


import com.example.resell.exception.AdminNotFoundException;
import com.example.resell.exception.CustomerNotFoundException;
import com.example.resell.exception.InvalidCustomerException;
import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.AppPersonRole;
import com.example.resell.model.Customer;
import com.example.resell.model.Person;
import com.example.resell.repository.CustomerRepository;
import com.example.resell.validator.CustomerDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private CustomerDetailsValidator customerDetailsValidator;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDetailsValidator customerDetailsValidator, PersonService personService) {
        this.customerRepository = customerRepository;
        this.customerDetailsValidator = customerDetailsValidator;
        this.personService = personService;
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
            Optional<Customer> foundCustomer = customerRepository.findByEmail(customer.getEmail());
            if (!foundCustomer.isPresent()) {
                Person person = new Person(customer.getId(), customer.getEmail(), customer.getPassword(), AppPersonRole.CUSTOMER);
                personService.addPerson(person);
                customer.setId(customer.getId());
                customerRepository.save(customer);
            } else {
                throw new InvalidCustomerException("Customer with this email already exists");
            }
        } catch (WrongDetailsException exp) {
            throw new InvalidCustomerException(exp.getMessage());
        }
        return customer;
    }


    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException, InvalidCustomerException {
        Optional<Customer> customerToUpdate = customerRepository.findById(customer.getId());
        if (customerToUpdate.isPresent()) {
            Person personToUpdate = new Person(customerToUpdate.get().getId(), customerToUpdate.get().getEmail(),
                    customerToUpdate.get().getPassword(), AppPersonRole.CUSTOMER);
            Person finalPerson = createPerson(customer, personToUpdate);
            Customer finalCustomer = createCustomer(customer, customerToUpdate.get());
            try {
                customerDetailsValidator.validateCustomerDetails(finalCustomer);
            } catch (WrongDetailsException exp) {
                throw new InvalidCustomerException(exp.getMessage());
            }
            customerRepository.save(finalCustomer);
            personService.addPerson(finalPerson);
        } else {
            throw new CustomerNotFoundException("Customer to add not found");
        }
        return customerToUpdate.get();
    }


    @Override
    public Customer updateOrderList(Customer customer) throws CustomerNotFoundException {
        if (customerRepository.findById(customer.getId()).isEmpty()) {
            throw new AdminNotFoundException("Admin with id " + customer.getId() + " not found");
        }
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteById(long id) throws CustomerNotFoundException {
        Optional<Customer> doctorToDelete = customerRepository.findById(id);
        if (doctorToDelete.isPresent()) {
            personService.deleteById(id);
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer to delete not found");
        }
    }

    private Person createPerson(Customer customer, Person personToUpdate) {
        if (customer.getEmail() != null) {
            personToUpdate.setEmail(customer.getEmail());
        }
        if (customer.getPassword() != null) {
            personToUpdate.setPassword(customer.getPassword());
        }
        return personToUpdate;
    }

    private Customer createCustomer(Customer customer, Customer customerToUpdate) {
        if (customer.getFirstName() != null) {
            customerToUpdate.setFirstName(customer.getFirstName());
        }
        if (customer.getLastName() != null) {
            customerToUpdate.setLastName(customer.getLastName());
        }
        if (customer.getEmail() != null) {
            customerToUpdate.setEmail(customer.getEmail());
        }
        if (customer.getPassword() != null) {
            customerToUpdate.setPassword(customer.getPassword());
        }
        if (customer.getCustomerPhone() != null) {
            customerToUpdate.setCustomerPhone(customer.getCustomerPhone());
        }
        if (customer.getBillingAddress() != null) {
            customerToUpdate.setBillingAddress(customer.getBillingAddress());
        }
        if(customer.getShippingAddress() != null){
            customerToUpdate.setShippingAddress(customer.getShippingAddress());
        }
        if(customer.getShoppingCart() != null){
            customerToUpdate.setShoppingCart(customer.getShoppingCart());
        }
        return customerToUpdate;
    }
}
