package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReport {
    private String patient_id;
    private String doctor_id;
    private String findings;
    private String bill_paid;
}
