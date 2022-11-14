package com.robosoft.TwitterJavaEvaluation.model;

import lombok.Data;
import java.sql.Date;
@Data
public class Users {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private long phoneNumber;
    private Date dateOfBirth;
    private long followers;
    private long following;
    private String about;
    private Boolean verified;
}
