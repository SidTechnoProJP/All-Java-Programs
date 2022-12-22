package com.majorEvaluation.foodApp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "user",uniqueConstraints = {@UniqueConstraint(columnNames = {"emailId"})})
public class User {
    @Id
    @Column(nullable = false)
    private long phoneNumber;

    @Column(nullable = false)
    @NotBlank(message = "username cannot be empty.")
    private String userName;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$",
            message = "Please provide password at least one uppercase letter,one lowercase letter,one number and " +
                    "one special character with minimum length 6")
    @NotBlank(message = "enter the password")
    private String password;

    @Column(nullable = false,unique = true)
    @Email(message = "enter valid email")
    private String emailId;

}
