package com.majorEvaluation.foodApp.service;

import com.majorEvaluation.foodApp.entity.User;
import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;
import org.springframework.http.ResponseEntity;

public interface IAdminService {
    public String signUp(User user) throws InvalidUserCredentialException;
    public boolean sendEmail(String message, String subject, String to);
    public ResponseEntity<String> sendOtp(String emailId);
    public ResponseEntity<String> verifyOtp(int otp);

}
