package com.example.resell.controller;

import com.example.resell.service.PersonService;
import com.example.resell.model.Person;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getPersons() {
        return personService.getPersons();

    }

    @GetMapping("/findById")
    public ResponseEntity findPersonById

//    @PostMapping //add new person
//    public void registerNewPerson(@RequestBody Person person) {
//        personService.addNewPerson(person);
//    }
//
//    @DeleteMapping(path = "{personId}") //delete
//    public void deletePerson(@PathVariable("personId") Long personId) {
//        personService.deletePerson(personId);
//    }
//
//    @PutMapping(path = "{personId}") //update
//    public void updatePerson(
//            @PathVariable("personId") long personId,
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) String password) {
//        personService.updatePerson(personId , email, password);
//    }

}
