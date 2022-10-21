package com.Robosoftin.patientHelpdesk.Service;

import com.Robosoftin.patientHelpdesk.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Get All Hospital
    public List<Hospital> getHospital(){
        return jdbcTemplate.query("SELECT * FROM hospital",new BeanPropertyRowMapper<Hospital>(Hospital.class));
    }

    //Get Helpdesk
    public List<HelpDesk> getHelpDesk(String hospital_id){
        return jdbcTemplate.query("SELECT * FROM helpdesk WHERE hospital_id = ?",new BeanPropertyRowMapper<HelpDesk>(HelpDesk.class),hospital_id);
    }

    //Registration
    public int getRegistered(Registration registration){
        return jdbcTemplate.update("INSERT INTO registration VALUES (?,?,?,?)",new Object[]{registration.getPatient_id(),registration.getPatient_name(),registration.getPatient_age(),registration.getPatient_gender()});
    }

    //Get all Doctors
    public List<Doctors> getDoctors(String hospital_id){
        return jdbcTemplate.query("SELECT * FROM doctors WHERE hospital_id = ?", new BeanPropertyRowMapper<Doctors>(Doctors.class), hospital_id);
    }

    //Get Out Patient Report
    public PatientReport outPatientReport(String patient_id){
        return jdbcTemplate.queryForObject("SELECT * FROM patient_report WHERE patient_id = ?", new BeanPropertyRowMapper<PatientReport>(PatientReport.class),patient_id);
    }


    //Get In Patient Report
    public InPatientReport inPatientReport(String patient_id){
        return jdbcTemplate.queryForObject("SELECT * FROM in_patient_report WHERE patient_id = ?", new BeanPropertyRowMapper<InPatientReport>(InPatientReport.class),patient_id);
    }

}
