package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ward {
    private String ward_id;
    private String ward_name;
    private String doctor_id;
    private int ward_size;
    private String hospital_id;
}
