package com.example.resell.repository;

import com.example.resell.model.Customer;
import com.example.resell.model.Order;
import com.example.resell.model.Product;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(long id);
    Optional<Order> findByCustomerAndTime(Customer customer, LocalDateTime time);
    List<Order> findAllByProducts(Product product);
    List<Order> findAllByTime(LocalDateTime time);

    List<Order> findAll();
}
