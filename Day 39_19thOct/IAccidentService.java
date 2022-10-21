package com.example.demo.insurnance.service;

import com.example.demo.insurnance.model.Accident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccidentService {

    public ResponseEntity<HttpStatus> saveAll(List<Accident> accidentList);
    public List<Accident> getAll();

    public Integer countName(Integer year);

    public Integer countNameOfPerson(String name);

    public void updateDamageAmount(int amount,int report_no,int reg_no);


}
