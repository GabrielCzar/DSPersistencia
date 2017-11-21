package com.dspersist;

import com.dspersist.queries.TwitterQueries;

public class App {
    public static void main(String[] args) {
        TwitterQueries queries = new TwitterQueries();
        // Consultas
        queries.getAllTweets();
    }
}
