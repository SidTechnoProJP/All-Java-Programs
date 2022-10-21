package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InPatient {
    private String patient_id;
    private String patient_name;
    private String ward_id;
    private String ward_name;
    private String insurance_covered;
    private int amount_paid;
}
