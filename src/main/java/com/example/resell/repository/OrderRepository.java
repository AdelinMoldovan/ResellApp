package com.example.resell.repository;

import com.example.resell.model.Customer;
import com.example.resell.model.Order;
import com.example.resell.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerAndTime(Customer customer, LocalDateTime time);
    List<Order> findAllByTime(LocalDateTime time);
}
