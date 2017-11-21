package com.dspersist.queries;

import com.dspersist.dao.TwitterDao;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class TwitterQueries {
    TwitterDao dao = new TwitterDao();

    public void insertFriend(String user, String friend) {
        dao.insertFriend(user, friend, new Timestamp(System.currentTimeMillis()));
    }

    public void insertTweet(String user, String content){
        dao.insertTweet(user, content);
    }

    public void insertFollower(String user, String follower) {
        dao.insertFollowers("Leandro", "Iarlen", new Timestamp(System.currentTimeMillis()));
    }

    public void insertUser(String user, String pass) {
        dao.insertUser(user, pass);
    }

    public void getUser(String user) {
        dao.listUser(user);
    }

    public void getFriends (String user) {
        dao.listFriends(user);
    }

    public void getTweets(String uuid){
        dao.listTweet(UUID.fromString(uuid));
    }

    public void getFollowers(String user) {
        dao.listFollowers(user);
    }

    public void getAllTweets() {
        dao.listAllTweets();
    }
}
