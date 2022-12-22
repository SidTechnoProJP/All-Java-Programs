package com.evaluation.foodapp.model;

import lombok.Data;

@Data
public class PasswordResetRequestDTO {
    private String phoneNumber; //destination
    private String username;
    private String oneTimePassword;
}
