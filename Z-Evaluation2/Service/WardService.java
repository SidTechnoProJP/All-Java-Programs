package com.Robosoftin.patientHelpdesk.Service;

import com.Robosoftin.patientHelpdesk.Entity.WardReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WardService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Generate Report for Admitted Patient
    public int generateInPatientReport(WardReport wardReport){
        return jdbcTemplate.update("INSERT INTO ward_report VALUES (?,?,?,?,?,?,?)", new Object[]{wardReport.getPatient_id(),wardReport.getPatient_name(),wardReport.getDoctor_id(),wardReport.getSurgery_type(),wardReport.getFindings(),wardReport.getAdmitted_days(),wardReport.getBill_payment()});
    }

}
