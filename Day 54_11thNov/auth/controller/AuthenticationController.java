package com.movieTicketBookingSystem.auth.controller;

//<dependency>
//        <groupId>com.twilio.sdk</groupId>
//        <artifactId>twilio</artifactId>
//        <version>7.34.0</version>
//        </dependency>

import com.movieTicketBookingSystem.auth.model.RegistrationModel;
import com.movieTicketBookingSystem.auth.model.SignInModel;
import com.movieTicketBookingSystem.auth.service.IauthenticationService;
import com.movieTicketBookingSystem.auth.model.PasswordResetRequestDto;
import com.movieTicketBookingSystem.auth.service.TwilioOtpService;
import com.movieTicketBookingSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class AuthenticationController {
    @Autowired
    IauthenticationService authenticationService;
@Autowired
    EmailService emailService;

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegistrationModel registrationModel){
        try {
            return ResponseEntity.ok(authenticationService.register(registrationModel));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
    @PostMapping("/otp")
    ResponseEntity<?> otp(@RequestBody PasswordResetRequestDto passwordResetRequestDto){
        try {
            return ResponseEntity.ok(authenticationService.sendOTPForPasswordReset(passwordResetRequestDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
    @GetMapping("/send")
    ResponseEntity<?> email(){
        try {
            emailService.sendSimpleMessage("sumanthprabhu2001@gmail.com");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }


    @PostMapping("/signIn")
    ResponseEntity<?> sigIn(@RequestBody SignInModel signInModel){
        try {
            return ResponseEntity.ok(authenticationService.signIn(signInModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getLocalizedMessage());
        }
    }

    //signIn
    @PatchMapping("/user/{phoneNumber}")
    ResponseEntity<?> update(@PathVariable Long phoneNumber ,@RequestBody Map<String ,Object> credentials){
        try {

            return ResponseEntity.ok(authenticationService.update(phoneNumber,credentials));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Authentication failed");
        }
    }

}
