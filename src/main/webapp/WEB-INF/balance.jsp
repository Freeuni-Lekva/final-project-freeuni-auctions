<%@ page import="models.User" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="models.RegularUser" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 07.09.2022
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Balance</title>
    <style>
        body {
            background-image: url("../images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }
        .no-background {
            background-image: url("../images/bg.jpg");
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #aa0473;
            color: white;
        }

        .topnav-right {
            float: right;
        }
    </style>
</head>
<body>
    <div class="topnav">
        <a href="profile">My Profile</a>
        <div class="topnav-right">
            <a href="../homepage.jsp">Home</a>
            <a href="logout">Log out</a>
        </div>
    </div>
    <% User user = (User)(request.getSession().getAttribute(User.ATTRIBUTE)); %>
    <h1>Your balance is: $<%=user.getBalance()%></h1><br>
    <form method="post" action="balance">
        <label for="amount">Amount: </label>
        <input type="number" id="amount" name="amount">
        <input type="submit" value="update balance">
    </form>
</body>
</html>
