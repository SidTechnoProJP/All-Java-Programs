package com.robosoft.TwitterJavaEvaluation.service;

import com.robosoft.TwitterJavaEvaluation.model.Admin;

public interface AdminService {
    String signIn(Admin admin);
    Boolean verifyUsers(String userId,int sId,Boolean bool);
}
