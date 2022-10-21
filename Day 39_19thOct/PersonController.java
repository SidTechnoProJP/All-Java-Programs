package com.example.demo.insurnance.controller;

import com.example.demo.insurnance.model.Person;
import com.example.demo.insurnance.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonService personService;
    @PostMapping(value = "/post")
    public void saveAll(@RequestBody List<Person> person){
        personService.saveAll(person);

    }

    @GetMapping(value = "/get")
    public List<Person> getAll(){
        return personService.getAll();
    }
}
