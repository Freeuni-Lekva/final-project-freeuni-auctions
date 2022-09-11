<%@ page import="dao.ProductDAO" %>
<%@ page import="models.Product" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="dao.CategoryDAO" %><%--
  Created by IntelliJ IDEA.
  User: nnayeno
  Date: 9/6/22
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Product prod = (Product)request.getAttribute(Product.ATTRIBUTE);
    UserDAO users = (UserDAO) application.getAttribute(UserDAO.ATTRIBUTE);
    CategoryDAO categories = (CategoryDAO) application.getAttribute(CategoryDAO.ATTRIBUTE);
%>
<html>
<head>
    <title> <%=prod.getName()%></title>
    <style>
        body {
            background-image: url("images/bg.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh;
        }
        div button {
            float:right
        }
    </style>
</head>
<body>
    <a href="homepage.jsp" style="float: right; margin-right: 10px">homepage</a>
    <h1 style="text-align: left; color: #A67079; font-family: 'Times New Roman',serif"> <%=prod.getName()%> </h1>
    <img src="<%=prod.getImage()%>"  alt="<%=prod.getName()%>"  width="300" height="300" style="float: left; margin-right: 15px;"/>
    <p>Date Posted: <%=prod.getDatePosted()%> End Date: <%=prod.getEndDate()%></p>
    <p>category: <%=categories.getFromID(prod.getCategoryId()).getName()%></p>
    <p><%=prod.getDescription()%></p>
    <p style="font-size: 140%">Current bidding price: <%=prod.getCurrPrice()%>$</p>
    <% request.setAttribute(Product.ATTRIBUTE, prod);
        System.out.println(prod.getId());%>
<%--    <%  String error = (String)request.getAttribute("ERROR");--%>
<%--        if (!error.equals("")) { %>--%>
<%--            <h3><%=error%></h3>--%>
<%--       <% } %>--%>
    <form action="makeBid" method="post">
        <label for="bid">MAKE BID:</label>
        <input type="hidden" id="prId" name="prId" value=<%=prod.getId()%>>
        <input type="text" id="bid" name="bid"><br>
    </form>
    <p>CURRENT STATUS: <%=prod.getStatus()%></p>

    <a href="foreign_profile?id=<%=users.getUserByID(prod.getUserId(), false).getId()%>"> POSTED BY: <%=users.getUserByID(prod.getUserId(), false).getUsername()%> </a>
</body>
</html>
