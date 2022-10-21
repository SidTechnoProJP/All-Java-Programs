package com.Robosoftin.patientHelpdesk.Controller;

import com.Robosoftin.patientHelpdesk.Entity.DoctorReport;
import com.Robosoftin.patientHelpdesk.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    //Create Report
    @PostMapping("/doctor/report")
    public ResponseEntity<String> report(@RequestBody DoctorReport doctorReport){
        int generatedReport = doctorService.generateReport(doctorReport);
        try {
            if(generatedReport > 0){
                return ResponseEntity.of(Optional.of(generatedReport+" Item added"));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
