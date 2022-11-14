package com.example.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        String name=jdbcTemplate.queryForObject("select username from userTable where username=?", String.class, new Object[]{username});
        String password=jdbcTemplate.queryForObject("select password from userTable where username=?",String.class, new Object[]{username});
        return new User(name, password, new ArrayList<>());
    }


}
