package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientReport {

    private String patient_id;
    private String patient_name;
    private String doctor_id;
    private  String findings;

}
