package com.Robosoftin.patientHelpdesk.Controller;

import com.Robosoftin.patientHelpdesk.Entity.InPatient;
import com.Robosoftin.patientHelpdesk.Entity.OutPatient;
import com.Robosoftin.patientHelpdesk.Entity.PatientReport;
import com.Robosoftin.patientHelpdesk.Service.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.el.ELException;
import java.rmi.server.RemoteServer;
import java.util.Optional;

@RestController
public class HelpDeskController {
    @Autowired
    HelpDeskService helpDeskService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Create Out Patient at Helpdesk
    @PostMapping("/helpdesk/outPatient/{doctor_id}")
    public ResponseEntity<String> createOutPatient(@RequestBody OutPatient outPatient, @PathVariable String doctor_id){
        int addedOutPatient = helpDeskService.createOutPatient(outPatient,doctor_id);
        try {
            if(addedOutPatient > 0){
                return ResponseEntity.of(Optional.of(addedOutPatient+" Appointment Booked, Doctor ID is "+doctor_id));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //Get Out Patient report
    @GetMapping("/helpdesk/outPatientReport/{patient_id}")
    public ResponseEntity<String> getReport(@PathVariable String patient_id){
        return ResponseEntity.of(Optional.of(helpDeskService.getPatientReport(patient_id)+" Report Saved"));
    }

    //Create In Patient and allocate ward
    @PostMapping("/helpdesk/inPatient/{ward_id}")
    public ResponseEntity<String> createInPatient(@RequestBody InPatient inPatient, @PathVariable String ward_id){
        int addInPatient = helpDeskService.createInPatient(inPatient, ward_id);
        try {
            if(addInPatient > 0){
                return ResponseEntity.of(Optional.of(addInPatient+" Item added"));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Get In Patient Report
    @GetMapping("/helpdesk/inPatientReport/{patient_id}")
    public ResponseEntity<String> getInPatientReport(@PathVariable String patient_id){
        int inPat = helpDeskService.getInPatientReport(patient_id);
        try {
            if(inPat > 0){
                return ResponseEntity.of(Optional.of(inPat+" Fetched Report"));
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
