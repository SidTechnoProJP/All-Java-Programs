package com.majorEvaluation.foodApp.service;
import com.majorEvaluation.foodApp.entity.User;
import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;
import com.majorEvaluation.foodApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
     IAdminService adminService;

    int sessionId;

    public String signUpUser(User user) throws InvalidUserCredentialException {
             return adminService.signUp(user);
    }

    public int signIn(String emailId, String password) throws NoSuchElementException {
        if (!(sessionId == 0)) {
            return 0;
        }
        Optional<User> user = userRepository.findUserByEmailIdAndPassword(emailId, password);
        if (user.isPresent()) {
            sessionId = generateSessionId();
            return sessionId;
        } else {
            throw new NoSuchElementException("No value s present in DB");
        }

    }


    public String signOut() {
        if (!(sessionId == 0)) {
            this.sessionId = 0;
            return "Signed Out successfully";
        }
        return "Already signed out";
    }


    public int generateSessionId() {
        return new Random().nextInt(1000, 100000);
    }

    public boolean checkSessionId(int sessionId) {
        return sessionId == this.sessionId;
    }

    public ResponseEntity<String> resetPassword(String password, String emailId) {
        Optional<User> user = userRepository.findUserByEmail(emailId);
        if (user.isPresent()) {
            userRepository.resetPassword(password, emailId);
            return ResponseEntity.ok().body("Your password has been resetted");
        }
        return ResponseEntity.ok().body("Enter the existing email Id");
    }




}

