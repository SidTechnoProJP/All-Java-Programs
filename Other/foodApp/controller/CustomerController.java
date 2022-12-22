package com.majorEvaluation.foodApp.controller;

import com.majorEvaluation.foodApp.entity.User;
import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;
import com.majorEvaluation.foodApp.service.AdminService;
import com.majorEvaluation.foodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CustomerController {

    @Autowired
    private UserService userService;

//    @PostMapping("/user")
//    public String signUp(@RequestBody @Valid User user) throws InvalidUserCredentialException {
//        return userService.signUp(user);
//    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam String emailId, @RequestParam String password) throws NoSuchElementException {
        int sessionId = userService.signIn(emailId, password);
        if (sessionId == 0) {
            return "already logged in";
        }
        return "yor are logged in with " + "sessionId :" + sessionId;
    }

    @DeleteMapping("/sign-out")
    public String signOut() {
        return userService.signOut();
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String password, @RequestParam String emailId) {
        return userService.resetPassword(password, emailId);

    }
}