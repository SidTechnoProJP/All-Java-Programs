package com.example.demo.insurnance.service;

import com.example.demo.insurnance.model.Person;
import com.example.demo.insurnance.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepository;
    @Override
    public ResponseEntity<HttpStatus> saveAll(List<Person> person) {
        personRepository.saveAll(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
