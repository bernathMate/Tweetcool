<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage = "ShowError.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.io.*, java.util.Date, java.util.Enumeration" %>
<%@page import="java.sql.*, java.sql.Timestamp, java.util.Enumeration" %>
<%@page import="java.text.*, java.text.SimpleDateFormat, java.util.Enumeration" %>
<!DOCTYPE html>
<html lang="eng">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tweets</title>
</head>
<body>
    <h1>Tweets</h1>
    <a href="index.html">Go back</a>
    <form action="#">
        Select:
        <select name="select">
            <option value="none">none</option>
            <option value="limit">limit</option>
            <option value="offset">offset</option>
            <option value="poster">poster</option>
            <option value="from">from</option>
        </select>
        Type: <input type="text" name="type">
        <input type="submit" value="Search">
    </form>
    <%
        String select = request.getParameter("select");
        String type = request.getParameter("type");
    %>
    <table>
        <c:set var="s" value="<%=select %>"/>
        <c:set var="t" value="<%=type %>"/>
        <c:if test="${(s == 'poster')}">
            <c:forEach items="${tweets}" var="tweet">
                <c:if test="${(t == tweet.poster)}">
                    <tr>
                        <td><b><c:out value = "${tweet.poster}:"/></b><p></td>
                        <td><c:out value = "${tweet.post}"/><p></td>
                    </tr>
                    <tr>
                        <td><c:out value = "${tweet.timestamp}"/><p></td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${(s == 'limit')}">
            <%
            if (Integer.parseInt(type) == 0 || Integer.parseInt(type) < 0) {
                throw new RuntimeException("Please type a number which is greater then 0, and do not type letters!");
            }
            %>
            <% int endIndex = Integer.parseInt(type)-1; %>
            <c:set var="endIndex" value="<%=endIndex %>"/>
            <c:forEach items="${tweets}" end="${endIndex}" var="tweet">
                <tr>
                    <td><b><c:out value = "${tweet.poster}:"/></b><p></td>
                    <td><c:out value = "${tweet.post}"/><p></td>
                </tr>
                <tr>
                    <td><c:out value = "${tweet.timestamp}"/><p></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${(s == 'offset')}">
            <%
            if (Integer.parseInt(type) == 0 || Integer.parseInt(type) < 0) {
                throw new RuntimeException("Please type a number which is greater then 0, and do not type letters!");
            }
            %>
            <% int startIndex = Integer.parseInt(type)-1; %>
            <c:set var="startIndex" value="<%=startIndex %>"/>
            <c:forEach items="${tweets}" begin="${startIndex}" var="tweet">
                <tr>
                    <td><b><c:out value = "${tweet.poster}:"/></b><p></td>
                    <td><c:out value = "${tweet.post}"/><p></td>
                </tr>
                <tr>
                    <td><c:out value = "${tweet.timestamp}"/><p></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${(s == 'from')}">
            <%
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date inputDate = dateFormat.parse(type);
            Timestamp inputTimestamp = new java.sql.Timestamp(inputDate.getTime());
            %>
            <c:set var="inputTime" value="<%=inputTimestamp %>"/>
            <c:forEach items="${tweets}" var="tweet">
                <c:set var="tweetTime" value="${tweet.timestamp}"/>
                <%
                String tweetTime = (String)pageContext.getAttribute("tweetTime");
                Date tweetDate = dateFormat.parse(tweetTime);
                Timestamp tweetTimestamp = new java.sql.Timestamp(tweetDate.getTime());
                %>
                <c:set var="tweetTimestamp" value="<%=tweetTimestamp %>"/>
                <c:if test="${(tweetTimestamp >= inputTime)}">
                    <tr>
                        <td><b><c:out value = "${tweet.poster}:"/></b><p></td>
                        <td><c:out value = "${tweet.post}"/><p></td>
                    </tr>
                    <tr>
                        <td><c:out value = "${tweet.timestamp}"/><p></td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:if>
        <c:if test="${(s == 'none')}">
            <c:forEach items="${tweets}" var="tweet">
                <tr>
                    <td><b><c:out value = "${tweet.poster}:"/></b><p></td>
                    <td><c:out value = "${tweet.post}"/><p></td>
                </tr>
                <tr>
                    <td><c:out value = "${tweet.timestamp}"/><p></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>
