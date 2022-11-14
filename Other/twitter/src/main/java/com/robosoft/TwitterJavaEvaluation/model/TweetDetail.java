package com.robosoft.TwitterJavaEvaluation.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
public class TweetDetail {
    private String tweetId;
    private byte[] attachment;
    private String attachmentType;
    private String attachmentName;
    private String attachmentUrl;
}
