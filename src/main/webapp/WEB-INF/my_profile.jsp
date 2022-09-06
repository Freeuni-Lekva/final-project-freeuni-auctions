<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 04.09.2022
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Profile</title>
    <style>
        body {
            background-image: url("../images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }
        .no-background {
            background-image: url("../images/bg.jpg");
        }
    </style>
</head>
<body>
    <% User user = (User)request.getAttribute(User.ATTRIBUTE); %>
    <h1><%=user.getUsername()%></h1>
    <img src="../images/blank-profile-picture.png" alt="profile picture" width="150" height="200">
    <h3><%=user.getEmail()%></h3>
    <%--<h2>Balance: $<%=user.getBalance()%></h2> --%>
    <p></p>
</body>
</html>
