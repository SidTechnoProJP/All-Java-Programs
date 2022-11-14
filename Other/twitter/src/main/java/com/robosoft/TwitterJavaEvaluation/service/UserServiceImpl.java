package com.robosoft.TwitterJavaEvaluation.service;

import com.robosoft.TwitterJavaEvaluation.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private int sessionId;
    private String userID;

    @Override
    public Boolean userAuthentication(int sId) {
        if(sId==sessionId) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean signUp(Users users) {
        try {
            String checkQuery = "select user_id from users where user_id = '" + users.getUserId() + "'";
            String userId = jdbcTemplate.queryForObject(checkQuery, new BeanPropertyRowMapper<>(String.class));
        } catch (Exception e) {
            String query = "insert into users(user_id,user_name,email,password,phone_number,date_of_birth) values('"+users.getUserId()+"','"+users.getUserName()+"','"+users.getEmail()+"','"+users.getPassword()+"',"+users.getPhoneNumber()+",'"+users.getDateOfBirth()+"')";
            jdbcTemplate.update(query);
            return true;
        }
        return false;
    }

    @Override
    public int signIn(SignIn signIn) {
        userID = signIn.getUserId();
        try {
            String query = "Select user_id from users where user_id = '" + signIn.getUserId() + "' and password = '" + signIn.getPassword()+ "'";
            String userId = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(String.class));
            sessionId = new Random().nextInt(1,100000);
            return sessionId;
        } catch (Exception e) {
            return 0;
        }
    }

    public String signOut() {
        sessionId=0;
        return "Sign Out Successful";
    }

    @Override
    public Boolean addProfilePhoto(MultipartFile file,int sId) {
        if (userAuthentication(sId)) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String downloadURL = "";
            try {
                if (fileName.contains("..")) {
                    throw new Exception("File Name Contains Invalid Sequence " + fileName);
                }
                downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/profile-photo").toUriString();
                String query = "insert into profile_photo values(?,?,?,?,?)";
                String finalDownloadURL = downloadURL;
                jdbcTemplate.update(query, (preparedStatement) -> {
                    preparedStatement.setString(1, userID);
                    preparedStatement.setString(2, fileName);
                    preparedStatement.setString(3, file.getContentType());
                    try {
                        preparedStatement.setBinaryStream(4, new ByteArrayInputStream(file.getBytes()), file.getBytes().length);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    preparedStatement.setString(5, finalDownloadURL);
                });
                return true;
            } catch (Exception e) {
                System.out.println("Could not save file " + fileName);
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteProfilePhoto(int sId) {
        if(userAuthentication(sId)) {
            try {
                String query = "delete from profile_photo where user_id = '" + userID + "'";
                jdbcTemplate.update(query);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean updateProfilePhoto(int sId, MultipartFile file) {
        try {
            deleteProfilePhoto(sId);
            addProfilePhoto(file, sId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ProfilePhoto getProfilePhoto() {
            String query = "select user_id,file_name,file_type,profile_pic from profile_photo where user_id = '" + userID + "'";
            List<ProfilePhoto> profilePhotos = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ProfilePhoto.class));
            int size = profilePhotos.size();
            if (size == 0) {
                return null;
            }
            return profilePhotos.get(0);
        }


    @Override
    public MyDetails myDetails(int sId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.email,users.phone_number,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users inner join profile_photo using(user_id) where users.user_id='"+userID+"'";
            List<MyDetails> myDetails = jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MyDetails.class));
            int size = myDetails.size();
            if (size == 0) {
                return null;
            }
            return myDetails.get(0);
        }
        return null;
    }

    @Override
    public UserDetails getUSerDetails(int sId, String userId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users inner join profile_photo using(user_id) where users.user_id='"+userId+"'";
            List<UserDetails> userDetails = jdbcTemplate.query(query,new BeanPropertyRowMapper<>(UserDetails.class));
            int size = userDetails.size();
            if (size == 0) {
                return null;
            }
            return userDetails.get(0);
        }
        return null;
    }

    @Override
    public Boolean addBio(String bio,int sId) {
        if(userAuthentication(sId)) {
            try {
                String query = "update users set about='"+bio+"' where user_id='"+userID+"'";
                jdbcTemplate.update(query);
                System.out.println("Im");
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteBio(int sId) {
        if(userAuthentication(sId)) {
            try {
                String query = "update users set about = '' where user_id='"+userID+"'";
                jdbcTemplate.update(query);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean follow(int sId, String userId) {
        if(userAuthentication(sId)) {
           String checkQuery = "select user_id from users where user_id = '"+userId+"'";
           List<String> userIds = jdbcTemplate.queryForList(checkQuery, String.class);
           int size = userIds.size();
           if(size!=0) {
               String query = "insert into followers values('"+userID+"','"+userId+"')";
               jdbcTemplate.update(query);
               String selQuery = "select following from users where user_id = '"+userID+"'";
               int count = jdbcTemplate.queryForObject(selQuery, Integer.class);
               count=count+1;
               String seleQuery = "select followers from users where user_id = '"+userId+"'";
               int followCount = jdbcTemplate.queryForObject(seleQuery, Integer.class);
               followCount=followCount+1;
               String updateQuery = "update users set following = "+count+" where user_id = '"+userID+"'";
               jdbcTemplate.update(updateQuery);
               String updateFollowQuery = "update users set followers = "+followCount+" where user_id = '"+userId+"'";
               jdbcTemplate.update(updateFollowQuery);
               return true;
           }
           return false;
        }
        return false;
    }

    @Override
    public Boolean unfollow(int sId, String userId) {
        if(userAuthentication(sId)) {
            String checkQuery = "select user_id from users where user_id = '"+userId+"'";
            List<String> userIds = jdbcTemplate.queryForList(checkQuery, String.class);
            int size = userIds.size();
            if(size!=0) {
                String query = "delete from followers where userId = '"+userId+"'";
                jdbcTemplate.update(query);
                String selQuery = "select following from users where user_id = '"+userID+"'";
                int count = jdbcTemplate.queryForObject(selQuery, Integer.class);
                count=count-1;
                String seleQuery = "select followers from users where user_id = '"+userId+"'";
                int followCount = jdbcTemplate.queryForObject(seleQuery, Integer.class);
                followCount=followCount-1;
                String updateQuery = "update users set following = "+count+" where user_id = '"+userID+"'";
                jdbcTemplate.update(updateQuery);
                String updateFollowQuery = "update users set followers = "+followCount+" where user_id = '"+userId+"'";
                jdbcTemplate.update(updateFollowQuery);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Boolean postTweet(Tweets tweet,int sId) {
        if (userAuthentication(sId)) {
            if(tweet.getAttachment()==null) {
                String query = "insert into tweets(tweet_id,user_id,hashtags,content,attachment,attachment_type,attachment_name,attachment_url) values(?,?,?,?,?,?,?,?)";
                jdbcTemplate.update(query, (preparedStatement) -> {
                            preparedStatement.setString(1, tweet.getTweetId());
                            preparedStatement.setString(2, userID);
                            preparedStatement.setString(3, tweet.getHashtags());
                            preparedStatement.setString(4, tweet.getContent());
                });
                return true;
            }
            String fileName = StringUtils.cleanPath(tweet.getAttachment().getOriginalFilename());
            String downloadURL = "";
            try {
                if (fileName.contains("..")) {
                    throw new Exception("File Name Contains Invalid Sequence " + fileName);
                }
                downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/my-tweet-attachment/").path(tweet.getTweetId()).toUriString();
                String query = "insert into tweets(tweet_id,user_id,hashtags,content,attachment,attachment_type,attachment_name,attachment_url) values(?,?,?,?,?,?,?,?)";
                String finalDownloadURL = downloadURL;
                jdbcTemplate.update(query, (preparedStatement) -> {
                    preparedStatement.setString(1, tweet.getTweetId());
                    preparedStatement.setString(2, userID);
                    preparedStatement.setString(3, tweet.getHashtags());
                    preparedStatement.setString(4, tweet.getContent());
                    try {
                        preparedStatement.setBinaryStream(5, new ByteArrayInputStream(tweet.getAttachment().getBytes()), tweet.getAttachment().getBytes().length);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    preparedStatement.setString(6, tweet.getAttachment().getContentType());
                    preparedStatement.setString(7, fileName);
                    preparedStatement.setString(8, finalDownloadURL);
                });
                return true;
            } catch (Exception e) {
                System.out.println("Could not save file " + fileName);
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public TweetDetail getMyTweetAttachment(String tweetId) {
            String query = "select tweet_id,attachment,attachment_type,attachment_name,attachment_url from tweets where tweet_id = '"+tweetId+"'";
            List<TweetDetail> tweetDetails = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(TweetDetail.class));
            int size = tweetDetails.size();
            if (size == 0) {
                return null;
            }
            return tweetDetails.get(0);
        }

    @Override
    public List<MyTweets> getMyTweets(int sId) {
        if(userAuthentication(sId)) {
            String query = "select tweet_id,user_id,date_time,hashtags,content,attachment_type,attachment_name,attachment_url,likes from tweets where user_id = '"+userID+"'";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MyTweets.class));
        }
        return null;
    }

    @Override
    public MyTweets getMyTweet(int sId, String tweetId) {
        if(userAuthentication(sId)) {
            String query = "select tweet_id,user_id,date_time,hashtags,content,attachment_type,attachment_name,attachment_url,likes from tweets where tweet_id = '"+tweetId+"'";
            List<MyTweets> myTweets = jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MyTweets.class));
            int size = myTweets.size();
            if(size==0) {
                return null;
            }
            return myTweets.get(0);
        }
        return null;
    }

    @Override
    public Boolean postComment(Comments comment,int sId) {
        if(userAuthentication(sId)) {
            if(comment.getAttachment()==null) {
                String query = "insert into comments(tweet_id,comment_id,user_id,hashtags,content) values(?,?,?,?,?)";
                jdbcTemplate.update(query, (preparedStatement) -> {
                    preparedStatement.setString(1, comment.getTweetId());
                    preparedStatement.setString(2, comment.getCommentId());
                    preparedStatement.setString(3, userID);
                    preparedStatement.setString(4, comment.getHashtags());
                    preparedStatement.setString(5, comment.getContent());
                });
                return true;
            }
            String fileName = StringUtils.cleanPath(comment.getAttachment().getOriginalFilename());
            String downloadURL = "";
            try {
                if (fileName.contains("..")) {
                    throw new Exception("File Name Contains Invalid Sequence " + fileName);
                }
                downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/my-comment-attachment/").path(comment.getCommentId()).toUriString();
                String query = "insert into comments(tweet_id,comment_id,user_id,hashtags,content,attachment,attachment_type,attachment_name,attachment_url) values(?,?,?,?,?,?,?,?,?)";
                String finalDownloadURL = downloadURL;
                jdbcTemplate.update(query, (preparedStatement) -> {
                    preparedStatement.setString(1, comment.getTweetId());
                    preparedStatement.setString(2, comment.getCommentId());
                    preparedStatement.setString(3, userID);
                    preparedStatement.setString(4, comment.getHashtags());
                    preparedStatement.setString(5, comment.getContent());
                    try {
                        preparedStatement.setBinaryStream(6, new ByteArrayInputStream(comment.getAttachment().getBytes()), comment.getAttachment().getBytes().length);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    preparedStatement.setString(7, comment.getAttachment().getContentType());
                    preparedStatement.setString(8, fileName);
                    preparedStatement.setString(9, finalDownloadURL);
                });
                return true;


            } catch (Exception e) {
                System.out.println("Could not save file " + fileName);
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public CommentDetail getMyCommentAttachment(String commentId) {
        String query = "select tweet_id,comment_id,attachment,attachment_type,attachment_name,attachment_url from comments where comment_id = '"+commentId+"'";
        List<CommentDetail> commentDetails = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CommentDetail.class));
        int size = commentDetails.size();
        if (size == 0) {
            return null;
        }
        return commentDetails.get(0);
    }

    @Override
    public List<MyComments> getMyComments(int sId) {
        if(userAuthentication(sId)) {
            String query = "select comment_id,tweet_id,user_id,date_time,hashtags,content,attachment_type,attachment_name,attachment_url,likes from comments where user_id = '"+userID+"'";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MyComments.class));
        }
        return null;
    }

    @Override
    public MyComments getComment(int sId, String commentId) {
        if(userAuthentication(sId)) {
            String query = "select comment_id,tweet_id,user_id,date_time,hashtags,content,attachment_type,attachment_name,attachment_url,likes from tweets where tweet_id = '"+commentId+"'";
            List<MyComments> myComments = jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MyComments.class));
            int size = myComments.size();
            if(size==0) {
                return null;
            }
            return myComments.get(0);
        }
        return null;
    }

    @Override
    public Boolean giveLikeToTweet(int sId, String tweetId) {
        if(userAuthentication(sId)) {
            try {
                String query = "insert into likes values('" + userID + "','" + tweetId + "')";
                jdbcTemplate.update(query);
                String selectQuery = "select likes from tweets where tweet_id = '" + tweetId + "'";
                Long likes = jdbcTemplate.queryForObject(selectQuery, Long.class);
                likes = likes + 1;
                String updateQuery = "update tweets set likes = " + likes + " where tweet_id = '" + tweetId + "'";
                jdbcTemplate.update(updateQuery);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean removeLikeFromTweet(int sId, String tweetId) {
        if(userAuthentication(sId)) {
            try {
                String query = "delete from likes where user_id = '"+userID+"' and tweet_id = '"+tweetId+"'";
                jdbcTemplate.update(query);
                String selectQuery = "select likes from tweets where tweet_id = '" + tweetId + "'";
                Long likes = jdbcTemplate.queryForObject(selectQuery, Long.class);
                likes = likes - 1;
                String updateQuery = "update tweets set likes = " + likes + " where tweet_id = '" + tweetId + "'";
                jdbcTemplate.update(updateQuery);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean giveLikeToComment(int sId, String commentId) {
        if(userAuthentication(sId)) {
            try {
                String query = "insert into comment_likes values('" + userID + "','" + commentId + "')";
                jdbcTemplate.update(query);
                String selectQuery = "select likes from comments where comment_id = '" + commentId + "'";
                Long likes = jdbcTemplate.queryForObject(selectQuery, Long.class);
                likes = likes + 1;
                String updateQuery = "update comments set likes = " + likes + " where comment_id = '" + commentId + "'";
                jdbcTemplate.update(updateQuery);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean removeLikeFromComment(int sId, String commentId) {
        if(userAuthentication(sId)) {
            try {
                String query = "delete from comment_likes where user_id = '"+userID+"' and comment_id = '"+commentId+"'";
                jdbcTemplate.update(query);
                String selectQuery = "select likes from comments where comment_id = '" + commentId + "'";
                Long likes = jdbcTemplate.queryForObject(selectQuery, Long.class);
                likes = likes - 1;
                String updateQuery = "update comments set likes = " + likes + " where comment_id = '" + commentId + "'";
                jdbcTemplate.update(updateQuery);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public List<UserDetails> myFollowing(int sId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users inner join followers on users.user_id = followers.follower_id left join profile_photo on profile_photo.user_id = users.user_id where followers.user_id = '"+userID+"'";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(UserDetails.class));
        }
        return null;
    }

    @Override
    public List<UserDetails> Followings(int sId, String userId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users inner join followers on users.user_id = followers.follower_id left join profile_photo on profile_photo.user_id = users.user_id where followers.user_id = '"+userId+"'";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(UserDetails.class));
        }
        return null;
    }

    @Override
    public List<UserDetails> myFollowers(int sId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users inner join followers on users.user_id = followers.follower_id left join profile_photo on profile_photo.user_id = users.user_id where followers.follower_id = '" + userID + "'";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(UserDetails.class));
        }
        return null;
    }

    @Override
    public List<UserDetails> Followers(int sId, String userId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users inner join followers on users.user_id = followers.follower_id left join profile_photo on profile_photo.user_id = users.user_id where followers.follower_id = '"+userId+"'";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(UserDetails.class));
        }
        return null;
    }

    @Override
    public List<MyTweets> homePage(int sId) {
        if(userAuthentication(sId)) {
            String query = "select tweets.tweet_id,tweets.user_id,tweets.date_time,tweets.hashtags,tweets.content,tweets.attachment_type,tweets.attachment_name,tweets.attachment_url,tweets.likes from tweets inner join followers on followers.follower_id = tweets.user_id where followers.user_id = '"+userID+"' order by tweets.date_time asc limit 10";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(MyTweets.class));
        }
        return null;
    }

    @Override
    public List<UserDetails> suggestionPage(int sId) {
        if(userAuthentication(sId)) {
            String query = "select users.user_id,users.user_name,users.date_of_birth,profile_photo.download_url,users.followers,users.following,users.about,users.verified from users left join profile_photo using(user_id) order by followers asc limit 10";
            return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(UserDetails.class));
        }
        return null;
    }

}
