package com.example.resell.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPersons() {
//        return List.of(
//                new Person(
//                        1L,
//                        "Adelin",
//                        "adelin123",
//                        "moldovanadelin111@gmail.com",
//                        "adelin123",
//                        AppPersonRole.USER
//                )
//        );
        return personRepository.findAll();

    }

    public void addNewPerson(Person person){
        Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
        if(personOptional.isPresent()){
            throw new IllegalStateException("This email is taken.");
        }
        personRepository.save(person);

//        Optional<Person> personOptionalUsername = personRepository.findPersonByUsername(person.getUsername());
//        if(personOptionalUsername.isPresent()){
//            throw new IllegalStateException("This username is taken.");
//        }
//        personRepository.save(person);
        //System.out.println(person);
    }

    public void deletePerson(Long personId) {
        boolean personExist = personRepository.existsById(personId);
        if(!personExist){
            throw new IllegalStateException(
                    "Perosn with id " + personId + " does not exist.");
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(long personId,
                             String username,
                             String email,
                             String password) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "Person with id " + personId + " does not exist"));
        if(username != null &&
                username.length() > 0 &&
                !Objects.equals(person.getUsername(), username)) {
//            Optional<Person> personOptionalUsername = personRepository.findPersonByUsername(person.getUsername());
//            if(personOptionalUsername.isPresent()){
//                throw new IllegalStateException("This username is taken.");
//            }
            person.setUsername(username);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
            if(personOptional.isPresent()){
                throw new IllegalStateException("This email is taken.");
            }
            person.setEmail(email);
        }

        if(password != null &&
                email.length() > 0 &&
                !Objects.equals(person.getPassword(), password)) {
            person.setPassword(password);
        }


    }
}
