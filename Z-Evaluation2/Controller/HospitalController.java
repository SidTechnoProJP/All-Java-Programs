package com.Robosoftin.patientHelpdesk.Controller;

import com.Robosoftin.patientHelpdesk.Entity.*;
import com.Robosoftin.patientHelpdesk.Service.HospitalService;
import com.Robosoftin.patientHelpdesk.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @Autowired
    PatientService patientService;

    //Create Hospitals
    @PostMapping("/hospital")
    public ResponseEntity<String> saveHospital(@RequestBody Hospital hospital){
        int addedHospital = hospitalService.addHospital(hospital);
        try{
            if(addedHospital > 0){
                return ResponseEntity.of(Optional.of(addedHospital+" Item Added"));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Create Helpdesk at Hospital
    @PostMapping("/hospital/helpdesk")
    public ResponseEntity<String> saveHelpdesk(@RequestBody HelpDesk helpDesk){
        int addedHelpdesk = hospitalService.addHelpdesk(helpDesk);
        try{
            if(addedHelpdesk > 0){
                return ResponseEntity.of(Optional.of(addedHelpdesk+" Item added"));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/hospital/doctors")
    public ResponseEntity<String> addDoctors(@RequestBody Doctors doctors){
        int addedDoctor = hospitalService.addDoctors(doctors);
        try {
            if(addedDoctor > 0){
                return ResponseEntity.of(Optional.of(addedDoctor+" Item added"));
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/hospital/ward")
    public ResponseEntity<String> addWard(@RequestBody Ward ward){
        int addedWard = hospitalService.addWard(ward);
        try {
            if(addedWard > 0){
                return ResponseEntity.of(Optional.of(addedWard+" Item added"));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
