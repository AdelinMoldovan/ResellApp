package com.example.resell.repository;

import com.example.resell.model.AppPersonRole;
import com.example.resell.model.Person;
import org.hibernate.usertype.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(long id);
    Optional<Person> findByEmailAndPassword(String email, String password);
    List<Person> findAll();

    List<Person> findAllByAppPersonRole(AppPersonRole appPersonRole);
}
