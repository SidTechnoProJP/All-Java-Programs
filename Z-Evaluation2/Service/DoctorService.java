package com.Robosoftin.patientHelpdesk.Service;

import com.Robosoftin.patientHelpdesk.Entity.DoctorReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int generateReport(DoctorReport doctorReport){
        return jdbcTemplate.update("INSERT INTO doctor_report VALUES (?,?,?,?)",new Object[]{doctorReport.getPatient_id(),doctorReport.getDoctor_id(),doctorReport.getFindings(),doctorReport.getBill_paid()});
    }

}
