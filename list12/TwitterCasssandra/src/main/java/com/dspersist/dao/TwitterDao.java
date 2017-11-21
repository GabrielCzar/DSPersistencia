package com.dspersist.dao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.sql.Timestamp;
import java.util.UUID;

public class TwitterDao {

    private Connection con = new Connection("localhost");

    // Inserts

    public void insertUser(String user, String pass) {
        String stmt = "insert into twissandra.users (username, password) values (?, ?)";
        PreparedStatement ps = getPs(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(user, pass);
        execute(bs);
    }

    public void insertTweet(String user, String body) {
        String stmt = "insert into twissandra.tweets (tweet_id, username, body) values (now(), ?, ?)";
        PreparedStatement ps = getPs(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(user, body);
        execute(bs);
    }

    public void insertUserLine(String user, UUID tweet_id) {
        String stmt = "insert into twissandra.userline (username, time, tweet_id) values (?, now(), ?)";
        PreparedStatement ps = getPs(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(user, tweet_id);
        execute(bs);
    }

    public void insertTimeline(String user, UUID tweet_id) {
        String stmt = "insert into twissandra.timeline (username, time, tweet_id) values (?, now(), ?)";
        PreparedStatement ps = getPs(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(user, tweet_id);
        execute(bs);
    }

    public void insertFriend(String user, String friend, Timestamp since) {
        String stmt = "insert into twissandra.friends (username, friend, since) values (?, ?, ?)";
        PreparedStatement ps = getPs(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(user, friend, since);
        execute(bs);
    }

    public void insertFollowers(String user, String friend, Timestamp since) {
        String stmt = "insert into twissandra.followers (username, follower, since) values (?, ?, ?)";
        PreparedStatement ps = getPs(stmt);
        BoundStatement bs = new BoundStatement(ps);
        bs.bind(user, friend, since);
        execute(bs);
    }

    // List

    public void listTweet(UUID id) {
        String query = "select * from twissandra.tweets where tweet_id=?";
        ResultSet rs = execute(query, id);
        for (Row row : rs) {
            System.out.println("Body: " + row.getString("body") + ", username: " + row.getString("username"));
        }
        con.close();
    }

    public void listUser(String user) {
        String query = "select * from twissandra.users where username=?";
        ResultSet rs = execute(query, user);
        for (Row row : rs) {
            System.out.println("Username: " + row.getString("username") + ", Password: " + row.getString("password"));
        }
        con.close();
    }

    public void listFriends(String user) {
        String query = "select * from twissandra.friends where username=?";
        ResultSet rs = execute(query, user);
        for (Row row : rs)
            System.out.println("Username: " + row.getString("username") + ", Friend: " + row.getString("friend")
                    + ", Since: " + row.getTimestamp("since"));
        con.close();
    }

    public void listFollowers(String user) {
        String query = "select * from twissandra.followers where username=?";
        ResultSet rs = execute(query, user);
        for (Row row : rs)
            System.out.println("Username: " + row.getString("username") + ", Follower: " + row.getString("follower")
                    + ", Since: " + row.getTimestamp("since"));
        con.close();
    }

    public void listAllTweets() {
        String query = "select * from twissandra.tweets";
        ResultSet rs = con.session.execute(query);
        for (Row row : rs) {
            System.out.println("Body: " + row.getString("body") + ", username: " + row.getString("username"));
        }
        con.close();
    }

    // Extras

    private void execute(BoundStatement bs) {
        con.session.execute(bs);
        con.close();
    }

    private ResultSet execute(String query, Object object) {
        return con.session.execute(query, object);
    }

    private PreparedStatement getPs(String stmt) {
        return con.session.prepare(stmt);
    }
}
