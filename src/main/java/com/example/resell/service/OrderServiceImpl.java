package com.example.resell.service;

import com.example.resell.exception.InvalidOrderException;
import com.example.resell.exception.OrderNotFoundException;
import com.example.resell.model.*;
import com.example.resell.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Order findById(Long id) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
        return order.get();
    }

    @Override
    public Order findByCustomerAndTime(Customer customer, LocalDateTime time) throws OrderNotFoundException {
        List<Order> order = orderRepository.findByCustomerAndTime(customer, time);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order with id not found");
        }
        return order.get(0);
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
        return orderRepository.save(order);
    }

    @Override
    public String generateInvoice(Order order) {
        StringBuilder invoice = new StringBuilder();

        invoice.append("Invoice\n");
        invoice.append("=======\n\n");

        invoice.append("Customer Details\n");
        invoice.append("----------------\n");
        invoice.append("Name: ").append(order.getCustomer().getFirstName()).append(" ").append(order.getCustomer().getLastName()).append("\n");
        invoice.append("Email: ").append(order.getCustomer().getEmail()).append("\n");
        invoice.append("Phone: ").append(order.getCustomer().getCustomerPhone()).append("\n\n");

        invoice.append("Shipping Address\n");
        invoice.append("----------------\n");
        invoice.append("Address: ").append(order.getShippingAddress().getAddress()).append("\n");
        invoice.append("City: ").append(order.getShippingAddress().getCity()).append("\n");
        invoice.append("Zipcode: ").append(order.getShippingAddress().getZipcode()).append("\n");
        invoice.append("Country: ").append(order.getShippingAddress().getCountry()).append("\n\n");

        invoice.append("Order Details\n");
        invoice.append("-------------\n");
        ShoppingCart shoppingCart = order.getShoppingCart();
        double total = 0;
        for (SingleCartItem item : shoppingCart.getSingleProductCart()) {
            invoice.append("Product: ").append(item.getProduct().getName()).append("\n");
            invoice.append("Quantity: ").append(item.getQuantity()).append("\n");
            invoice.append("Price: ").append(item.getPrice()).append("\n");
            invoice.append("--------------------\n");
            total += item.getPrice();
        }

        invoice.append("Total: ").append(total).append("\n");

        return invoice.toString();
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
    public void deleteById(Long id) {
        Optional<Order> orderToDelete = orderRepository.findById(id);
        if (orderToDelete.isPresent()) {
            orderRepository.deleteById((long) id);
        } else {
            throw new OrderNotFoundException("Order to delete not found");
        }
    }
}
