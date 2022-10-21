package com.Robosoftin.patientHelpdesk.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelpDesk {
    private String helpdesk_id;
    private String hospital_id;
}
