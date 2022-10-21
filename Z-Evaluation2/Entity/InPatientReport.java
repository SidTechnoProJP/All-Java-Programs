package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InPatientReport {
    private String patient_id;
    private String patient_name;
    private String doctor_id;
    private String surgery_type;
    private String findings;
    private int admitted_days;
    private String bill_payment;
}
