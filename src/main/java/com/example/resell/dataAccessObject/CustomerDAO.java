package com.example.resell.dataAccessObject;

import com.example.resell.model.Customer;

public interface CustomerDAO {

        void addNewCustomer(Customer customer);


    Customer getCustomerByUserName(String userName);
}
