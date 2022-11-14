package com.springboot.phoneotp.controller;

import com.springboot.phoneotp.model.SignInModel;
import com.springboot.phoneotp.service.IauthenticationService;
import com.springboot.phoneotp.model.PasswordResetRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
public class AuthenticationController {
    @Autowired
    IauthenticationService authenticationService;

    @PostMapping("/otp")
    ResponseEntity<?> otp(@RequestBody PasswordResetRequestDto passwordResetRequestDto){
        try {
            return ResponseEntity.ok(authenticationService.sendOTPForPasswordReset(passwordResetRequestDto));
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
