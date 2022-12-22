package com.majorEvaluation.foodApp.service;

import com.majorEvaluation.foodApp.entity.User;
import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;

public interface IUserService {
    public String signUpUser(User user) throws InvalidUserCredentialException;
    public int signIn(String emailId, String password);
    public String signOut();
}
