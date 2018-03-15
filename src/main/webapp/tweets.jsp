<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
