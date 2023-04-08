package com.example.resell.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PersonService personService;

//    @Autowired
//    private CustomerDetailsValidator customerDetailsValidator;
}
