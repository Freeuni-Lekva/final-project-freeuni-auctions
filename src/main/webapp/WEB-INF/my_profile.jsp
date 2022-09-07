<%@ page import="models.User" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="models.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="models.RegularUser" %>
<%@ page import="java.security.NoSuchAlgorithmException" %><%--
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
        <div class="topnav-right">
            <a href="homepage.jsp">Home</a>
            <a href="logout">Log out</a>
        </div>
    </div>
    <% User user = (User) request.getSession().getAttribute(User.ATTRIBUTE); %>
<%--    <% User user = null;--%>
<%--        try {--%>
<%--            user = new RegularUser(9, "sandro", "sandro", "sandro", "sandro");--%>
<%--        } catch (NoSuchAlgorithmException e) {--%>
<%--            throw new RuntimeException(e);--%>
<%--        } %>--%>
    <h1><%=user.getUsername()%></h1>
    <img src="../images/blank-profile-picture.png" alt="profile picture" width="150" height="200">
    <h3><%=user.getEmail()%></h3>
    <h2>Balance: $<%=user.getBalance()%></h2> <%--TODO: user needs balance --%>
    <form action="balance" method="get">
        <input type="submit" value="Update Balance">
    </form>
    <p></p>
    <ul>
        <%
            ProductDAO productDAO = (ProductDAO)application.getAttribute(ProductDAO.ATTRIBUTE);
            List<Product> products = productDAO.getProductsByUser(user.getId());
            for(Product p : products) {

        %>
        <li><a href="product?productId=<%=p.getId()%>"><%=p.getName()%></a></li>
        <%
            }
        %>
    </ul>

</body>
</html>
