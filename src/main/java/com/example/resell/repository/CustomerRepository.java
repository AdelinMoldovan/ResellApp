package com.example.resell.repository;

import com.example.resell.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(long id);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findAll();
}
