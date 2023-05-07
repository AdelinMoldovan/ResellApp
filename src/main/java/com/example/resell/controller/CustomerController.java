package com.example.resell.controller;

import com.example.resell.model.Customer;
import com.example.resell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Returns a customer by id.
     * @param id
     * @return DataResponse(status, message, customer).
     */
    @GetMapping("/customer/id")
    public ResponseEntity findCustomerById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    /**
     * Returns a customer by email.
     * @param email
     * @return DataResponse(status, message, customer).
     */
    @GetMapping("/customer/email")
    public ResponseEntity findCustomerByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByEmail(email));
    }

    /**
     * Returns a customer by first name and last name.
     * @param firstName
     * @param lastName
     * @return DataResponse(status, message, customer).
     */
    @GetMapping("/customer/firstNameAndLastName")
    public ResponseEntity findCustomerByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByFirstNameAndLastName(firstName, lastName));
    }

    /**
     * Returns a list of all customers from DB.
     * @return DataResponse(status, message, list of customers).
     */
    @GetMapping("/customer/all")
    public ResponseEntity findAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    /**
     * Creates a customer and saves it in the DB.
     * @param customer
     * @return DataResponse (status, message).
     */
    @PostMapping("/customer/add")
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.addCustomer(customer));
    }

    /**
     * Updates an existing customer from DB.
     * @param customer
     * @return DataResponse (status, message).
     */
    @PutMapping("/customer/update")
    public ResponseEntity updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customer));
    }

    /**
     *  Deletes a customer from DB.
     * @param id
     * @return DataResponse (status, message).
     */
    @DeleteMapping("/customer/delete")
    public void deleteCustomer(@RequestParam long id) {
        customerService.deleteById(id);
    }
}
