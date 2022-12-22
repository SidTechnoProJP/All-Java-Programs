package com.majorEvaluation.foodApp.controller;
import com.majorEvaluation.foodApp.entity.OtpStatus;
import com.majorEvaluation.foodApp.entity.User;
import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;
import com.majorEvaluation.foodApp.payload.SmsRequest;
import com.majorEvaluation.foodApp.service.AdminService;
import com.majorEvaluation.foodApp.service.IUserService;
import com.majorEvaluation.foodApp.service.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;


    @Autowired
    IUserService userService;
    @Autowired
    TwilioSmsSender twilioSmsSender;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody @Valid User user) throws InvalidUserCredentialException {
        return userService.signUpUser(user);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam("email") String emailId) {
        return adminService.sendOtp(emailId);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam("OTP") int otp){
        return adminService.verifyOtp(otp);

    }
    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        twilioSmsSender.sendSms(smsRequest);
        return ResponseEntity.ok().body(String.valueOf(OtpStatus.DELIVERED));

    }

   @PostMapping("/verify-mobile-otp")
   private String  validateOTP(@RequestParam("OTP") String userInputOtp){
        return twilioSmsSender.validateOTP(userInputOtp);
   }
}


