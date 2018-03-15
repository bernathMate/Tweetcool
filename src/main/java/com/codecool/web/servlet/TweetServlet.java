package com.codecool.web.servlet;

import com.codecool.web.service.TweetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tweets")
public class TweetServlet extends HttpServlet {

    private final TweetService tweetService = TweetService.getInstance();

    public TweetService getTweetService() {
        return tweetService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tweets", tweetService.getTweets());
        req.getRequestDispatcher("tweets.jsp").forward(req, resp);
    }
}
