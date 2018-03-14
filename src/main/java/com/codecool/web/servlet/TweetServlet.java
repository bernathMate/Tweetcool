package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tweetServlet")
public class TweetServlet extends HttpServlet {

    private List<Tweet> tweets = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tweets.add(new Tweet(request.getParameter("poster"), request.getParameter("post")));
        System.out.println("New tweet is added!");

        /*
        PrintWriter out = response.getWriter();
        String htmlResponse = "<p>" + tweets.get(0) + "</p>";
        out.println(htmlResponse);
        */

        saveTweetsToXml();
    }

    private void saveTweetsToXml() {
        //save the tweets to xml file
    }
}
