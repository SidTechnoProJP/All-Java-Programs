package com.spring.restlogin.service;

import com.spring.restlogin.exception.SignInFailedException;
import com.spring.restlogin.exception.SignOutFailedException;
import com.spring.restlogin.exception.SignUpFailedException;
import com.spring.restlogin.model.User;
import org.springframework.stereotype.Service;

public interface LoginInterface {
    String signIn(String username, String password) throws SignInFailedException;

    String signUp(User user) throws SignUpFailedException;

    String signOut() throws SignOutFailedException;

    // String forgotPassword()

}
