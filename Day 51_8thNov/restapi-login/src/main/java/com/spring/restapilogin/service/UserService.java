package com.spring.restapilogin.service;

import com.spring.restapilogin.model.User;
import com.spring.restapilogin.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
