package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/newTweetServlet")
public class NewTweetServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String poster = req.getParameter("poster");
        String post = req.getParameter("post");

        if (poster.equals("") || post.equals("")) {
            PrintWriter out = res.getWriter();
            String htmlResponse = "<h1>ERROR: Please type your name, and your post!";
            out.println(htmlResponse);
            return;
        }

        new TweetServlet().getTweetService().getTweets().add(new Tweet(poster, post));
        System.out.println("New post is added!");
        res.sendRedirect("index.html");
    }
}
