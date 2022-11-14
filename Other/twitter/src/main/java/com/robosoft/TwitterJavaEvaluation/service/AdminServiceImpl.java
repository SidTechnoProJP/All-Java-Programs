package com.robosoft.TwitterJavaEvaluation.service;

import com.robosoft.TwitterJavaEvaluation.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    JdbcTemplate jdbcTemplate;
    int sessionId;
    @Override
    public String signIn(Admin admin) {
        String query = "select admin_id from admin where admin_id='"+admin.getAdminId()+"' and password = '"+admin.getPassword()+"'";
        String adminId= jdbcTemplate.queryForObject(query,String.class);
        if(adminId.equals(admin.getAdminId())) {
            sessionId= new Random().nextInt(1,1000);
            return String.valueOf(sessionId);
        }
        return null;
    }

    @Override
    public Boolean verifyUsers(String userId,int sId,Boolean bool) {
        if(sId==sessionId) {
            String query = "update users set verified = "+bool+" where user_id = '"+userId+"'";
            jdbcTemplate.update(query);
            return true;
        }
        return false;
    }

}
