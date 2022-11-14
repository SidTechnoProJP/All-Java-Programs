package com.spring.restlogin.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    @Pattern(regexp = "^([a-z])[?=.*a-z][?=.*0-9][?=.*._]+$", message = "")
    private String userName;
    @NotNull
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "[?=.*a-zA-Z][?=.*0-9][?=.*@!#_]{6,}$", message = "Create a password with minimum 6 characters, at least one lowercase, uppercase, number and special character like : @!#_")
    private String password;
    private LocalDate createdDate;
    @NotNull
    private LocalDate dateOfBirth;
    @Pattern(regexp = "^[0-9]{10}$", message = "Enter a 10-digit Phone Number")
    private long phoneNumber;
}
