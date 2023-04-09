package com.example.resell.repository;

import com.example.resell.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(long id);
    List<Product> findAllByName(String name);
    List<Product> findAllByProductCategory(String category);
    List<Product> findAll();
}
