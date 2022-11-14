package com.robosoft.TwitterJavaEvaluation.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class MyComments {
    private String commentId;
    private String tweetId;
    private String userId;
    private Timestamp dateTime;
    private String hashtags;
    private String content;
    private String attachmentType;
    private String attachmentName;
    private String attachmentUrl;
    private Long likes;
}
