package com.example.resell.controller;

import com.example.resell.model.Customer;
import com.example.resell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/findById")
    public ResponseEntity findCustomerById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findById(id));
    }

    @GetMapping("/findByEmail")
    public ResponseEntity findCustomerByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByEmail(email));
    }


    @GetMapping("/findByFirstNameAndLastName")
    public ResponseEntity findCustomerByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByFirstNameAndLastName(firstName, lastName));
    }


    @GetMapping()
    public ResponseEntity findAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.addCustomer(customer));
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customer));
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@RequestParam long id) {
        customerService.deleteById(id);
    }
}
