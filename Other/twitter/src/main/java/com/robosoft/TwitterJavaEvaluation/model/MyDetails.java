package com.robosoft.TwitterJavaEvaluation.model;

import lombok.*;

import java.sql.Date;
@Data
public class MyDetails {
    private String userId;
    private String userName;
    private String email;
    private long phoneNumber;
    private Date dateOfBirth;
    private String downloadUrl;
    private long followers;
    private long following;
    private String about;
    private Boolean verified;
}
