package com.Robosoftin.patientHelpdesk.Controller;

import com.Robosoftin.patientHelpdesk.Entity.*;
import com.Robosoftin.patientHelpdesk.Service.HelpDeskService;
import com.Robosoftin.patientHelpdesk.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    HelpDeskService helpDeskService;

    @GetMapping("/patient/hospitals")
    public ResponseEntity<List<Hospital>> getHospital(){
        List<Hospital> hospitals = patientService.getHospital();
        try {
            if(hospitals.size() > 0){
                return ResponseEntity.of(Optional.of(hospitals));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/patient/helpdesk/{hospital_id}")
    public ResponseEntity<List<HelpDesk>> getHelpdesk(@PathVariable String hospital_id){
        List<HelpDesk> helpDesks = patientService.getHelpDesk(hospital_id);
        try {
            if(helpDesks.size() > 0){
                return ResponseEntity.of(Optional.of(helpDesks));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping("patient/register")
    public ResponseEntity<String> register(@RequestBody Registration registration){
        int addPatient = patientService.getRegistered(registration);
        try {
            if(addPatient > 0){
                return ResponseEntity.of(Optional.of(addPatient+" Item added"));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Search Doctors
    @GetMapping("/patient/doctors/{hospital_id}")
    public ResponseEntity<List<Doctors>> getDoctors(@PathVariable String hospital_id){
        List<Doctors> doctorsList = patientService.getDoctors(hospital_id);
        try {
            if(doctorsList.size() > 0){
                return ResponseEntity.of(Optional.of(doctorsList));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Get Out patient Report
    @GetMapping("/patient/outPatientReport/{patient_id}")
    public ResponseEntity<PatientReport> getReport(@PathVariable String patient_id){
        return ResponseEntity.of(Optional.of(patientService.outPatientReport(patient_id)));
    }

    //Get In Patient Report
    @GetMapping("/patient/inPatientReport/{patient_id}")
    public ResponseEntity<InPatientReport> getInPatientReport(@PathVariable String patient_id){
        InPatientReport patientReport = patientService.inPatientReport(patient_id);
        try {
            if(patientReport != null){
                return ResponseEntity.of(Optional.of(patientReport));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
