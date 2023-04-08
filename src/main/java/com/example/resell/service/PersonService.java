package com.example.resell.service;

import com.example.resell.dataAccessObject.PersonRepository;
import com.example.resell.model.AppPersonRole;
import com.example.resell.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Component
public interface PersonService {

    Person findById(long id); // throws PersonNotFoundException;
    Person findByEmailAndPassword(String email, String password); //throws PersonNotFoundException;
    List<Person> findAll();
    List<Person> findAllByPersonRole(AppPersonRole appPersonRole);

    Person addPerson(Person person); // throws InvalidPersonException;
    Person updatePerson(Person person); // throws PersonNotFoundException, InvalidPersonException;

    void deleteById(long personId); // throws PersonNotFoundException;


}
