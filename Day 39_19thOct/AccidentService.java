package com.example.demo.insurnance.service;

import com.example.demo.insurnance.model.Accident;
import com.example.demo.insurnance.repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class AccidentService implements IAccidentService{
    @Autowired
    AccidentRepository  accidentRepository;

    @Override
    public ResponseEntity<HttpStatus> saveAll(List<Accident> accidentList) {
        accidentRepository.saveAll(accidentList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public List<Accident> getAll() {
        return accidentRepository.findAll();
    }

    @Override
    public Integer countName(Integer year) {
        return accidentRepository.countName(year);
    }

    @Override
    public Integer countNameOfPerson(String name) {
        return accidentRepository.countNameOfPerson(name);
    }

    @Override
    public void updateDamageAmount(int amount,int report_no,int reg_no) {
        accidentRepository.updateDamageAmount(amount,report_no,reg_no);
    }
}
