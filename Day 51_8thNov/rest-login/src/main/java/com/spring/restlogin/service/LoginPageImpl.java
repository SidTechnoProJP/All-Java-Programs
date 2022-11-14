package com.spring.restlogin.service;

import com.spring.restlogin.exception.SignInFailedException;
import com.spring.restlogin.exception.SignOutFailedException;
import com.spring.restlogin.exception.SignUpFailedException;
import com.spring.restlogin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;

@Service
public class LoginPageImpl implements LoginInterface{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sessionId;

    @Override
    public String signIn(String username, String password) throws SignInFailedException {
        try {
            if (sessionId == null) {
                if (jdbcTemplate.query("select *  from user where username = ? and password  = ?",
                        new BeanPropertyRowMapper<>(User.class), username, password).isEmpty())
                    return "Invalid username or password";
                this.sessionId = generateSessionId();
                return "Login Successful with Session Id : " + sessionId;
            }
            return "You are already logged in. To SignIn again, please LogOut. ";

        } catch (DataAccessException exception) {
            throw new SignInFailedException("Failed login");
        }
    }

    @Override
    public String signUp(User user) throws SignUpFailedException {
        try {
            if (sessionId == null) {
                if (jdbcTemplate.query("Select * from user where username = ? ",
                        new BeanPropertyRowMapper<>(User.class), user.getUserName()).isEmpty()) {
                    if (jdbcTemplate.query("Select * from user where email = ? ",
                            new BeanPropertyRowMapper<>(User.class), user.getEmail()).isEmpty()) {
                        jdbcTemplate.update("Insert into user values(?,?,?,?,?,?,?)", user.getUserName(), user.getName(), user.getEmail(), user.getPassword(),
                                user.getDateOfBirth(), user.getPhoneNumber(), LocalDate.now());
                        return "Account successfully created";
                    }
                    return "Email is already registered.";
                }
                return "username is already taken try with other username.";
            }
            return "you are already logged in. please logout for new sign up";
        } catch (DataAccessException exception) {
            throw new SignUpFailedException("Failed to register.Please provide valid details");
        }
    }

    @Override
    public String signOut() throws SignOutFailedException {
        try {
            if (sessionId != null) {
                this.sessionId = null;
                return "sign out successful";
            }
            return "already signed out     ";
        } catch (Exception exception) {
            throw new SignOutFailedException("sign out failed.try again after sometime.");
        }
    }

    protected String generateSessionId() {
        return Integer.toString(new Random().nextInt(100, 1000));
    }

    public String getOTPEmail() {
        String getOTP = generateOTP();
        String message = "This email is regarding your recent request to change/reset your password. " +
                "\nYour OTP is: " + getOTP;
        String subject = "OTP to Change/Reset Your Password";
        String toEmail = "sid123.sawant@gmail.com";
        String fromEmail = "";

        return sendEmail(message, subject, toEmail, fromEmail);
    }

    protected static String sendEmail(String message, String subject, String toEmail, String fromEmail){
        //Variable for gmail host
        String host = "smtp.gmail.com";

        //get system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES: "+properties);

        //setting important information to properties

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "465");

    }
    protected static String generateOTP() {
        return Integer.toString(new Random().nextInt(1000, 9999));
    }

}
