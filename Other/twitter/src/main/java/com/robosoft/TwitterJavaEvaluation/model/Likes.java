package com.robosoft.TwitterJavaEvaluation.model;

import lombok.Data;
import java.math.BigInteger;
@Data
public class Likes {
    private String userId;
    private BigInteger tweetId;
}
