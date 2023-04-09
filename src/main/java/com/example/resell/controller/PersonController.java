package com.example.resell.controller;

import com.example.resell.service.PersonService;
import com.example.resell.model.Person;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/findById")
    public ResponseEntity findPersonById(@RequestParam long id){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));
    }

    @GetMapping("/findByEmail")
    public ResponseEntity findPersonByEmailAndPassword(@RequestParam String email, @RequestParam String password){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findByEmailAndPassword(email, password));
    }

    @GetMapping("/findAllByAppPersonRole")
    public ResponseEntity findAllPersonsByAppPersonRole(){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @GetMapping()
    public ResponseEntity findAllPersons() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }
}
