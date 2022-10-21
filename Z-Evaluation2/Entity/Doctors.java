package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctors {
    private String doctor_id;
    private String doctor_name;
    private int max_patient;
    private String hospital_id;
}
