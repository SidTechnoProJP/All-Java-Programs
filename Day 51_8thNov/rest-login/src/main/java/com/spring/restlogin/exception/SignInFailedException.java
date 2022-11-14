package com.spring.restlogin.exception;

public class SignInFailedException extends Exception {
    public SignInFailedException(String failed_login) { super(failed_login); }
}
