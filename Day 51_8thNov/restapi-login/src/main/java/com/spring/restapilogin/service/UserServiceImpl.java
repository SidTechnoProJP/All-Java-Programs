package com.spring.restapilogin.service;

import com.spring.restapilogin.model.Role;
import com.spring.restapilogin.model.User;
import com.spring.restapilogin.repository.UserRepository;
import com.spring.restapilogin.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository  userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(), registrationDto.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }
}
