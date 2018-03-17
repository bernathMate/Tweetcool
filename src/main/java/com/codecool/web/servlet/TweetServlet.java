package com.codecool.web.servlet;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.TweetService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@WebServlet("/tweets")
public class TweetServlet extends HttpServlet {

    private final TweetService tweetService = TweetService.getInstance();
    //private String filePath = "resources/tweets.xml";

    public TweetService getTweetService() {
        return tweetService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String contextPath = req.getSession().getServletContext().getRealPath("/" + "tweets.xml");
        //loadTweetsFromXml(contextPath);
        req.setAttribute("tweets", tweetService.getTweets());
        req.getRequestDispatcher("tweets.jsp").forward(req, resp);
    }

    private void loadTweetsFromXml(String filePath) {
        try {
            Document doc = createDoc(filePath);
            doc.getDocumentElement().normalize();
            NodeList tweets = doc.getElementsByTagName("Tweet");

            for (int i = 0; i < tweets.getLength(); i++) {
                Node tweet = tweets.item(i);
                if (tweet.getNodeType() == Node.ELEMENT_NODE) {
                    Element tweetElement = (Element) tweet;
                    String id = tweetElement.getAttribute("id");
                    String poster = tweetElement.getElementsByTagName("Poster").item(0).getTextContent();
                    String post = tweetElement.getElementsByTagName("Post").item(0).getTextContent();
                    String time = tweetElement.getElementsByTagName("Time").item(0).getTextContent();
                    //tweetService.getTweets().add(new Tweet(Integer.parseInt(id), poster, post, time));
                    new TweetService().getTweets().add(new Tweet(Integer.parseInt(id), poster, post, time));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private Document createDoc(String filename) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        return doc;
    }


}
