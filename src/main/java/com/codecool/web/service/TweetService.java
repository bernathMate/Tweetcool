package com.codecool.web.service;

import com.codecool.web.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetService {

    private static TweetService tweetService = new TweetService();
    private List<Tweet> tweets;

    public TweetService() {
        this.tweets = new ArrayList<>();
    }

    public static TweetService getInstance() {
        return tweetService;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }
}
