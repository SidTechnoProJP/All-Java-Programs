package com.Robosoftin.patientHelpdesk.Service;

import com.Robosoftin.patientHelpdesk.Entity.Doctors;
import com.Robosoftin.patientHelpdesk.Entity.HelpDesk;
import com.Robosoftin.patientHelpdesk.Entity.Hospital;
import com.Robosoftin.patientHelpdesk.Entity.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Add Hospital
    public int addHospital(Hospital hospital){
        return jdbcTemplate.update("INSERT INTO hospital VALUES (?,?,?)",new Object[]{hospital.getHospital_id(),hospital.getHospital_name(),hospital.getHospital_location()});
    }

    public int addHelpdesk(HelpDesk helpDesk){
        return jdbcTemplate.update("INSERT INTO helpdesk VALUES (?,?)", new Object[]{helpDesk.getHelpdesk_id(),helpDesk.getHospital_id()});
    }

    public int addDoctors(Doctors doctors){
        return jdbcTemplate.update("INSERT INTO doctors VALUES (?,?,?,?)", new Object[]{doctors.getDoctor_id(),doctors.getDoctor_name(),doctors.getMax_patient(),doctors.getHospital_id()});
    }

    //Create Ward
    public int addWard(Ward ward){
        return jdbcTemplate.update("INSERT INTO ward VALUES (?,?,?,?,?)",new Object[]{ward.getWard_id(),ward.getWard_name(),ward.getDoctor_id(),ward.getWard_size(),ward.getHospital_id()});
    }

}
