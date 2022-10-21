package com.example.demo.insurnance.service;

import com.example.demo.insurnance.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPersonService {
   public ResponseEntity<HttpStatus> saveAll(List<Person> person);

   public List<Person> getAll();

}
