package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String patient_id;
    private String patient_name;
    private int patient_age;
    private String patient_gender;
}
