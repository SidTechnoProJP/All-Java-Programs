package com.example.demo.insurnance.controller;

import com.example.demo.insurnance.model.Accident;
import com.example.demo.insurnance.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccidentController {
    @Autowired
    AccidentService accidentService;
    @PostMapping(value = "/post/accident")
    public void saveAll(@RequestBody List<Accident> accidentList){
        accidentService.saveAll(accidentList);

    }
  @GetMapping(value = "/getaccident")
    public List<Accident> getAll(){
      return  accidentService.getAll();
  }
    @GetMapping(value = "/get/{year}")
    public Integer countName(@PathVariable Integer year){
        return accidentService.countName(year);
    }

    @GetMapping(value = "/get/count/of/{name}")
    public Integer countNameOfPerson(@PathVariable String name){
        return accidentService.countNameOfPerson(name);
    }

    @GetMapping(value = "/get/{amount}/{report_no}/{reg_no}")
    public ResponseEntity<HttpStatus> updateDamageAmount(@PathVariable int amount,@PathVariable int report_no,@PathVariable int reg_no){
        accidentService.updateDamageAmount(amount, report_no, reg_no);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
