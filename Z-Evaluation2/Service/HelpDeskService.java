package com.Robosoftin.patientHelpdesk.Service;
import com.Robosoftin.patientHelpdesk.Entity.Doctors;
import com.Robosoftin.patientHelpdesk.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelpDeskService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    Doctors doctors;

    //Create Out Patient
    public int createOutPatient(OutPatient outPatient, String doctor_id){
        if(doctors.getMax_patient() > 0) {
            decreasePatientCount(doctor_id);
            int outPat = jdbcTemplate.update("INSERT INTO out_patient values (?,?,?,?)", new Object[]{outPatient.getPatient_id(), outPatient.getDoctor_id(), outPatient.getPatient_name(), outPatient.getPatient_age()});
            return outPat;
        }
        return 0;
    }

    //Method to decrease Max Patient count on Patient Registration
    public int decreasePatientCount(String doctor_id){
        int dec = jdbcTemplate.update("update doctors set max_patient = max_patient - 1 where doctor_id = ?",doctor_id);
        return dec;
    }

    //Send Patient details to doctor
    public int bookAppointment(String patient_id){

        PatientDetails patientDetails = jdbcTemplate.queryForObject("select out_patient.patient_id, out_patient.doctor_id, out_patient.patient_name, out_patient.patient_age from out_patient\n"+
                "inner join registration on\n" +
                "registration.patient_id = out_patient.patient_id and registration.patient_id = ?",new BeanPropertyRowMapper<PatientDetails>(PatientDetails.class),patient_id);

        return jdbcTemplate.update("INSERT INTO patient_details VALUES(?,?,?,?)",patientDetails.getPatient_id(),patientDetails.getDoctor_id(),patientDetails.getPatient_name(),patientDetails.getPatient_age());
    }


    //Get Out Patient Report
    public int getPatientReport(String patient_id){
        PatientReport patientReport = jdbcTemplate.queryForObject(" select doctor_report.patient_id, registration.patient_name, doctor_report.doctor_id, doctor_report.findings from doctor_report\n" +
                "inner join registration on registration.patient_id = doctor_report.patient_id and doctor_report.patient_id = ? and doctor_report.bill_paid= 'Yes'",new BeanPropertyRowMapper<PatientReport>(PatientReport.class),patient_id);
        return jdbcTemplate.update("INSERT INTO patient_report VALUES (?,?,?,?)",patientReport.getPatient_id(),patientReport.getPatient_name(),patientReport.getDoctor_id(),patientReport.getFindings());
    }


    //Create In Patient
    public int createInPatient(InPatient inPatient, String ward_id){
        int inPat =  jdbcTemplate.update("INSERT INTO in_patient VALUES (?,?,?,?,?,?)",new Object[]{inPatient.getPatient_id(),inPatient.getPatient_name(),inPatient.getWard_id(),inPatient.getWard_name(),inPatient.getInsurance_covered(),inPatient.getAmount_paid()});
        decreaseWardSizeCount(ward_id);
        return inPat;
    }

    //Method to decrease Max Patient count on Patient Registration
    public int decreaseWardSizeCount(String ward_id){
        int decCount = jdbcTemplate.update("update ward set ward_size = ward_size - 1 where ward_id = ?",ward_id);
        return decCount;
    }

    //Get In Patient Report
    public int getInPatientReport(String patient_id){
        InPatientReport inPatientReport = jdbcTemplate.queryForObject("select ward_report.patient_id, ward_report.patient_name, ward_report.doctor_id, ward_report.surgery_type,\n" +
                "ward_report. findings, ward_report.admitted_days from ward_report\n" +
                "inner join registration on\n" +
                "registration.patient_id = ward_report.patient_id ward_report.patient_id = ? and ward_report.bill_payment='Yes'",new BeanPropertyRowMapper<InPatientReport>(InPatientReport.class),patient_id);

        return jdbcTemplate.update("INSERT INTO in_patient_report VALUES (?,?,?,?,?,?)",inPatientReport.getPatient_id(),inPatientReport.getPatient_name(),inPatientReport.getDoctor_id(),inPatientReport.getSurgery_type(),inPatientReport.getFindings(),inPatientReport.getAdmitted_days());
    }

}


