package com.robosoft.TwitterJavaEvaluation.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
public class Comments {
    private String tweetId;
    private String commentId;
    private String userId;
    private Timestamp dateTime;
    private String hashtags;
    private String content;
    private MultipartFile attachment;
    private String attachmentType;
    private String attachmentName;
    private String attachmentUrl;
    private long likes;
}
