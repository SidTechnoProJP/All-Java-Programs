package com.robosoft.TwitterJavaEvaluation.model;

import lombok.Data;

@Data
public class CommentDetail {
    private String tweetId;
    private String commentId;
    private byte[] attachment;
    private String attachmentType;
    private String attachmentName;
    private String attachmentUrl;
    private long likes;
}
