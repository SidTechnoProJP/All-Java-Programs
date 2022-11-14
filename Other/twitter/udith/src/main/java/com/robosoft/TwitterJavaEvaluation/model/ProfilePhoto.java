package com.robosoft.TwitterJavaEvaluation.model;

import lombok.*;

@Data
public class ProfilePhoto {
    private String userId;
    private String fileName;
    private String fileType;
    private byte[] profilePic;
    private String downloadURL;
}
