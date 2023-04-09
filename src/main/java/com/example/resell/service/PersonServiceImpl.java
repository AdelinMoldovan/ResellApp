package com.example.resell.service;


import com.example.resell.exception.InvalidPersonException;
import com.example.resell.exception.PersonNotFoundException;
import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.AppPersonRole;
import com.example.resell.model.Person;
import com.example.resell.repository.PersonRepository;
import com.example.resell.validator.PersonDetailsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDetailsValidator personDetailsValidator;

    public PersonServiceImpl(PersonRepository personRepository, PersonDetailsValidator personDetailsValidator){
        this.personRepository = personRepository;
        this.personDetailsValidator = personDetailsValidator;
    }

    @Override
    public Person findById(long id) throws PersonNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) {
            throw new PersonNotFoundException("Person with id " + id + " not found");
        }
        return person.get();
    }

    @Override
    public Person findByEmailAndPassword(String email, String password) {
        Optional<Person> person = personRepository.findByEmailAndPassword(email, password);
        if (!person.isPresent()) {
            throw new PersonNotFoundException("Person with email " + email + " and password " + password + " not found");
        }
        return person.get();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findAllByPersonRole(AppPersonRole appPersonRole) {
        return personRepository.findAllByAppPersonRole(appPersonRole);
    }

    @Override
    public Person addPerson(Person person) throws InvalidPersonException {
        try {
            personDetailsValidator.validateUserDetails(person);
            Optional<Person> personToAdd = personRepository.findByEmailAndPassword(person.getEmail(), person.getPassword());
            if (!personToAdd.isPresent()) {
                personRepository.save(person);
            }
        } catch (WrongDetailsException exp) {
            throw new InvalidPersonException(exp.getMessage());
        }
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        Optional<Person> personToUpdate = personRepository.findById(person.getId());
        if (personToUpdate.isPresent()) {
            Person finalPerson = createPerson(person, personToUpdate.get());
            try {
                personDetailsValidator.validateUserDetails(finalPerson);
            } catch (WrongDetailsException e) {
                throw new InvalidPersonException(e.getMessage());
            }
            personRepository.save(finalPerson);
        } else {
            throw new PersonNotFoundException("Person to update not found");
        }
        return personToUpdate.get();
    }

    @Override
    public void deleteById(long personId) {
        Optional<Person> personToDelete = personRepository.findById(personId);
        if (personToDelete.isPresent()) {
            personRepository.deleteById(personId);
        } else {
            throw new PersonNotFoundException("Person to delete not found");
        }
    }

    private Person createPerson(Person person, Person personToUpdate) {
        if (person.getEmail() != null) {
            personToUpdate.setEmail(person.getEmail());
        }
        if (person.getPassword() != null) {
            personToUpdate.setPassword(person.getPassword());
        }
        return personToUpdate;
    }

}
