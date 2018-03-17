package com.codecool.web.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Tweet {

    private int id;
    private String poster;
    private String post;
    private String timestamp;

    public Tweet(String poster, String post) {
        this.id = new Random().nextInt(100) + 1;
        this.poster = poster;
        this.post = post;
        this.timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Timestamp(System.currentTimeMillis()));
    }

    public Tweet(int id, String poster, String post, String timestamp) {
        this.id = id;
        this.poster = poster;
        this.post = post;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getPoster() {
        return poster;
    }

    public String getPost() {
        return post;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", poster='" + poster + '\'' +
                ", post='" + post + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
