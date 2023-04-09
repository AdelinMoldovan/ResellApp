package com.example.resell.service;


import com.example.resell.exception.InvalidOrderException;
import com.example.resell.exception.OrderNotFoundException;
import com.example.resell.model.Customer;
import com.example.resell.model.Order;
import com.example.resell.model.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public interface OrderService {

    Order findById(int id) throws OrderNotFoundException;
    Order findByCustomerAndTime(Customer customer, LocalDateTime time) throws OrderNotFoundException;
    List<Order> findAllByProducts(Product product);
    List<Order> findAllByTime(LocalDateTime time);
    List<Order> findAll();

    Order addOrder(Order order) throws InvalidOrderException;
    Order updateOrder(Order order) throws InvalidOrderException, OrderNotFoundException;

    void deleteById(long id) throws OrderNotFoundException;

}
