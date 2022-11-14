package com.spring.restlogin.controller;

import com.spring.restlogin.exception.SignInFailedException;
import com.spring.restlogin.exception.SignOutFailedException;
import com.spring.restlogin.exception.SignUpFailedException;
import com.spring.restlogin.model.User;
import com.spring.restlogin.service.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginPageController {

    @Autowired
    private LoginInterface loginInterface;

    @PostMapping("/sign-in")
    public ResponseEntity<String> userLogin(@RequestParam String username, @RequestParam String password) throws SignInFailedException {
        if (username != null && (password != null))
            return ResponseEntity.of(Optional.of(loginInterface.signIn(username, password)));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization failed.");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> newRegistration(@RequestBody User user) throws SignUpFailedException{
        if (user != null)
            return ResponseEntity.of(Optional.of(loginInterface.signUp(user)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provide valid details for registration");
    }

    @DeleteMapping("/sign-out")
    public ResponseEntity<String> logOut() throws SignOutFailedException {
        return ResponseEntity.of(Optional.of(loginInterface.signOut()));
    }
}
