package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
            String htmlResponse = "<a href=\"index.html\"/>Go back</a>";
            htmlResponse += "<h1>ERROR: Please type your name, and your post!";
            out.println(htmlResponse);
            return;
        }

        try {
            String contextPath = req.getSession().getServletContext().getRealPath("/" + "tweets.xml");
            saveTweetToXml(new Tweet(poster, post), contextPath);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        new TweetServlet().getTweetService().getTweets().add(new Tweet(poster, post));
        System.out.println("New post is added!");
        res.sendRedirect("index.html");
    }

    private void saveTweetToXml(Tweet tweet, String contextPath) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(contextPath);
        Element root = document.getDocumentElement();

        Element newTweet = document.createElement("Tweet");
        //newTweet.setAttribute("id", String.valueOf(tweet.getId()));

        Element poster = document.createElement("Poster");
        poster.appendChild(document.createTextNode(tweet.getPoster()));
        newTweet.appendChild(poster);

        Element post = document.createElement("Post");
        post.appendChild(document.createTextNode(tweet.getPost()));
        newTweet.appendChild(post);

        Element time = document.createElement("Time");
        time.appendChild(document.createTextNode(tweet.getTimestamp()));
        newTweet.appendChild(time);

        root.appendChild(newTweet);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(contextPath);
        transformer.transform(source, result);
    }
}
