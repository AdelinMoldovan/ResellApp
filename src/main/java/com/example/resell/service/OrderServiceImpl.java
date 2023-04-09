package com.example.resell.service;

import com.example.resell.exception.CustomerNotFoundException;
import com.example.resell.exception.InvalidOrderException;
import com.example.resell.exception.OrderNotFoundException;
import com.example.resell.exception.ProductNotFoundException;
import com.example.resell.model.Customer;
import com.example.resell.model.Order;
import com.example.resell.model.Product;
import com.example.resell.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public Order findById(int id) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
        return order.get();
    }

    @Override
    public Order findByCustomerAndTime(Customer customer, LocalDateTime time) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findByCustomerAndTime(customer, time);
        if (!order.isPresent()) {
            throw new OrderNotFoundException("Order with id not found");
        }
        return order.get();
    }

    @Override
    public List<Order> findAllByProducts(Product product) {
       return orderRepository.findAllByProducts(product);
    }

    @Override
    public List<Order> findAllByTime(LocalDateTime time) {
        return orderRepository.findAllByTime(time);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(Order order) throws InvalidOrderException {
      return null;
    }

    @Override
    public Order updateOrder(Order order) throws InvalidOrderException, OrderNotFoundException{
        Optional<Order> orderToUpdate = orderRepository.findById(order.getId());
        if (orderToUpdate.isPresent()) {
            if (order.getTime() != null) {
                orderToUpdate.get().setTime(order.getTime());
            }
            orderRepository.save(orderToUpdate.get());
        } else {
            throw new OrderNotFoundException("Order to update not found");
        }
        return orderToUpdate.get();
    }

    @Override
    public void deleteById(long id) {
        Optional<Order> orderToDelete = orderRepository.findById(id);
        if (orderToDelete.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order to delete not found");
        }
    }
}
