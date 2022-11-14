package com.robosoft.TwitterJavaEvaluation.controller;

import com.robosoft.TwitterJavaEvaluation.model.*;
import com.robosoft.TwitterJavaEvaluation.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;
    //User Registration
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody Users users) {
        if(userService.signUp(users)) {
            return ResponseEntity.status(HttpStatus.OK).body("Registration Successful");
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User ID Already Taken");
    }
    //User Sign In
    @GetMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignIn signIn) {
        int res = userService.signIn(signIn);
        if(res==0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Credentials");
        }
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(String.valueOf(res));
    }
    //Suggestion Page for user after first sign in
    @GetMapping("/suggestion-page/{sId}")
    public ResponseEntity<List<UserDetails>> suggestionPage(@PathVariable int sId) {
        List<UserDetails> userDetails = userService.suggestionPage(sId);
        if(userDetails==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userDetails);
    }
    //Follow
    @PostMapping("/follow/{sId}/{userId}")
    public ResponseEntity<String> follow(@PathVariable int sId,@PathVariable String userId) {
        if(userService.follow(sId,userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("You started following "+userId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Denied");
    }
    //Home Page
    @GetMapping("/home/{sId}")
    public ResponseEntity<List<MyTweets>> homePage(@PathVariable int sId) {
        List<MyTweets> tweets = userService.homePage(sId);
        if(tweets==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(tweets);
    }
    //User Sign Out
    @GetMapping("/sign-out")
    public ResponseEntity<String> signIn() {
        String res = userService.signOut();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    //Add User Profile Photo
    @PostMapping("/post-profile-photo/{sId}")
    public ResponseEntity<String> addProfilePhoto(@RequestParam("file")MultipartFile file,@PathVariable int sId) {
        if(userService.addProfilePhoto(file, sId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Profile Photo Added.");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied OR Could not save file");
    }
    //View Users Profile Photo
    @GetMapping("/profile-photo")
    public ResponseEntity<Resource> getProfilePhoto() {
        ProfilePhoto profilePhoto = userService.getProfilePhoto();
        if(profilePhoto==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(profilePhoto.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION,"profilePhoto; fileName=\""+profilePhoto.getFileName()+"\"").body(new ByteArrayResource(profilePhoto.getProfilePic()));
    }
    //View Users Details
    @GetMapping("/my-details/{sId}")
    public ResponseEntity<MyDetails> myDetails(@PathVariable int sId ) {
        MyDetails userDetails = userService.myDetails(sId);
        if(userDetails==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userDetails);
    }
    //View Other User Details
    @GetMapping("/user-details/{sId}/{userId}")
    public ResponseEntity<UserDetails> myDetails(@PathVariable int sId, @PathVariable String userId) {
        UserDetails userDetails = userService.getUSerDetails(sId,userId);
        if(userDetails==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(userDetails);
    }
    //Delete Profile Photo
    @DeleteMapping("/delete-profile-photo/{sId}")
    public ResponseEntity<String> deleteProfilePhoto(@PathVariable int sId) {
        if(userService.deleteProfilePhoto(sId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Profile Photo Deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Task Denied");
    }
    //Update Profile Photo
    @PutMapping("/update-profile-photo/{sId}")
    public ResponseEntity<String> updateProfilePhoto(@RequestParam("file")MultipartFile file, @PathVariable int sId) {
        if(userService.updateProfilePhoto(sId, file)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Profile Photo Updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Task Denied");
    }
    //Add Bio
    @PostMapping("/about/{sId}")
    public ResponseEntity<String> addBio(@PathVariable int sId, @RequestBody String about) {
        if(userService.addBio(about, sId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bio added successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Task Denied !");
    }
    //Delete Bio
    @DeleteMapping("/delete-about/{sId}")
    public ResponseEntity<String> deleteBio(@PathVariable int sId) {
        if(userService.deleteBio(sId)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bio Deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Task Denied");
    }
    //Unfollow Users
    @PostMapping("/unfollow/{sId}/{userId}")
    public ResponseEntity<String> unfollow(@PathVariable int sId,@PathVariable String userId) {
        if(userService.unfollow(sId,userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("You have unfollowed "+userId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Denied");
    }
    //Post Tweet
    @PostMapping("/tweet/{sId}")
    public ResponseEntity<String> tweet(@PathVariable int sId, @ModelAttribute Tweets tweets) {
        if(userService.postTweet(tweets, sId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Tweet Posted");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Failed");
    }
    //View Tweet Attachment
    @GetMapping("/my-tweet-attachment/{tweetId}")
    public ResponseEntity<Resource> myTweetAttachment( @PathVariable String tweetId) {
        TweetDetail tweetDetail = userService.getMyTweetAttachment(tweetId);
        if(tweetDetail==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(tweetDetail.getAttachmentType())).header(HttpHeaders.CONTENT_DISPOSITION,"tweetDetail; fileName=\""+tweetDetail.getAttachmentName()+"\"").body(new ByteArrayResource(tweetDetail.getAttachment()));
    }
    //View Tweets
    @GetMapping("/get-tweets/{sId}")
    public ResponseEntity<List<MyTweets>> myTweets(@PathVariable int sId) {
        List<MyTweets> myTweets = userService.getMyTweets(sId);
        if(myTweets==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(myTweets);
    }
    //View Tweet by Tweet ID
    @GetMapping("/get-tweet/{sId}")
    public ResponseEntity<MyTweets> myTweet(@PathVariable int sId,@RequestBody String tweetId) {
        MyTweets myTweet = userService.getMyTweet(sId, tweetId);
        if(myTweet==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(myTweet);
    }
    //post Comment
    @PostMapping("/comment/{sId}")
    public ResponseEntity<String> comment(@PathVariable int sId, @ModelAttribute Comments comment) {
        if(userService.postComment(comment, sId)) {
            return ResponseEntity.status(HttpStatus.OK).body("Comment Posted");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Failed");
    }
    //View Comment Attachment
    @GetMapping("/my-comment-attachment/{commentId}")
    public ResponseEntity<Resource> myCommentAttachment( @PathVariable String commentId) {
        CommentDetail commentDetail = userService.getMyCommentAttachment(commentId);
        if(commentDetail==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(commentDetail.getAttachmentType())).header(HttpHeaders.CONTENT_DISPOSITION,"commentDetail; fileName=\""+commentDetail.getAttachmentName()+"\"").body(new ByteArrayResource(commentDetail.getAttachment()));
    }
    //view comments
    @GetMapping("/get-comments/{sId}")
    public ResponseEntity<List<MyComments>> myComments(@PathVariable int sId) {
        List<MyComments> myComments = userService.getMyComments(sId);
        if(myComments==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(myComments);
    }
    //View comment by comment ID
    @GetMapping("/get-comment/{sId}")
    public ResponseEntity<MyComments> myComment(@PathVariable int sId,@RequestBody String commentId) {
        MyComments myComments = userService.getComment(sId, commentId);
        if(myComments==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(myComments);
    }
    //Like Tweet
    @PostMapping("/like-tweet/{sId}")
    public ResponseEntity<String> likeTweet(@PathVariable int sId,@RequestBody String tweetId) {
        if(userService.giveLikeToTweet(sId,tweetId)) {
            return ResponseEntity.status(HttpStatus.OK).body("You liked this tweet");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Failed");
    }
    //Dislike Tweet
    @PostMapping("/dislike-tweet/{sId}")
    public ResponseEntity<String> dislikeTweet(@PathVariable int sId,@RequestBody String tweetId) {
        if(userService.removeLikeFromTweet(sId, tweetId)) {
            return ResponseEntity.status(HttpStatus.OK).body("You disliked this tweet");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Failed");
    }
    //Like Comment
    @PostMapping("/like-comment/{sId}")
    public ResponseEntity<String> likeComment(@PathVariable int sId,@RequestBody String commentId) {
        if(userService.giveLikeToComment(sId,commentId)) {
            return ResponseEntity.status(HttpStatus.OK).body("You liked this comment");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Failed");
    }
    //Dislike comment
    @PostMapping("/dislike-comment/{sId}")
    public ResponseEntity<String> dislikeComment(@PathVariable int sId,@RequestBody String commentId) {
        if(userService.removeLikeFromComment(sId, commentId)) {
            return ResponseEntity.status(HttpStatus.OK).body("You disliked this comment");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task Failed");
    }
    //View my followings
    @GetMapping("/my-following/{sId}")
    public ResponseEntity<List<UserDetails>> myFollowing(@PathVariable int sId) {
        List<UserDetails> followings = userService.myFollowing(sId);
        if(followings==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(followings);
    }
    //View Followings
    @GetMapping("/followings/{sId}/{userId}")
    public ResponseEntity<List<UserDetails>> followings(@PathVariable int sId,@PathVariable String userId) {
        List<UserDetails> followings = userService.Followings(sId,userId);
        if(followings==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(followings);
    }
    //View my followers
    @GetMapping("/my-followers/{sId}")
    public ResponseEntity<List<UserDetails>> myFollowers(@PathVariable int sId) {
        List<UserDetails> followers = userService.myFollowers(sId);
        if(followers==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(followers);
    }
    //View Followers
    @GetMapping("/followers/{sId}/{userId}")
    public ResponseEntity<List<UserDetails>> followers(@PathVariable int sId,@PathVariable String userId) {
        List<UserDetails> followers = userService.Followers(sId,userId);
        if(followers==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(followers);
    }

}
