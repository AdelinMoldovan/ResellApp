package com.example.resell.repository;

import com.example.resell.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(long id);
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Admin> findByEmailAndPassword(String email, String password);

    List<Admin> findAll();
}