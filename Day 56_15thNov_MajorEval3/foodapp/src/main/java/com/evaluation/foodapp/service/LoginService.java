package com.evaluation.foodapp.service;

import com.evaluation.foodapp.exception.InvalidUserCredentialException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.*;
import org.springframework.http.ResponseEntity;


public interface LoginService {

	public String loginAccount(LoginDTO loginDTO) throws LoginException;

	public String logoutAccount(String role, String key) throws LoginException;

}
