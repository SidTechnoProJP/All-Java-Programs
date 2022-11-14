package com.robosoft.TwitterJavaEvaluation.service;


import com.robosoft.TwitterJavaEvaluation.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

public interface UserService {
     Boolean userAuthentication(int sId);
     Boolean signUp(Users users);
     int signIn(SignIn signIn);
     String signOut();
     Boolean addProfilePhoto(MultipartFile file, int sId);
     Boolean deleteProfilePhoto(int sId);
     Boolean updateProfilePhoto(int sId, MultipartFile file);
     ProfilePhoto getProfilePhoto();
     MyDetails myDetails(int sId);
     UserDetails getUSerDetails(int sId, String userId);
     Boolean addBio(String bio, int sId);
     Boolean deleteBio(int sId);
     Boolean follow(int sId, String userId);
     Boolean unfollow(int sId, String userId);
     Boolean postTweet(Tweets tweets,int sId);
     TweetDetail getMyTweetAttachment(String tweetId);
     List<MyTweets> getMyTweets(int sId);
     MyTweets getMyTweet(int sId,String tweet_id);
     Boolean postComment(Comments comment,int sId);
     CommentDetail getMyCommentAttachment(String commentId);
     List<MyComments> getMyComments(int sId);
     MyComments getComment(int sId,String commentId);
     Boolean giveLikeToTweet(int sId,String tweetId);
     Boolean removeLikeFromTweet(int sId,String tweetId);
     Boolean giveLikeToComment(int sId,String commentId);
     Boolean removeLikeFromComment(int sId,String commentId);
     List<UserDetails> myFollowing(int sId);
     List<UserDetails> Followings(int sId,String userId);
     List<UserDetails> myFollowers(int sId);
     List<UserDetails> Followers(int sId,String userId);
     List<MyTweets> homePage(int sId);
     List<UserDetails> suggestionPage(int sId);
}

