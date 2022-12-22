package com.evaluation.foodapp.controller;

import com.evaluation.foodapp.exception.InvalidUserCredentialException;
import com.evaluation.foodapp.model.Customer;
import com.evaluation.foodapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.LoginDTO;
import com.evaluation.foodapp.service.LoginService;

import javax.validation.Valid;

@RestController
@RequestMapping("/app")
public class LoginLogoutController {
    @Autowired
    CustomerService customerService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginDTO loginDTO) throws LoginException {
        String result = loginService.loginAccount(loginDTO);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam(required = false) String role,
                                         @RequestParam(required = false) String key) throws LoginException {
        String result = loginService.logoutAccount(role, key);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
